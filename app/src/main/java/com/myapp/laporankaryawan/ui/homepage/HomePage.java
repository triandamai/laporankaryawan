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

        }

        @Override
        public void tambahKota(View v) {

        }

        @Override
        public void tambahOutlet(View v) {

        }

        @Override
        public void pegawai(View v) {

        }

        @Override
        public void harian(View v) {

        }

        @Override
        public void bulanan(View v) {

        }

        @Override
        public void logout(View v) {

        }

        @Override
        public void notifikasi(View v) {

        }
    };
}
