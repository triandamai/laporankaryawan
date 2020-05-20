package com.myapp.laporankaryawan.ui.laporanbulanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.myapp.R;
import com.myapp.databinding.LaporanBulananFragment2Binding;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;

public class LaporanBulanan extends BaseKaryawanFragment {

    private LaporanBulananViewModel mViewModel;
    private LaporanBulananFragment2Binding binding;
    private AdapterLaporanBulanan adapterLaporanBulanan;

    public static LaporanBulanan newInstance() {
        return new LaporanBulanan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_bulanan_fragment2, container, false);
        binding.setIsLoading(true);
        adapterLaporanBulanan = new AdapterLaporanBulanan(adapterItemClicked);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar, "Laporan Bulanan Anda", "");
        binding.setListener(refreshListener);
        binding.rv.setAdapter(adapterLaporanBulanan);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext(), getRequestBulanan())).get(LaporanBulananViewModel.class);
        // TODO: Use the ViewModel
    }

    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onEdit(int pos) {

        }

        @Override
        public void onDelete(int pos) {

        }

        @Override
        public void onDetail(int pos) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(LaporanBulananViewModel mViewModel) {
        mViewModel.init().observe(getViewLifecycleOwner(), laporanBulananObjects -> {
            binding.setIsLoading(false);
            if (laporanBulananObjects != null) {

                adapterLaporanBulanan.setData(laporanBulananObjects);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        mViewModel.fetchFromApi(getRequestBulanan());
        mViewModel.init();
        binding.setIsLoading(false);
    };
}
