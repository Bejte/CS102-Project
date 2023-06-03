# Catastrophe Compass
## Description

As observed from disasters happening worldwide, knowledge is crucial to saving lives.
Regrettably, several occurrences of natural disasters, such as the recent earthquake in Turkey &
Syria, have exposed the fact that important information occasionally fails to transmit efficiently.
In Catastrophe Compass, we strive to establish a system that transmits information effectively.
By guaranteeing a proper communication channel among different teams and individuals, such
as single volunteers, and governmental & non-governmental organizations, our development
team aims to boost the effectiveness of post-disaster management. Additionally, by presenting a
hybrid algorithm, we plan to automate the allocation of humanitarian assistance to locations in
need and teams of volunteers to workplaces.

## Setup


Pinecone, GCP, OpenWeather and Firebase is used in the project. API keys are removed due to the 
free trial usage and cloud functions are needed to deployed to GCP. 

For Pinecone: You need to create a pod (p1 is reccomended). Then, retrieve the api key and instance location.
Record them in respective places in CS102-Project/app/src/main/java/com/example/catastrophecompass/RemoteDataRepository
/VectorDatabaseRepo/
You can reach Pinecone from: https://www.pinecone.io/learn/vector-database/
Note that free trials are limited due to high request.

For OpenWeather: 

For GCP: We have 2 cloud functions in CS102-Project/Cloud_Functions/ that needs to be deployed to GCP. 
We reccomend the usage of Google Cloud CLI. The trigger types are notes in the respective readme files. Remove readme files in the deployment.
NOTE: To start the app, you need to remove cloud functions from the repository as they consist their own build precedures 
as manifested in the respective pom.xml files. 
Deploy functions as 1st gen.

Firebase: You need to create a database instance and record the application. You need to retrieve the new google-services.json and replace it with 
the one under CS102-Project/app/ 

## Dependecies:

Firebase:
'com.google.firebase:firebase-bom:32.1.0'
'com.google.firebase:firebase-analytics'

Serialization/Deserialization:
'com.google.code.gson:gson:2.9.0'

Concurrency:
'io.reactivex.rxjava3:rxandroid:3.0.2'
'io.reactivex.rxjava3:rxjava:3.1.5'

UI:
'com.google.android.material:material:1.8.0'
'com.github.PhilJay:MPAndroidChart:v3.1.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
'androidx.navigation:navigation-fragment:2.5.3'
'androidx.navigation:navigation-ui:2.5.3
'de.hdodenhof:circleimageview:3.1.0'

Testing:
'androidx.test.espresso:espresso-core:3.5.1'
'androidx.test.ext:junit:1.1.5'
'junit:junit:4.13.2'

Room Persistance for Local Storage:
"androidx.room:room-runtime:2.5.1"
"androidx.room:room-compiler:2.5.1"
"androidx.room:room-rxjava3:2.5.1"

Dependency Injection (Hilt + Dagger2)
'com.google.dagger:hilt-android:2.45'
'com.google.dagger:hilt-compiler:2.45'

Rest Api (Retrofit for general, Okhttp for tcp optimizations)
'com.squareup.retrofit2:retrofit:2.9.0'
'com.squareup.retrofit2:converter-gson:2.9.0'
"com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"
'com.squareup.okhttp3:logging-interceptor:4.9.1
"com.squareup.okhttp3:okhttp-bom:4.10.0"

