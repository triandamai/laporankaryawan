package com.myapp.laporankaryawan.ui.listubahprofil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.ListUbahProfilFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.callback.ListKaryawanListener;
import com.myapp.laporankaryawan.ui.resetpassword.ResetPassword;
import com.myapp.laporankaryawan.ui.ubahprofil.UbahProfil;


public class ListUbahProfil extends BaseKaryawanFragment {

    private ListUbahProfilViewModel mViewModel;
    private ListUbahProfilFragmentBinding binding;

    public static ListUbahProfil newInstance() {
        return new ListUbahProfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_ubah_profil_fragment, container, false);
        binding.setEvent(listRekapan);
        mViewModel = new ViewModelProvider(getActivity(), new KaryawanFactory(getContext())).get(ListUbahProfilViewModel.class);

        return binding.getRoot();
    }


    private ListKaryawanListener listRekapan = new ListKaryawanListener() {
        @Override
        public void ubahPassword() {
            replaceFragment(ResetPassword.newInstance(), null);
        }

        @Override
        public void ubahProfil() {
            replaceFragment(UbahProfil.newInstance(), null);
        }
    };

}