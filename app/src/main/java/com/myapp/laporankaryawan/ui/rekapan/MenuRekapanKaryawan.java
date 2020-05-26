package com.myapp.laporankaryawan.ui.rekapan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.MenuRekapanKaryawanFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.ui.rekapan.harian.RekapanHarianKaryawan;

public class MenuRekapanKaryawan extends BaseKaryawanFragment {

    private MenuRekapanKaryawanViewModel mViewModel;
    private MenuRekapanKaryawanFragmentBinding binding;

    public static MenuRekapanKaryawan newInstance() {
        return new MenuRekapanKaryawan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.menu_rekapan_karyawan_fragment, container, false);
        setActionBar(binding.toolbar, "Data Rekapan", "");
        setHasOptionsMenu(true);
        binding.setEvent(new com.myapp.laporanadmin.callback.ListRekapan() {
            @Override
            public void onBulanan() {
                replaceFragment(RekapanHarianKaryawan.newInstance(), null);
            }

            @Override
            public void onHarian() {
                replaceFragment(RekapanHarianKaryawan.newInstance(), null);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuRekapanKaryawanViewModel.class);
        // TODO: Use the ViewModel
    }
}