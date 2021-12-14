package com.example.tdmd.ViewModels;

import androidx.lifecycle.MutableLiveData;

public class MainViewModel {
    private MutableLiveData<String> testString;

    public void init() {
        if(testString != null) {
            return;
        }

        testString = new MutableLiveData<>();
    }

    public MutableLiveData<String> getTestString() {
        return testString;
    }

    public void setTestString(MutableLiveData<String> testString) {
        this.testString = testString;
    }
}
