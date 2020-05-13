package com.myapp.laporankaryawan.callback;

public interface SendDataListener {
    void onStart();
    void onSuccess(String message);
    void onFailed(String message);
    void onError(String message);
}
