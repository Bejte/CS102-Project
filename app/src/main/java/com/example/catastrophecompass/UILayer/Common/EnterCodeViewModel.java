package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnterCodeViewModel extends ViewModel {

    private MutableLiveData<String> placeName = new MutableLiveData<>();

    public LiveData<String> getPlaceName() {
        return placeName;
    }

   // public void validateCode(String code) {
        // Implement the logic to validate the code here.
        // This might involve interacting with your Firebase repository to check if the code exists,
        // then updating the placeName MutableLiveData with the result.

        // For now, I'll use a placeholder implementation.
     //   if (code.equals("1234")) {
       //     placeName.setValue("Valid Place");
       // } else {

        //    placeName.setValue(null);
       // }
   // }
}