package com.myapp.laporanadmin.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.HomePageAdminFragmentBinding;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.HomePageItemClicked;
import com.myapp.laporanadmin.ui.datakota.DataKota;
import com.myapp.laporanadmin.ui.dataoutlet.DataOutlet;
import com.myapp.laporanadmin.ui.datapegawai.DataPegawai;
import com.myapp.laporanadmin.ui.laporanbulanan.LaporanBulanan;
import com.myapp.laporanadmin.ui.laporanharian.LaporanHarian;
import com.myapp.laporanadmin.ui.rekapan.ListRekapan;
import com.myapp.laporanadmin.ui.tambahuser.TambahUser;

public class HomePage extends BaseFragment {
    public static String TAG = "Home Page Fragment";

    private HomePageViewModel mViewModel;
    private HomePageAdminFragmentBinding binding;
    private MaterialAlertDialogBuilder builder;

    public static HomePage newInstance() {
        return new HomePage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_page_admin_fragment, container, false);
        binding.setIsLoading(true);
        binding.setIsNotifikasiBulanan(false);
        binding.setIsNotifikasiBulanan(false);
        binding.setListener(refreshListener);
        binding.setClick(homePageItemClicked);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new HomePageFactory(getContext())).get(HomePageViewModel.class);


        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchFromApi();
        mViewModel.init();
        observe(mViewModel);
    }

    private void observe(HomePageViewModel mViewModel) {
        mViewModel.getHomePageModelLiveData().observe(getViewLifecycleOwner(), homePageObject -> {

            if (homePageObject != null) {
                binding.setIsLoading(false);
                int bulanan = Integer.parseInt(homePageObject.getLapMasukBulanan().toString());
                int harian = Integer.parseInt(homePageObject.getLapMasukHarian().toString());
                if (bulanan == 0 || homePageObject.getLapMasukBulanan() == null) {
                    binding.setIsNotifikasiBulanan(false);
                } else {
                    binding.setIsNotifikasiBulanan(true);
                }
                if (harian == 0 || homePageObject.getLapMasukHarian() == null) {
                    binding.setIsNotifikasiHarian(false);
                } else {
                    binding.setIsNotifikasiHarian(true);
                }

                binding.setOverview(homePageObject);
            }
        });


    }

    private void keluar() {

        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        builder.setTitle("Hi..");
        builder.setMessage("Yakin Mau Keluar ?");
        builder.setPositiveButton("Keluar", (dialog, which) -> {
            MyUser.getInstance(getContext()).signOut();
            signOut();
        });
        builder.setNegativeButton("Batal", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private HomePageItemClicked homePageItemClicked = new HomePageItemClicked() {
        @Override
        public void dataUser(View v) {
            replaceFragment(TambahUser.newInstance(), null);
        }

        @Override
        public void dataKota(View v) {
            replaceFragment(DataKota.newInstance(), DataOutlet.TAG);
        }

        @Override
        public void dataOutlet(View v) {
            replaceFragment(DataOutlet.newInstance(), DataOutlet.TAG);
        }

        @Override
        public void Pegawai(View v) {
            replaceFragment(DataPegawai.newInstance(), DataOutlet.TAG);
        }

        @Override
        public void Harian(View v) {
            LaporanHarian laporanHarian = new LaporanHarian();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNotif", false);
            laporanHarian.setArguments(bundle);
            replaceFragment(laporanHarian, null);
        }

        @Override
        public void Bulanan(View v) {
            LaporanBulanan laporanBulanan = new LaporanBulanan();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNotif", false);
            laporanBulanan.setArguments(bundle);
            replaceFragment(laporanBulanan, null);
        }

        @Override
        public void LogOut(View v) {
            keluar();
        }

        @Override
        public void notifikasiBulanan(View v) {
            LaporanBulanan laporanBulanan = new LaporanBulanan();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNotif", true);
            laporanBulanan.setArguments(bundle);
            replaceFragment(laporanBulanan, null);

        }

        @Override
        public void notifikasiHarian(View v) {
            LaporanHarian laporanHarian = new LaporanHarian();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNotif", true);
            laporanHarian.setArguments(bundle);
            replaceFragment(laporanHarian, null);

        }

        @Override
        public void RekapLaporan(View v) {
            replaceFragment(ListRekapan.newInstance(), null);
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.fetchFromApi();
        }
    };
}
