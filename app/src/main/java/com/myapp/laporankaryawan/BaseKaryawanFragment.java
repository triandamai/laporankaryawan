package com.myapp.laporankaryawan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.ImagePickerActivity;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.laporanadmin.RootAdmin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Objects;

public class BaseKaryawanFragment extends Fragment {
    protected MaterialAlertDialogBuilder builder;
    public static final int REQUEST_IMAGE = 100;
    private ProgressDialog progressDialog;


    public void showProgress(String pesan) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(pesan);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }

    protected LaporanHarianRequestData getRequestHarian() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        LaporanHarianRequestData l = new LaporanHarianRequestData();
        l.setIduser(Integer.parseInt(MyUser.getInstance(getContext()).getUser().getIdUser()));
        l.setBulanLaporanharian(month + 1);
        l.setTahunLaporanharian(year);
        return l;
    }

    protected LaporanBulananRequestData getRequestBulanan() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        LaporanBulananRequestData l = new LaporanBulananRequestData();
        l.setIdUser(MyUser.getInstance(getContext()).getUser().getIdUser());
        l.setBulanLaporanbulanan(month + 1);
        l.setTahunLaporanbulanan(year);
        return l;
    }

    protected void addFragment(Fragment f, String TAG) {
        try {
            ((RootKaryawan) requireActivity()).addFragment(f, TAG);
        } catch (NullPointerException e) {
            Log.e("add Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }

    protected void replaceFragment(Fragment f, String TAG) {
        try {

            ((RootKaryawan) requireActivity()).replaceFragment(f, TAG);
        } catch (NullPointerException e) {
            Log.e("replace Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }

    protected void back() {
        try {
            if(MyUser.getInstance(getContext()).getUser().getLevelUser().equalsIgnoreCase("1")){
                ((RootKaryawan) getActivity()).onBack();
            }else {
                ((RootAdmin) getActivity()).onBack();
            }

        } catch (NullPointerException e) {
            Log.e("onBack Fragment", Objects.requireNonNull(e.getMessage()));
        }
    }

    protected void setActionBar(Toolbar toolbar, String title, String subtitle) {
        try {
            ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(subtitle);
        } catch (NullPointerException e) {
            Log.e("AsctionBar", e.getMessage());
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

    protected void signOut() {
        ((RootKaryawan) getActivity()).onSignOut();
    }


    protected void launchCameraIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

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

    // navigating user to app settings
    protected void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    protected void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("heheh");
        builder.setMessage("pesan");
        builder.setPositiveButton("Pengaturan", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    protected String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    protected String encodeImage(String path) {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;

    }

}
