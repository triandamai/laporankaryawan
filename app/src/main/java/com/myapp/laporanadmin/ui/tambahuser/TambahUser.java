package com.myapp.laporanadmin.ui.tambahuser;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;

import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import com.myapp.ImagePickerActivity;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.TambahUserFragmentBinding;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.PickImage;
import com.myapp.laporanadmin.callback.SendDataListener;

import java.io.IOException;
import java.util.List;

public class TambahUser extends BaseFragment {
    public static String  TAG = TambahUser.class.getSimpleName();

    private TambahUserViewModel mViewModel;
    private TambahUserFragmentBinding binding;
    private String tipe;
    private UserModel userModel;

    public TambahUser(){ }

    public static TambahUser newInstance() {
        return new TambahUser();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_user_fragment, container, false);
        binding.setPick(pickImage);

        mViewModel = new ViewModelProvider(requireActivity(),new TambahUserFactory(getContext()))
                .get(TambahUserViewModel.class);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar,"Tambah Karyawan","");

        Bundle bundle = getArguments();
        if (bundle.getString("user") != null){
            Gson gson = new Gson();
            UserModel userModel = gson.fromJson(bundle.getString("user"),UserModel.class);
            mViewModel.usermodel.set(userModel);
        }


        binding.setVm(mViewModel);
        mViewModel.setOnSendData(sendDataListener);
        binding.setIsLoading(false);


        ImagePickerActivity.clearCache(getContext());

        return binding.getRoot();
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.toolbarformnav,menu);
    }
    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String message) {
            binding.setIsLoading(false);
            dialogBerhasil(message);
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }
    };
    private PickImage pickImage = new PickImage() {
        @Override
        public void ImageClicked(View v) {
            Dexter.withActivity(getActivity())
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                showImagePickerOptions();
                            }

                            if (report.isAnyPermissionPermanentlyDenied()) {
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        }
    };

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getContext(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                    // loading profile image from local cache
                    binding.setImage(uri.toString());
                    mViewModel.foto.setValue(encodeImage(uri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
