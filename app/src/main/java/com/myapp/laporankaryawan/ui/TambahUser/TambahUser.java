package com.myapp.laporankaryawan.ui.TambahUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;

public class TambahUser extends BaseFragment {
    public static String TAG = "Tambah User Fragment";
    private TambahUserViewModel mViewModel;

    public static TambahUser newInstance() {
        return new TambahUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambah_user_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new TambahUserFactory(getContext()))
                .get(TambahUserViewModel.class);
        // TODO: Use the ViewModel
    }

}
