package com.myapp.laporankaryawan.ui.detaillaporanhariankaryawan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.DetailHarianKaryawanFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.callback.OnViewClicked;
import com.myapp.laporankaryawan.ui.tambahlaporanharian.TambahLaporanHarian;

public class DetailHarianKaryawan extends BaseKaryawanFragment {

    private GoogleMap gmaps;

    private DetailHarianKaryawanViewModel mViewModel;
    private DetailHarianKaryawanFragmentBinding binding;
    private String id;
    private LaporanHarianObject laporanHarianObject;

    public static DetailHarianKaryawan newInstance() {
        return new DetailHarianKaryawan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_harian_karyawan_fragment, container, false);
        binding.setUpdate(onViewClicked);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(), new DetailHarianKaryawanFactory(getContext())).get(DetailHarianKaryawanViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        Bundle bundle = getArguments();
        if (bundle != null) {

            this.id = bundle.getString("idlaporanharian");

            String status = bundle.getString("statuslaporanharian");
            if (status.equalsIgnoreCase("1")) {
                binding.setIsEditable(true);
                binding.setISrejected(false);
            } else if (status.equalsIgnoreCase("2")) {
                binding.setIsEditable(false);
                binding.setISrejected(false);
            } else {
                binding.setIsEditable(false);
                binding.setISrejected(true);
            }

            setActionBar(binding.toolbar, "Laporan Harian ", "");
        } else {

        }
        binding.mapview.onCreate(savedInstanceState);
        binding.mapview.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.mapview.getMapAsync(googleMap -> {
            gmaps = googleMap;
            LatLng latLng = new LatLng(Double.parseDouble(laporanHarianObject.getLatitudeLaporanharian()), Double.parseDouble(laporanHarianObject.getLongitudeLaporanharian()));
            gmaps.addMarker(new MarkerOptions().position(latLng).title("Lokasi Laporan").snippet(laporanHarianObject.getAlamatLaporanharian()));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
        binding.mapview.onResume();
    }

    private void observe(DetailHarianKaryawanViewModel mViewModel) {
        mViewModel.getObject(id);
        mViewModel.getLaporanHarianObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanHarianObject>() {
            @Override
            public void onChanged(LaporanHarianObject laporanHarianObject) {
                if (laporanHarianObject != null) {

                    binding.setData(laporanHarianObject);
                    DetailHarianKaryawan.this.laporanHarianObject = laporanHarianObject;
                    if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("1")) {
                        binding.setISrejected(false);
                    } else if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("2")) {
                        binding.setISrejected(true);
                    } else if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("3")) {
                        binding.setISrejected(false);
                    }
                }
            }
        });
    }

    private OnViewClicked onViewClicked = new OnViewClicked() {
        @Override
        public void onUpdateBulanan(LaporanBulananObject l) {

        }

        @Override
        public void onUpdateHarian(LaporanHarianObject l) {
            Gson gson = new Gson();

            KotaModel k = new KotaModel();
            k.setNamaKota(l.getNamaKota());
            k.setIdKota(l.getIdKota());
            k.setUpdatedAt("kosong");
            k.setCreatedAt(l.getCreatedAt());
            OutletModel o = new OutletModel();
            o.setKota(k);
            o.setIdOutlet(l.getIdOutlet());
            o.setNamaOutlet(l.getNamaOutlet());
            o.setUpdatedAt("kosong");
            o.setCreatedAt(l.getCreatedAt());
            o.setIdKota(l.getIdKota());

            LaporanHarianModel h = new LaporanHarianModel();
            h.setOutlet(o);
            h.setUser(MyUser.getInstance(getContext()).getUser());
            h.setStatusLaporanharian(l.getStatusLaporanharian());
            h.setLongitudeLaporanharian(l.getLongitudeLaporanharian());
            h.setLatitudeLaporanharian(l.getLatitudeLaporanharian());
            h.setKeteranganLaporanharian(l.getKeteranganLaporanharian());
            h.setUpdatedAt(l.getUpdatedAt());
            h.setIdUser(MyUser.getInstance(getContext()).getUser().getIdUser());
            h.setIdOutlet(l.getIdOutlet());
            h.setIdLaporanharian(l.getIdLaporanharian());
            h.setBuktiLaporanharian(l.getBuktiLaporanharian());
            h.setAlamatLaporanharian(l.getAlamatLaporanharian());

            Bundle bundle = new Bundle();
            bundle.putString("laporanharian", gson.toJson(h));
            TambahLaporanHarian tambahUser = new TambahLaporanHarian();
            tambahUser.setArguments(bundle);
            builder.setTitle("Hi");
            builder.setMessage("Mau Edit Laporan " + l.getKeteranganLaporanharian() + "?");
            builder.setPositiveButton("Edit", (dialog, which) -> {
                dialog.dismiss();

                replaceFragment(tambahUser, null);
            });
            builder.setNeutralButton("Batal", (dialog, which) -> dialog.dismiss());
            builder.show();
        }
    };

    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            showProgress("Memproses...");
        }

        @Override
        public void onSuccess(String message) {
            dismissProgress();

            builder.setTitle("Info");
            builder.setMessage(message);
            builder.setPositiveButton("Oke", (dialog, which) -> {
                dialog.dismiss();
                back();
            });
            builder.show();
        }

        @Override
        public void onFailed(String message) {
            dismissProgress();
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            dismissProgress();
            dialogGagal(message);
        }
    };
}
