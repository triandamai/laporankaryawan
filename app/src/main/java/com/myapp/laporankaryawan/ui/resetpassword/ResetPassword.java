package com.myapp.laporankaryawan.ui.resetpassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.ResetPasswordFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;


public class ResetPassword extends BaseKaryawanFragment {

    private ResetPasswordViewModel mViewModel;
    private ResetPasswordFragmentBinding binding;

    public static ResetPassword newInstance() {
        return new ResetPassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.reset_password_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(ResetPasswordViewModel.class);
        return binding.getRoot();
    }


}
