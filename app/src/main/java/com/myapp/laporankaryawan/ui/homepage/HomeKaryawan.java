package com.myapp.laporankaryawan.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.HomeKaryawanFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.callback.HomePageCallback;
import com.myapp.laporankaryawan.ui.laporanbulanan.LaporanBulanan;
import com.myapp.laporankaryawan.ui.laporanharian.LaporanHarian;
import com.myapp.laporankaryawan.ui.listubahprofil.ListUbahProfil;
import com.myapp.laporankaryawan.ui.tambahlaporanbulanan.TambahLaporanBulanan;
import com.myapp.laporankaryawan.ui.tambahlaporanharian.TambahLaporanHarian;

public class HomeKaryawan extends BaseKaryawanFragment {

    private HomeKaryawanViewModel mViewModel;
    private HomeKaryawanFragmentBinding binding;

    public static HomeKaryawan newInstance() {
        return new HomeKaryawan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_karyawan_fragment, container, false);
        binding.setClick(homePageCallback);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(HomeKaryawanViewModel.class);
        // TODO: Use the ViewModel
    }

    private HomePageCallback homePageCallback = new HomePageCallback() {
        @Override
        public void onHarian() {
            replaceFragment(TambahLaporanHarian.newInstance(), null);
        }

        @Override
        public void onBulanan() {
            replaceFragment(TambahLaporanBulanan.newInstance(), null);
        }

        @Override
        public void onAllHarian() {
            LaporanHarian laporanHarian = new LaporanHarian();
            replaceFragment(laporanHarian, null);
        }

        @Override
        public void onAllBulanan() {
            LaporanBulanan laporanBulanan = new LaporanBulanan();
            replaceFragment(laporanBulanan, null);
        }

        @Override
        public void onEditProfil() {
            replaceFragment(ListUbahProfil.newInstance(), null);
        }

        @Override
        public void onSignOut() {
            MyUser.getInstance(getContext()).signOut();
            signOut();
        }
    };

}
