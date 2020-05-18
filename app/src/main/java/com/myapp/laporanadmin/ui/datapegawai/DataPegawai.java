package com.myapp.laporanadmin.ui.datapegawai;

import android.os.Bundle;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.myapp.R;
import com.myapp.databinding.DataPegawaiFragmentBinding;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.ui.tambahuser.TambahUser;

public class DataPegawai extends BaseFragment {
    public static String TAG = "Data Pegawai";
    private DataPegawaiViewModel mViewModel;
    private DataPegawaiFragmentBinding binding;
    private AdapterDataPegawai adapterDataPegawai;
    private MaterialAlertDialogBuilder builder;

    public static DataPegawai newInstance() {
        return new DataPegawai();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.data_pegawai_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar, "Data Karyawan", "");
        setHasOptionsMenu(true);
        binding.setIsLoading(true);
        adapterDataPegawai = new AdapterDataPegawai(adapterItemClicked);
        binding.rv.setAdapter(adapterDataPegawai);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new DataPegawaiFactory(getContext())).get(DataPegawaiViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        mViewModel.init();
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataPegawaiViewModel mViewModel) {
        mViewModel.getKaryawanData().observe(getViewLifecycleOwner(), karyawanObjects -> {

            binding.setIsLoading(false);
            if (karyawanObjects != null) {
                adapterDataPegawai.setData(karyawanObjects);
            }
        });
    }

    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onEdit(int pos) {
            KaryawanObject data = adapterDataPegawai.getFromPosition(pos);
            UserModel userModel = UserModel.covertdariobjek(data);
            builder = new MaterialAlertDialogBuilder(getActivity(), R.style.dialog);
            builder.create();
            builder.setTitle("Hi");
            builder.setMessage("Mau Edit Karyawan " + userModel.getNamaUser() + "?");
            builder.setPositiveButton("Edit", (dialog, which) -> {
                dialog.dismiss();
                Gson gson = new Gson();
                Bundle bundle = new Bundle();
                bundle.putString("user",gson.toJson(userModel));
                TambahUser tambahUser = new TambahUser();
                replaceFragment(, null);
            });
            builder.setNeutralButton("Batal", (dialog, which) -> dialog.dismiss());
            builder.setNegativeButton("Hapus", (dialog, which) -> {
                mViewModel.hapus(userModel);
            });
            builder.show();
        }

        @Override
        public void onDelete(int pos) {

        }

        @Override
        public void onDetail(int pos) {

        }
    };
    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            showProgress("Menghapus...");
        }

        @Override
        public void onSuccess(String message) {
            dismissProgress();
            dialogBerhasil(message);
            mViewModel.fetchFromApi();
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
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.fetchFromApi();
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbardata, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_tambah) {
            replaceFragment(TambahUser.newInstance(), null);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
