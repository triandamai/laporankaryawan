package com.myapp.laporankaryawan.ui.tambahlaporanharian;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import com.myapp.MapsActivity;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.TambahLaporanHarianFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.laporanadmin.callback.PickImage;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.ui.bottomsheet.SheetOutlet;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.callback.PickAlamat;
import com.myapp.laporankaryawan.callback.PickOutlet;

import java.io.IOException;
import java.util.List;


public class TambahLaporanHarian extends BaseKaryawanFragment {
    private static final int CODE_MAP = 2;
    private TambahLaporanHarianViewModel mViewModel;

    private TambahLaporanHarianFragmentBinding binding;
    private SheetOutlet sheetOutlet;

    public static TambahLaporanHarian newInstance() {
        return new TambahLaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_laporan_harian_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(TambahLaporanHarianViewModel.class);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
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

        mViewModel.setListener(sendDataListener);
        mViewModel.foto.setValue("");
        mViewModel.tipe.setValue(getString(R.string.AKSI_TAMBAH));
        mViewModel.outletmodel.setValue(outletModel);
        mViewModel.lat.setValue(0.0);
        mViewModel.lng.setValue(0.0);
        mViewModel.isi.setValue("");
        mViewModel.alamat.setValue("");
        sheetOutlet = new SheetOutlet();
        sheetOutlet.setOnSheetListener(sheetListener);

        binding.setPickOutlet(pickOutlet);
        binding.setIsLoading(false);
        binding.setAlamat("");
        binding.setImage("dssfd");
        binding.setOutlet(outletModel);
        binding.setVm(mViewModel);
        binding.setPick(pickImage);
        binding.setPickAlamat(pickAlamat);

        return binding.getRoot();
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
            builder.setMessage("Berhasil Menambahkan Laporan");
            builder.setPositiveButton("Oke", (dialog, which) -> {
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
    private PickOutlet pickOutlet = () -> {
        sheetOutlet.show(getActivity().getSupportFragmentManager(), "Ambil Outlet");
    };
    private SheetOutlet.BottomSheetListener sheetListener = new SheetOutlet.BottomSheetListener() {
        @Override
        public void onOptionClick(OutletObject outletObject) {
            sheetOutlet.dismiss();
            OutletModel outletModel = new OutletModel();
            KotaModel kota = new KotaModel();
            outletModel.setIdKota(outletObject.getIdKota());
            outletModel.setNamaOutlet(outletObject.getNamaOutlet());
            outletModel.setIdOutlet(outletObject.getIdOutlet());
            outletModel.setCreatedAt("");
            outletModel.setUpdatedAt("");
            kota.setCreatedAt("");
            kota.setUpdatedAt("");
            kota.setIdKota(outletObject.getIdKota());
            kota.setNamaKota(outletObject.getNamaKota());
            outletModel.setKota(kota);
            mViewModel.outletmodel.setValue(outletModel);
            binding.setOutlet(outletModel);
        }
    };
    private PickAlamat pickAlamat = v -> Dexter.withActivity(getActivity())
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()) {
                        Intent map = new Intent(getActivity(), MapsActivity.class);
                        startActivityForResult(map, CODE_MAP);
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
    private PickImage pickImage = v -> Dexter.withActivity(getActivity())
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()) {
                        //showImagePickerOptions();
                        launchCameraIntent();
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        MyUser.getInstance(getContext()).setTipeFormUser(null);

        mViewModel.tipe.setValue(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
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
            } else if (requestCode == CODE_MAP) {
                //if (resultCode == Activity.RESULT_OK) {
                String alamat = data.getStringExtra("alamat");
                double lat = data.getDoubleExtra("lat", 0);
                double lng = data.getDoubleExtra("lng", 0);
                mViewModel.lng.setValue(lng);
                mViewModel.lat.setValue(lat);
                Log.e("hasil Alamat", alamat);
                binding.setAlamat(alamat);
                mViewModel.alamat.getValue();
                //}
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
