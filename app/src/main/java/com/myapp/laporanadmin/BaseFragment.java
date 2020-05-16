package com.myapp.laporanadmin;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.ImagePickerActivity;
import com.myapp.R;

import java.util.Objects;

public class BaseFragment extends Fragment {
    private MaterialAlertDialogBuilder builder;
    public static final int REQUEST_IMAGE = 100;
    protected void addFragment(Fragment f,String TAG){
        try {
            ((Root) requireActivity()).addFragment(f, TAG);
        }catch (NullPointerException e){
            Log.e("add Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }
    protected void replaceFragment(Fragment f,String TAG){
        try {

            ((Root) requireActivity()).replaceFragment(f, TAG);
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
            ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
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
    protected void dialogBerhasil(String message) {

        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        builder.setTitle("Info");
        builder.setMessage(message);
        builder.setPositiveButton("Oke", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    protected void signOut(){
        ((Root) getActivity()).onSignOut();
    }

    protected void launchCameraIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    protected void launchGalleryIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }
}
