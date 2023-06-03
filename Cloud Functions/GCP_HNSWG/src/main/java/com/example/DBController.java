package com.example;

import com.example.Firebase.FirebaseRestApi;
import com.example.Model.PineconeModel.*;
import com.example.Model.RequestForGCP.InventoryList;
import com.example.Model.RequestForGCP.Payload;
import com.example.Pinecone.PineconeRestApi;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DBController implements HttpFunction {

  Gson gson = new GsonBuilder()
          .setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cs102project-1bfe2b5.svc.asia-northeast1-gcp.pinecone.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    PineconeRestApi pineconeRestApi = retrofit.create(PineconeRestApi.class);

  Retrofit retrofitFirebase = new Retrofit.Builder()
            .baseUrl("https://databasetrialapp-30d6b-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

  FirebaseRestApi firebaseRestApi = retrofitFirebase.create(FirebaseRestApi.class);

  private static final Logger logger = Logger.getLogger(DBController.class.getName());


  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {


      response.setContentType("application/json");
    // deserialize
    Payload payload = gson.fromJson(request.getReader(), Payload.class);

    Match match = matchWithPlace(payload, response);

    if (match != null) {
        updateArrivingAid(match, response, payload);
    }
  }




  public Match matchWithPlace(Payload payload, HttpResponse response) {

      logger.info("matchWithPlace called");

      final Match[] match = new Match[1];



    PineconeQueryRequest request = new PineconeQueryRequest();

    List<Float> vector = vectorize(payload);
    request.setVector(vector);
    request.setTopK(1);
    request.setIncludeMetadata(false);
    request.setIncludeValues(true);
    request.setNamespace("");

    pineconeRestApi.query(request)
            .subscribe(new SingleObserver<>() {
                @Override
                public void onSubscribe(Disposable d) {
                    logger.info("pineconeRestApi.query onSubscribe");
                }

                @Override
                public void onSuccess(@NonNull PineconeQueryResponse pineconeQueryResponse) {

                    match[0] = pineconeQueryResponse.getMatches().get(0);
                    logger.info("pineconeRestApi.query onSuccess, matched field: " +  match[0].getId());

                }

                @Override
                public void onError(Throwable e) {
                    logger.info("pineconeRestApi.query onError " + e.getMessage() );
                    try {
                        response.getWriter().write(e.getMessage());
                    } catch (IOException ex) {
                        //log
                    }
                }
            });
    return match[0];
  }

  public List<Float> vectorize(Payload payload) {

    InventoryList list = payload.getInventoryList();
    List<Float> values = new ArrayList<>(8);

    values.add( (float) list.getFood() );
    values.add( (float) list.getHeater() );
    values.add( (float) list.getManCloth() );
    values.add( (float) list.getWomanCloth() );
    values.add( (float) list.getChildCloth() );
    values.add( (float) list.getHygene() );
    values.add( (float) list.getKitchenMaterial() );
    values.add( (float) list.getPowerbank() );

    return values;
  }

  public void updateArrivingAid(Match match, HttpResponse response, Payload payload) {

      logger.info("updateArrivingAid called");
      logger.info(match.getId());


      // fetch arriving aid
      firebaseRestApi.getInventory(match.getId())
              .subscribe(new SingleObserver<InventoryList>() {
                  @Override
                  public void onSubscribe(@NonNull Disposable d) {
                      logger.info("updateArrivingAid  firebaseRestApi.getInventory onSubscribe" );
                  }

                  @Override
                  public void onSuccess(@NonNull InventoryList inventoryList) {

                      logger.info("updateArrivingAid  firebaseRestApi.getInventory onSuccess" );

                      // add match internals to arriving aid
                      InventoryList arrivingUpdated = new InventoryList();
                      arrivingUpdated.setId(inventoryList.getId());

                      InventoryList values = payload.getInventoryList();
                      // ToDo: convert values to day

                      values = convertToDay(values);

                      int food = inventoryList.getFood() + values.getFood();
                      arrivingUpdated.setFood(food);

                      int heater = inventoryList.getHeater() + values.getHeater();
                      arrivingUpdated.setHeater(heater);

                      int manCloth = inventoryList.getManCloth() + values.getManCloth();
                      arrivingUpdated.setManCloth(manCloth);

                      int womanCloth = inventoryList.getWomanCloth() + values.getWomanCloth();
                      arrivingUpdated.setWomanCloth(womanCloth);

                      int childCloth = inventoryList.getChildCloth() + values.getChildCloth();
                      arrivingUpdated.setChildCloth(childCloth);

                      int hygene = inventoryList.getHygene() + values.getHygene();
                      arrivingUpdated.setHygene(hygene);

                      int kitchenMaterial = inventoryList.getKitchenMaterial() + values.getKitchenMaterial();
                      arrivingUpdated.setKitchenMaterial(kitchenMaterial);

                      int powerBank = inventoryList.getPowerbank() + values.getPowerbank();
                      arrivingUpdated.setPowerbank(powerBank);


                      // update arrivingList

                      firebaseRestApi.updateInventory(match.getId(), arrivingUpdated)
                              .subscribe(new SingleObserver<Object>() {
                                  @Override
                                  public void onSubscribe(@NonNull Disposable d) {
                                      logger.info("updateArrivingAid  firebaseRestApi.updateInventory onSubscribe" );
                                  }

                                  @Override
                                  public void onSuccess(@NonNull Object o) {

                                      logger.info("updateArrivingAid  firebaseRestApi.updateInventory onSuccess" );

                                      // update vector -> v = match + payload

                                      VectorUpsertRequest request = new VectorUpsertRequest();
                                      Vector vector = new Vector();

                                      List<Float> updatedValues = new ArrayList<>(8);

                                      updatedValues.add(match.getValues().get(0) - payload.getInventoryList().getFood());
                                      updatedValues.add(match.getValues().get(1) - payload.getInventoryList().getHeater());
                                      updatedValues.add(match.getValues().get(2) - payload.getInventoryList().getManCloth());
                                      updatedValues.add(match.getValues().get(3) - payload.getInventoryList().getWomanCloth());
                                      updatedValues.add(match.getValues().get(4) - payload.getInventoryList().getChildCloth());
                                      updatedValues.add(match.getValues().get(5) - payload.getInventoryList().getHygene());
                                      updatedValues.add(match.getValues().get(6) - payload.getInventoryList().getKitchenMaterial());
                                      updatedValues.add(match.getValues().get(7) - payload.getInventoryList().getPowerbank());

                                      vector.setValues(updatedValues);
                                      vector.setId(match.getId());

                                      List<Vector> vectorList = new ArrayList<>(1);
                                      vectorList.add(vector);
                                      request.setVectors(vectorList);

                                      pineconeRestApi.upsertVectors(request)
                                              .subscribe(new SingleObserver<Object>() {
                                                  @Override
                                                  public void onSubscribe(@NonNull Disposable d) {
                                                      logger.info("pineconeRestApi.upsertVectors onSubscribe " );
                                                  }

                                                  @Override
                                                  public void onSuccess(@NonNull Object o) {

                                                      logger.info("pineconeRestApi.upsertVectors onSuccess " );


                                                          // update driver status
                                                          logger.info("pineconeRestApi.upsertVectors onSuccess response succeeded" );

                                                          //first, update status:
                                                          Map<String, String> status = new HashMap<>();
                                                          status.put("status", "getChecked");

                                                          firebaseRestApi.updateDriverStatus(payload.getDriverName(), status)
                                                                  .subscribe(new SingleObserver<Object>() {
                                                                      @Override
                                                                      public void onSubscribe(@NonNull Disposable d) {
                                                                          logger.info("firebaseRestApi.updateDriverStatus onSubscribe " );
                                                                      }

                                                                      @Override
                                                                      public void onSuccess(@NonNull Object o) {

                                                                          logger.info("firebaseRestApi.updateDriverStatus onSuccess " );

                                                                          Map<String, Boolean> getStatus = new HashMap<>();
                                                                          getStatus.put("getStatus", true);

                                                                          firebaseRestApi.updateDriverGetStatus(payload.getDriverName(), getStatus)
                                                                                  .subscribe(new SingleObserver<Object>() {
                                                                                      @Override
                                                                                      public void onSubscribe(@NonNull Disposable d) {
                                                                                          logger.info("firebaseRestApi.updateDriverGetStatus onSubscribe " );
                                                                                      }

                                                                                      @Override
                                                                                      public void onSuccess(@NonNull Object o) {
                                                                                          logger.info("firebaseRestApi.updateDriverGetStatus onSuccess " );

                                                                                          try {
                                                                                              response.getWriter().write("OK");
                                                                                          } catch (IOException e) {

                                                                                          }

                                                                                          // write dropName
                                                                                          Map<String, String> drop = new HashMap<>();
                                                                                          drop.put("dropAdress", match.getId());

                                                                                          firebaseRestApi.updateDropAddress(payload.getDriverName(), drop)
                                                                                                  .subscribe(new SingleObserver<Object>() {
                                                                                                      @Override
                                                                                                      public void onSubscribe(@NonNull Disposable d) {
                                                                                                          logger.info("firebaseRestApi.updateDropAddress onSubscribe");
                                                                                                      }

                                                                                                      @Override
                                                                                                      public void onSuccess(@NonNull Object o) {
                                                                                                          logger.info("firebaseRestApi.updateDropAddress onSuccess. Function succeeded.");

                                                                                                          try {
                                                                                                              response.getWriter().write("OK");
                                                                                                          } catch (IOException e) {

                                                                                                          }
                                                                                                      }

                                                                                                      @Override
                                                                                                      public void onError(@NonNull Throwable e) {
                                                                                                          logger.info("firebaseRestApi.updateDropAddress onError: " + e.getMessage());
                                                                                                      }
                                                                                                  });
                                                                                      }

                                                                                      @Override
                                                                                      public void onError(@NonNull Throwable e) {
                                                                                          logger.info("firebaseRestApi.updateDriverGetStatus onError " + e.getMessage() );
                                                                                          try {
                                                                                              response.getWriter().write(e.getMessage());
                                                                                          } catch (IOException ex) {
                                                                                              //log
                                                                                          }
                                                                                      }
                                                                                  });
                                                                      }

                                                                      @Override
                                                                      public void onError(@NonNull Throwable e) {
                                                                          logger.info("firebaseRestApi.updateDriverStatus onError " );
                                                                          try {
                                                                              response.getWriter().write(e.getMessage());
                                                                          } catch (IOException ex) {
                                                                              //log
                                                                          }
                                                                      }
                                                                  });

                                                  }

                                                  @Override
                                                  public void onError(@NonNull Throwable e) {
                                                      logger.info("pineconeRestApi.upsertVectors onError " + e.getMessage() );
                                                  }
                                              });


                                  }

                                  @Override
                                  public void onError(@NonNull Throwable e) {
                                      logger.info("updateArrivingAid  firebaseRestApi.updateInventory onError: " + e.getMessage() );
                                      try {
                                          response.getWriter().write(e.getMessage());
                                      } catch (IOException ex) {
                                          //log
                                      }
                                  }
                              });
                  }

                  @Override
                  public void onError(@NonNull Throwable e) {
                      logger.info("updateArrivingAid  firebaseRestApi.getInventory onError: " + e.getMessage() );
                      try {
                          response.getWriter().write(e.getMessage());

                      } catch (IOException ex) {
                          //log
                      }
                  }
              });

  }

  public InventoryList convertToDay(InventoryList listAmount) {
      InventoryList listAsDay = new InventoryList();

      listAsDay.setFood(listAmount.getFood());
      listAsDay.setHeater(listAmount.getHeater() * 5);
      listAsDay.setManCloth(listAmount.getManCloth() * 10);
      listAsDay.setWomanCloth(listAmount.getWomanCloth() * 10);
      listAsDay.setChildCloth(listAmount.getChildCloth() * 6);
      listAsDay.setHygene(listAmount.getHygene());
      listAsDay.setKitchenMaterial(listAmount.getKitchenMaterial());
      listAsDay.setPowerbank(listAmount.getPowerbank());

      return listAsDay;
  }



}
