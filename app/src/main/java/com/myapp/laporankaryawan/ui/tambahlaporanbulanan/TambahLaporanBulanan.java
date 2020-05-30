package com.myapp.laporankaryawan.ui.tambahlaporanbulanan;

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
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.TambahLaporanBulananFragmentBinding;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.serialize.req.RequestSimpanBulanan;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;

public class TambahLaporanBulanan extends BaseKaryawanFragment {

    private TambahLaporanBulananViewModel mViewModel;
    private TambahLaporanBulananFragmentBinding binding;


    public static TambahLaporanBulanan newInstance() {
        return new TambahLaporanBulanan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_laporan_bulanan_fragment, container, false);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(TambahLaporanBulananViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            setActionBar(binding.toolbar, "Ubah Laporan Bulanan", "");
            Gson gson = new Gson();
            LaporanBulananModel b = gson.fromJson(bundle.getString("laporanbulanan"), LaporanBulananModel.class);
            RequestSimpanBulanan requestSimpanBulanan = new RequestSimpanBulanan();
            requestSimpanBulanan.setIdUser(Integer.parseInt(b.getIdUser()));
            requestSimpanBulanan.setIsiLaporanbulanan(b.getIsiLaporanbulanan());
            requestSimpanBulanan.setIdLaporanbulanan(b.getIdLaporanbulanan());
            mViewModel.setListener(sendDataListener);
            mViewModel.tipe.setValue(getString(R.string.AKSI_UBAH));
            mViewModel.req.setValue(requestSimpanBulanan);

        } else {
            setActionBar(binding.toolbar, "Tambah Laporan Bulanan", "");
            RequestSimpanBulanan requestSimpanBulanan = new RequestSimpanBulanan();
            requestSimpanBulanan.setIdUser(Integer.parseInt(MyUser.getInstance(getContext()).getUser().getIdUser()));
            requestSimpanBulanan.setIsiLaporanbulanan("");

            mViewModel.setListener(sendDataListener);
            mViewModel.tipe.setValue(getString(R.string.AKSI_TAMBAH));
            mViewModel.req.setValue(requestSimpanBulanan);
        }
        binding.setVm(mViewModel);
        binding.setIsLoading(false);


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
            builder.setTitle("Infor");
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton("Oke", (dialog, which) -> {
                dialog.dismiss();
                back();
            }).show();
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
