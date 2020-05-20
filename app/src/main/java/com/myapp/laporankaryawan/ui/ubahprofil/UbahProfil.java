package com.myapp.laporankaryawan.ui.ubahprofil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.myapp.ImagePickerActivity;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.UbahProfilFragmentBinding;
import com.myapp.laporanadmin.callback.PickImage;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;

import java.io.IOException;
import java.util.List;

public class UbahProfil extends BaseKaryawanFragment {

    private UbahProfilViewModel mViewModel;
    private UbahProfilFragmentBinding binding;

    public static UbahProfil newInstance() {
        return new UbahProfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.ubah_profil_fragment, container, false);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        setActionBar(binding.toolbar, "Ubah Profil", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(UbahProfilViewModel.class);
        mViewModel.setListener(sendDataListener);
        mViewModel.usermodel.setValue(MyUser.getInstance(getContext()).getUser());
        mViewModel.foto.setValue("");
        binding.setFoto(MyUser.getInstance(getContext()).getUser().getFotoUser());
        binding.setIsLoading(false);
        binding.setVm(mViewModel);
        binding.setPick(pickImage);
        ImagePickerActivity.clearCache(getContext());
        return binding.getRoot();
    }

    private PickImage pickImage = v -> Dexter.withActivity(getActivity())
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
    public void onDestroy() {
        super.onDestroy();
        MyUser.getInstance(getContext()).setTipeFormUser(null);

        mViewModel.usermodel.setValue(null);
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
                    binding.setFoto(uri.toString());

                    mViewModel.foto.setValue(encodeImage(uri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String message) {
            binding.setIsLoading(false);
            builder.setTitle("Info");
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton("OKE", (dialog, which) -> {
                dialog.dismiss();
                back();
            });
            builder.show();
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbarformnav, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
