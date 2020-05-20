package com.myapp.laporankaryawan.ui.laporanharian;

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
import com.myapp.databinding.LaporanHarianFragment2Binding;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.ui.laporanbulanan.AdapterLaporanBulanan;

public class LaporanHarian extends BaseKaryawanFragment {

    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragment2Binding binding;
    private AdapterLaporanBulanan adapterLaporanBulanan;

    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_harian_fragment2, container, false);
        binding.setIsLoading(false);
        binding.setListener(refreshListener);
        adapterLaporanBulanan = new AdapterLaporanBulanan(itemClicked);
        binding.rv.setAdapter(adapterLaporanBulanan);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext(), getRequestHarian())).get(LaporanHarianViewModel.class);

    }

    private AdapterItemClicked itemClicked = new AdapterItemClicked() {
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
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.fetchFromApi(getRequestHarian());
            mViewModel.init();
            binding.setIsLoading(false);
        }
    };


}
