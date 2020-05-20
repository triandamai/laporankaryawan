package com.myapp.laporankaryawan.ui.tambahlaporanharian;

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

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.myapp.ImagePickerActivity;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.TambahLaporanHarianFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.laporanadmin.callback.PickImage;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;

import java.io.IOException;
import java.util.List;


public class TambahLaporanHarian extends BaseKaryawanFragment {

    private TambahLaporanHarianViewModel mViewModel;

    private TambahLaporanHarianFragmentBinding binding;

    public static TambahLaporanHarian newInstance() {
        return new TambahLaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_laporan_harian_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(TambahLaporanHarianViewModel.class);
        setActionBar(binding.toolbar, "Tambah Laporan Harian", "");
        setHasOptionsMenu(true);
        OutletModel outletModel = new OutletModel();
        KotaModel kotaModel = new KotaModel();
        kotaModel.setIdKota("");
        kotaModel.setNamaKota("");
        kotaModel.setUpdatedAt("");
        kotaModel.setCreatedAt("");
        outletModel.setKota(kotaModel);
        outletModel.setIdOutlet("");
        outletModel.setUpdatedAt("");
        outletModel.setCreatedAt("");
        outletModel.setNamaOutlet("");
        outletModel.setIdKota("");
        mViewModel.foto.setValue("");
        mViewModel.tipe.setValue("");
        mViewModel.outletmodel.setValue(outletModel);

        mViewModel.req.setValue(null);
        binding.setIsLoading(false);
        binding.setAlamat("");
        binding.setImage("");
        binding.setOutlet(outletModel);
        binding.setVm(mViewModel);
        binding.setPick(pickImage);

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

        mViewModel.tipe.setValue(null);
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
