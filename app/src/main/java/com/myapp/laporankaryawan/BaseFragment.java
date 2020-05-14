package com.myapp.laporankaryawan;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class BaseFragment extends Fragment {
    private MaterialAlertDialogBuilder builder;
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
    protected void back(){
        try {
        ((Root) getActivity()).onBack();
        }catch (NullPointerException e){
            Log.e("onBack Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }
    protected void setActionBar(Toolbar toolbar, String title, String subtitle){
        try {
            ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(subtitle);
        }catch (NullPointerException e){
            Log.e("AsctionBar",e.getMessage());
        }
    }
    protected void dialogGagal(String message) {

        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        builder.setTitle("Oops!");
        builder.setMessage(message);
        builder.setPositiveButton("Oke", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    protected void signOut(){
        ((Root) getActivity()).onSignOut();
    }
}
