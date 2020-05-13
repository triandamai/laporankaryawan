package com.myapp.laporankaryawan.ui.laporanharian;

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
import com.myapp.laporankaryawan.databinding.LaporanHarianFragmentBinding;

public class LaporanHarian extends BaseFragment {
    public static String TAG = "LaporanObject";
    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragmentBinding binding;

    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater,R.layout.laporan_harian_fragment, container, false);
         return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new LaporanHarianFactory(getContext())).get(LaporanHarianViewModel.class);
        // TODO: Use the ViewModel
    }

}
