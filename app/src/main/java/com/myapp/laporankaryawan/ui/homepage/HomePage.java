package com.myapp.laporankaryawan.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.HomePageItemClicked;
import com.myapp.laporankaryawan.databinding.HomePageFragmentBinding;
import com.myapp.laporankaryawan.ui.DataPegawai.DataPegawai;
import com.myapp.laporankaryawan.ui.Laporan.Laporan;
import com.myapp.laporankaryawan.ui.TambahKota.TambahKota;
import com.myapp.laporankaryawan.ui.TambahOutlet.TambahOutlet;
import com.myapp.laporankaryawan.ui.TambahUser.TambahUser;

public class HomePage extends BaseFragment {
    public static String TAG = "Home Page Fragment";

    private HomePageViewModel mViewModel;
    private HomePageFragmentBinding binding;

    public static HomePage newInstance() {
        return new HomePage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater,R.layout.home_page_fragment, container, false);
      binding.setClick(homePageItemClicked);
      return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new HomePageFactory(getContext())).get(HomePageViewModel.class);
        // TODO: Use the ViewModel
    }

    private HomePageItemClicked homePageItemClicked = new HomePageItemClicked() {
        @Override
        public void tambahUser(View v) {
            replaceFragment(TambahUser.newInstance(),null);
        }

        @Override
        public void tambahKota(View v) {
            replaceFragment(TambahKota.newInstance(),null);
        }

        @Override
        public void tambahOutlet(View v) {
            replaceFragment(TambahOutlet.newInstance(),null);
        }

        @Override
        public void Pegawai(View v) {
            replaceFragment(DataPegawai.newInstance(),null);
        }

        @Override
        public void Harian(View v) {
            replaceFragment(Laporan.newInstance(),null);
        }

        @Override
        public void Bulanan(View v) {
            replaceFragment(Laporan.newInstance(),null);
        }

        @Override
        public void LogOut(View v) {
            replaceFragment(Laporan.newInstance(),null);
        }

        @Override
        public void Notifikasi(View v) {
            replaceFragment(Laporan.newInstance(),null);
        }
    };
}
