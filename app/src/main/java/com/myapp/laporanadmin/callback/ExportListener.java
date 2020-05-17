package com.myapp.laporanadmin.callback;

public interface ExportListener {
    void onStart();
    void onSucces(String nama_file);
    void onError(String message);
}
