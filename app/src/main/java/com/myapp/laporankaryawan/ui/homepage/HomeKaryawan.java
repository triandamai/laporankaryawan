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
import com.myapp.databinding.HomeKaryawanFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.callback.HomePageCallback;

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

        }

        @Override
        public void onBulanan() {

        }

        @Override
        public void onAllHarian() {

        }

        @Override
        public void onAllBulanan() {

        }

        @Override
        public void onEditProfil() {

        }

        @Override
        public void onSignOut() {

        }
    };

}
