package com.example.supermarket;

public interface Listener<T> {
    void onListen(T value) throws InterruptedException;
}
