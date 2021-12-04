package com.test.stoppishing.ui.setphonenumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetNumberViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SetNumberViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}