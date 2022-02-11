package com.example.inventory.utils;

import androidx.lifecycle.MutableLiveData;

public class StateView extends MutableLiveData {
    //region
    public enum State {
        LOADING,
        COMPLETE,
        ERROR
    }
    //endregion

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        postValue(this.state = state);
    }
}
