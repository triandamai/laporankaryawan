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
import com.myapp.domain.serialize.req.RequestUbahPassword;
import com.myapp.laporanadmin.callback.SendDataListener;
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
        setActionBar(binding.toolbar, "Ubah Password", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(ResetPasswordViewModel.class);
        mViewModel.setListener(sendDataListener);
        mViewModel.repass.setValue("");
        RequestUbahPassword requestUbahPassword = new RequestUbahPassword();
        requestUbahPassword.setIdUser(0);
        requestUbahPassword.setPasswordBaru("");
        requestUbahPassword.setPasswordLama("");
        mViewModel.req.setValue(requestUbahPassword);
        binding.setIsLoading(false);
        binding.setVm(mViewModel);
        return binding.getRoot();
    }

    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String message) {
            binding.setIsLoading(false);
            builder.setCancelable(false);
            builder.setTitle("Infor");
            builder.setMessage(message);
            builder.setNeutralButton("Oke", (dialog, which) -> {
                dialog.dismiss();
                back();
            });
            builder.show();
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }
    };

}
