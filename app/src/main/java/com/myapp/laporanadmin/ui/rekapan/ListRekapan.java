package com.myapp.laporanadmin.ui.rekapan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.R;
import com.myapp.databinding.ListRekapanFragmentBinding;
import com.myapp.laporanadmin.BaseFragment;

public class ListRekapan extends BaseFragment {

    private ListRekapanViewModel mViewModel;
    private ListRekapanFragmentBinding binding;

    public static ListRekapan newInstance() {
        return new ListRekapan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.list_rekapan_fragment, container, false);
       binding.setEvent(new com.myapp.laporanadmin.callback.ListRekapan() {
           @Override
           public void onBulanan() {
               replaceFragment(HalamanPilihRekapanBulanan.newInstance(),null);
           }

           @Override
           public void onHarian() {
                replaceFragment(HalamanPilihRekapanHarian.newInstance(),null);
           }
       });
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListRekapanViewModel.class);
        // TODO: Use the ViewModel
    }

}
