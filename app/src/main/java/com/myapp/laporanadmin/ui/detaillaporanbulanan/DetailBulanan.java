package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.R;
import com.myapp.databinding.DetailBulananFragmentBinding;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.BaseFragment;

public class DetailBulanan extends BaseFragment {

    private DetailBulananViewModel mViewModel;
    private DetailBulananFragmentBinding binding;
    private LaporanBulananObject laporanBulananObject;

    public DetailBulanan(LaporanBulananObject obj) {
        this.laporanBulananObject = obj;
    }

    public static DetailBulanan newInstance(LaporanBulananObject obj) {
        return new DetailBulanan(obj);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.detail_bulanan_fragment, container, false);
        setActionBar(binding.toolbar,"Laporan Bulanan "+laporanBulananObject.getNamaUser(),"");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(),new DetailBulananFactory(getContext(),laporanBulananObject)).get(DetailBulananViewModel.class);
        binding.setIsLoading(true);
        return binding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailBulananViewModel mViewModel) {
        mViewModel.getLaporanBulananObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanBulananObject>() {
            @Override
            public void onChanged(LaporanBulananObject laporanBulananObject) {
                if(laporanBulananObject != null){

                }
            }
        });
    }
}
