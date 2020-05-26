package com.myapp.laporanadmin.ui.tambahkota;

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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.myapp.R;
import com.myapp.databinding.TambahKotaFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.callback.SendDataListener;

public class TambahKota extends BaseAdminFragment {
    public static String TAG = "Tambah Kota";
    private TambahKotaViewModel mViewModel;
    private TambahKotaFragmentBinding binding;
    private String tipe;
    private KotaModel kotaModel;

    public TambahKota() {

    }


    public static TambahKota newInstance() {
        return new TambahKota();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_kota_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new TambahKotaFactory(getContext())).get(TambahKotaViewModel.class);
        setHasOptionsMenu(true);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        binding.setVm(mViewModel);
        mViewModel.setOnSendData(sendDataListener);
        Gson gson = new Gson();
        Bundle bundle = getArguments();
        if (bundle != null) {
            setActionBar(binding.toolbar, "Ubah Kota", "");
            kotaModel = gson.fromJson(bundle.getString("kota"), KotaModel.class);
            mViewModel.tipe.setValue(getString(R.string.AKSI_UBAH));
            mViewModel.kotamodel.setValue(kotaModel);
        } else {
            KotaModel kotaModel = new KotaModel();
            kotaModel.setIdKota("");
            kotaModel.setNamaKota("");
            kotaModel.setUpdatedAt("");
            kotaModel.setCreatedAt("");
            mViewModel.kotamodel.setValue(kotaModel);
            mViewModel.tipe.setValue(getString(R.string.AKSI_TAMBAH));
            setActionBar(binding.toolbar, "Tambah Kota", "");
            mViewModel.tipe.setValue(getString(R.string.AKSI_TAMBAH));

        }


        return binding.getRoot();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        KotaModel kotaModel = new KotaModel();
        kotaModel.setIdKota("");
        kotaModel.setNamaKota("");
        kotaModel.setUpdatedAt("");
        kotaModel.setCreatedAt("");
        mViewModel.kotamodel.setValue(kotaModel);
        mViewModel.tipe.setValue("");
    }
}
