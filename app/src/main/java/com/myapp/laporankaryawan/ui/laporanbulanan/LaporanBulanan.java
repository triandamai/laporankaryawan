package com.myapp.laporankaryawan.ui.laporanbulanan;

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
import com.myapp.laporankaryawan.databinding.LaporanBulananFragmentBinding;

public class LaporanBulanan extends BaseFragment {
    public static String TAG = "LaporanObject";
    private LaporanBulananViewModel mViewModel;
    private LaporanBulananFragmentBinding binding;

    public static LaporanBulanan newInstance() {
        return new LaporanBulanan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater,R.layout.laporan_bulanan_fragment, container, false);
         return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new LaporanBulananFactory(getContext())).get(LaporanBulananViewModel.class);
        // TODO: Use the ViewModel
    }

}
