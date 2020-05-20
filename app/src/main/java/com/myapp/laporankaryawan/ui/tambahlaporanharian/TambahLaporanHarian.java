package com.myapp.laporankaryawan.ui.tambahlaporanharian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.TambahLaporanHarianFragmentBinding;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;


public class TambahLaporanHarian extends BaseKaryawanFragment {

    private TambahLaporanHarianViewModel mViewModel;

    private TambahLaporanHarianFragmentBinding binding;

    public static TambahLaporanHarian newInstance() {
        return new TambahLaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_laporan_harian_fragment, container, false);
        setActionBar(binding.toolbar, "Tambah Laporan Harian", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext())).get(TambahLaporanHarianViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbarformnav, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
