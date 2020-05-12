package com.myapp.laporankaryawan.ui.DataPegawai;

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
import com.myapp.laporankaryawan.databinding.DataPegawaiFragmentBinding;

public class DataPegawai extends BaseFragment {
    public static String TAG = "Data Pegawai";
    private DataPegawaiViewModel mViewModel;
    private DataPegawaiFragmentBinding binding;

    public static DataPegawai newInstance() {
        return new DataPegawai();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_pegawai_fragment, container, false);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataPegawaiFactory(getContext())).get(DataPegawaiViewModel.class);
        // TODO: Use the ViewModel
    }

}
