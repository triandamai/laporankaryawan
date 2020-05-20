package com.myapp.laporankaryawan.ui.tambahlaporanbulanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.TambahLaporanBulananFragmentBinding;
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
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(TambahLaporanBulananViewModel.class);
        return binding.getRoot();
    }


}
