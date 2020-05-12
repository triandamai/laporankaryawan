package com.myapp.laporankaryawan.ui.TambahOutlet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.R;

public class TambahOutlet extends Fragment {
    public static String TAG = "Tambah Outlet";
    private TambahOutletViewModel mViewModel;

    public static TambahOutlet newInstance() {
        return new TambahOutlet();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambah_outlet_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new TambahOutletFactory(getContext())).get(TambahOutletViewModel.class);
        // TODO: Use the ViewModel
    }

}
