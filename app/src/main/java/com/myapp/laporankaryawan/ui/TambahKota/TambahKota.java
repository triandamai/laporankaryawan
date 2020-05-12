package com.myapp.laporankaryawan.ui.TambahKota;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.R;

public class TambahKota extends Fragment {
    public static String TAG = "Tambah Kota";
    private TambahKotaViewModel mViewModel;

    public static TambahKota newInstance() {
        return new TambahKota();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambah_kota_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new TambahKotaFactory(getContext())).get(TambahKotaViewModel.class);
        // TODO: Use the ViewModel
    }

}
