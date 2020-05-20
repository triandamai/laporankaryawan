package com.myapp.laporankaryawan.ui.ubahprofil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.UbahProfilFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;

public class UbahProfil extends BaseKaryawanFragment {

    private UbahProfilViewModel mViewModel;
    private UbahProfilFragmentBinding binding;

    public static UbahProfil newInstance() {
        return new UbahProfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.ubah_profil_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(UbahProfilViewModel.class);
        return binding.getRoot();
    }


}
