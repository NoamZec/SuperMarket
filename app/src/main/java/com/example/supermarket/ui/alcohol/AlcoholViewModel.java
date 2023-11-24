package com.example.supermarket.ui.alcohol;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlcoholViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AlcoholViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}