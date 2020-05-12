package com.myapp.laporankaryawan;

import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class BaseFragment extends Fragment {

    protected void addFragment(Fragment f,String TAG){
        try {
            ((Root) Objects.requireNonNull(getActivity())).addFragment(f, TAG);
        }catch (NullPointerException e){
            Log.e("add Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }
    protected void replaceFragment(Fragment f,String TAG){
        try {

            ((Root) Objects.requireNonNull(getActivity())).replaceFragment(f, TAG);
        }catch (NullPointerException e){
            Log.e("replace Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }
    private void back(){
        try {
        ((Root) getActivity()).onBack();
        }catch (NullPointerException e){
            Log.e("onBack Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }
}
