package com.myapp.laporankaryawan.ui.laporanharian;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.myapp.R;
import com.myapp.databinding.LaporanHarianFragment2Binding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.KaryawanFactory;
import com.myapp.laporankaryawan.ui.detaillaporanhariankaryawan.DetailHarian;

public class LaporanHarian extends BaseKaryawanFragment {

    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragment2Binding binding;
    private AdapterLaporanHarian adapterLaporanHarian;

    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_harian_fragment2, container, false);
        setActionBar(binding.toolbar, "Laporan Harian", "");
        setHasOptionsMenu(true);
        binding.setIsLoading(false);
        binding.setListener(refreshListener);
        adapterLaporanHarian = new AdapterLaporanHarian(itemClicked);
        binding.rv.setAdapter(adapterLaporanHarian);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new KaryawanFactory(getContext(), getRequestHarian())).get(LaporanHarianViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchFromApi(getRequestHarian());

        observe(mViewModel);
    }

    private void observe(LaporanHarianViewModel mViewModel) {
        mViewModel.init().observe(getViewLifecycleOwner(), laporanHarianObjects -> {
            if (laporanHarianObjects != null) {
                adapterLaporanHarian.setData(laporanHarianObjects);
            }
        });
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
            LaporanHarianObject obj = adapterLaporanHarian.getFromPosition(pos);
            Bundle bundle = new Bundle();
            bundle.putString("idlaporanharian", obj.getIdLaporanharian());
            bundle.putString("statuslaporanharian", obj.getStatusLaporanharian());
            DetailHarian detailHarian = new DetailHarian();
            detailHarian.setArguments(bundle);
            replaceFragment(detailHarian, null);
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
