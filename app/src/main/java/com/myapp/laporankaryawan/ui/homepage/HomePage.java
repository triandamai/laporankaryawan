package com.myapp.laporankaryawan.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.myapp.domain.realmobject.HomePageObject;
import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.HomePageItemClicked;
import com.myapp.laporankaryawan.databinding.HomePageFragmentBinding;
import com.myapp.laporankaryawan.ui.laporanbulanan.DataPegawai;
import com.myapp.laporankaryawan.ui.laporanharian.LaporanHarian;
import com.myapp.laporankaryawan.ui.tambahkota.TambahKota;
import com.myapp.laporankaryawan.ui.tambahoutlet.TambahOutlet;
import com.myapp.laporankaryawan.ui.tambahuser.TambahUser;

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
      binding.setIsLoading(true);
      binding.setIsNotifikasiBulanan(false);
      binding.setIsNotifikasiBulanan(false);
      binding.setLitener(refreshListener);
      binding.setClick(homePageItemClicked);

      return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new HomePageFactory(getContext())).get(HomePageViewModel.class);
        mViewModel.init();

        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();

        observe(mViewModel);
    }

    private void observe(HomePageViewModel mViewModel) {
        mViewModel.getHomePageModelLiveData().observe(getViewLifecycleOwner(), new Observer<HomePageObject>() {
            @Override
            public void onChanged(HomePageObject homePageObject) {


                    binding.setIsLoading(false);
//                    Log.e(TAG, homePageObject.toString());
                    int bulanan = Integer.parseInt(homePageObject.getLapMasukBulanan().toString());
                    int harian = Integer.parseInt(homePageObject.getLapMasukHarian().toString());
                    if(bulanan >= 1){
                        binding.setIsNotifikasiBulanan(true);
                    }
                    if (harian >= 1){
                        binding.setIsNotifikasiHarian(true);
                    }

                    binding.setOverview(homePageObject);
               
            }
        });


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
            replaceFragment(LaporanHarian.newInstance(),null);
        }

        @Override
        public void Bulanan(View v) {
            replaceFragment(LaporanHarian.newInstance(),null);
        }

        @Override
        public void LogOut(View v) {
            replaceFragment(LaporanHarian.newInstance(),null);
        }

        @Override
        public void Notifikasi(View v) {
            replaceFragment(LaporanHarian.newInstance(),null);
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
           observe(mViewModel);
        }
    };
}
