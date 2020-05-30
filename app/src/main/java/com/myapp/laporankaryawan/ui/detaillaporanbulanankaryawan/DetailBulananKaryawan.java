package com.myapp.laporankaryawan.ui.detaillaporanbulanankaryawan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.DetailBulananKaryawanFragmentBinding;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.callback.OnViewClicked;
import com.myapp.laporankaryawan.ui.tambahlaporanbulanan.TambahLaporanBulanan;

public class DetailBulananKaryawan extends BaseKaryawanFragment {

    private DetailBulananKaryawanViewModel mViewModel;
    private DetailBulananKaryawanFragmentBinding binding;
    private String id = "";
    private LaporanBulananObject laporanBulananObject;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_bulanan_karyawan_fragment, container, false);
        binding.setUpdate(viewClicked);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(), new DetailBulananKaryawanFactory(getContext())).get(DetailBulananKaryawanViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);


        Bundle bundle = getArguments();
        if (bundle != null) {

            this.id = bundle.getString("idlaporanbulanan");

            String status = bundle.getString("statuslaporanbulanan");
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

            setActionBar(binding.toolbar, "Laporan Bulanan ", "");
        } else {

        }

        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailBulananKaryawanViewModel mViewModel) {
        mViewModel.getObject(id);
        mViewModel.getLaporanBulananObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanBulananObject>() {
            @Override
            public void onChanged(LaporanBulananObject laporanBulananObject) {
                if (laporanBulananObject != null) {
                    DetailBulananKaryawan.this.laporanBulananObject = laporanBulananObject;
                    binding.setData(laporanBulananObject);
                    if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("1")) {
                        binding.setISrejected(false);
                    } else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("2")) {
                        binding.setISrejected(true);
                    } else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("3")) {
                        binding.setISrejected(false);
                    }
                }
            }
        });
    }

    private OnViewClicked viewClicked = new OnViewClicked() {
        @Override
        public void onUpdateBulanan(LaporanBulananObject l) {

            builder.setTitle("Hi");
            builder.setMessage("Mau Edit Laporan " + l.getNamaUser() + "?");
            builder.setPositiveButton("Edit", (dialog, which) -> {
                dialog.dismiss();
                Gson gson = new Gson();

                LaporanBulananModel b = new LaporanBulananModel();
                b.setUser(MyUser.getInstance(getContext()).getUser());
                b.setUpdatedAt(l.getUpdatedAt());
                b.setIsiLaporanbulanan(l.getIsiLaporanbulanan());
                b.setStatusLaporanbulanan(l.getStatusLaporanbulanan());
                b.setIdUser(l.getIdUser());
                b.setCreatedAt(l.getCreatedAt());
                b.setIdLaporanbulanan(l.getIdLaporanbulanan());

                Bundle bundle = new Bundle();
                bundle.putString("laporanbulanan", gson.toJson(b));
                TambahLaporanBulanan tambahUser = new TambahLaporanBulanan();
                tambahUser.setArguments(bundle);
                replaceFragment(tambahUser, null);
            });
            builder.setNeutralButton("Batal", (dialog, which) -> dialog.dismiss());
            builder.show();
        }

        @Override
        public void onUpdateHarian(LaporanHarianObject l) {

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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
