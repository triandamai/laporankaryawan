package com.myapp.data.service;

import android.content.Context;
import android.util.Log;

import com.myapp.data.R;

public class ApiHandler {
    private static final String TAG = "LAPORAN ::";

    public static boolean cek(int res, Context context, String pesan) {
        try {
            if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_OK))) {
                Log.d(TAG, "response code = 200 : " + pesan);
                return true;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_BAD_REQUEST))) {
                Log.d(TAG, "response code = 400 : " + pesan);
                return false;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_UNAUTHORIZED))) {
                Log.d(TAG, "response code = 401 : " + pesan);
                return false;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_NOT_FOUND))) {
                Log.d(TAG, "response code = 404 : " + pesan);
                return false;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_NOT_INTERNALSERVERERROR))) {
                Log.d(TAG, "response code = 500 : " + pesan);
                return false;
            } else {
                Log.d(TAG, "response code = Tidak diketahui : " + pesan);
                return false;
            }
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }

    }
}
