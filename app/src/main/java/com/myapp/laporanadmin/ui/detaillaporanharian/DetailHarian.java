package com.myapp.laporanadmin.ui.detaillaporanharian;

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
import com.myapp.databinding.DetailHarianFragmentBinding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.BaseFragment;

public class DetailHarian extends BaseFragment {

    private DetailHarianViewModel mViewModel;
    private DetailHarianFragmentBinding binding;
    private LaporanHarianObject laporanHarianObject;

    public DetailHarian(LaporanHarianObject obj) {
        this.laporanHarianObject = obj;
    }

    public static DetailHarian newInstance(LaporanHarianObject obj) {
        return new DetailHarian(obj);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.detail_harian_fragment, container, false);
        setActionBar(binding.toolbar,"Laporan Bulanan "+laporanHarianObject.getNamaUser(),"");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(),new DetailHarianFactory(getContext(),laporanHarianObject)).get(DetailHarianViewModel.class);
       binding.setIsLoading(true);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }
    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailHarianViewModel mViewModel) {
        mViewModel.getLaporanHarianObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanHarianObject>() {
            @Override
            public void onChanged(LaporanHarianObject laporanHarianObject) {
                if(laporanHarianObject != null){
                    binding.setData(laporanHarianObject);
                }
            }
        });
    }
}
