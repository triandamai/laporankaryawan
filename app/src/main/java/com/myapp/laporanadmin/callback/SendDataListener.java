package com.myapp.laporanadmin.callback;

public interface SendDataListener {
    void onStart();
    void onSuccess(String message);
    void onFailed(String message);
    void onError(String message);
}
