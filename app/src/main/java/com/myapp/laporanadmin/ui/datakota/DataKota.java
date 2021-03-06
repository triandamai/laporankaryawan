package com.myapp.laporanadmin.ui.datakota;

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
import com.myapp.databinding.DataKotaFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.ui.tambahkota.TambahKota;

public class DataKota extends BaseAdminFragment {
    public static String TAG = "Data Kota";
    private DataKotaViewModel mViewModel;
    private DataKotaFragmentBinding binding;
    private AdapterDataKota adapterDataKota;

    public static DataKota newInstance() {
        return new DataKota();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.data_kota_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar, "Data Kota", "");
        setHasOptionsMenu(true);
        binding.setIsLoading(true);
        adapterDataKota = new AdapterDataKota(adapterItemClicked);
        binding.rv.setAdapter(adapterDataKota);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new DataKotaFactory(getContext())).get(DataKotaViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        mViewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchFromApi();
        observe(mViewModel);
    }

    private void observe(DataKotaViewModel mViewModel) {
        mViewModel.getListKota().observe(getViewLifecycleOwner(), kotaObjects -> {
            binding.setIsLoading(false);
            if (kotaObjects != null) {
                adapterDataKota.setData(kotaObjects);
            }
        });
    }

    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {

        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onEdit(int pos) {
            KotaObject kotaObject = adapterDataKota.getFromPosition(pos);
            KotaModel kotaModel = KotaModel.convertdariobject(kotaObject);

            builder.setTitle("Hi");
            builder.setMessage("Mau Edit Kota " + kotaModel.getNamaKota() + "?");
            builder.setPositiveButton("Edit", (dialog, which) -> {
                dialog.dismiss();
                TambahKota tambahKota = new TambahKota();
                Bundle bundle = new Bundle();
                Gson gson = new Gson();

                bundle.putString("kota", gson.toJson(kotaModel));
                tambahKota.setArguments(bundle);
                replaceFragment(tambahKota, null);
            });
            builder.setNeutralButton("Batal", (dialog, which) -> dialog.dismiss());
            builder.setNegativeButton("Hapus", (dialog, which) -> {
                dialog.dismiss();
                mViewModel.hapus(kotaModel);
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
            binding.setIsLoading(false);
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbardata, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tambah:
                replaceFragment(TambahKota.newInstance(), null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
