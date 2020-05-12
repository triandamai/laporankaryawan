package com.myapp.laporankaryawan.ui.DataPegawai;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.databinding.DataPegawaiFragmentBinding;

public class DataPegawai extends Fragment {

    private DataPegawaiViewModel mViewModel;
    private DataPegawaiFragmentBinding binding;

    public static DataPegawai newInstance() {
        return new DataPegawai();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_pegawai_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataPegawaiFactory(getContext())).get(DataPegawaiViewModel.class);
        // TODO: Use the ViewModel
    }

}
