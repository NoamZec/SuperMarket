package com.example.supermarket;

import com.google.android.gms.tasks.OnSuccessListener;

public class SuccessListenerClass implements OnSuccessListener {

    private Object o;

    public SuccessListenerClass() {}

    @Override
    public void onSuccess(Object o) {
        this.o = o;
    }

    public Object getO() {
        return o;
    }
}
