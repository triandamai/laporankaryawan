package com.myapp.laporanadmin.ui.laporanbulanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.R;
import com.myapp.databinding.LaporanBulananFragmentBinding;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.detaillaporanbulanan.DetailBulananAdmin;

public class LaporanBulanan extends BaseAdminFragment {
    public static String TAG = "LaporanHarianObject";
    private LaporanBulananViewModel mViewModel;
    private LaporanBulananFragmentBinding binding;
    private AdapterLaporanBulanan adapterLaporanBulanan;
    private boolean isNotif = false;

    public LaporanBulanan() {

    }

    public static LaporanBulanan newInstance() {
        return new LaporanBulanan();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_bulanan_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar, "Laporan Bulanan", "");
        binding.setIsLoading(true);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        Bundle bundle = getArguments();
        if (bundle != null) {
            isNotif = bundle.getBoolean("isNotif");
        }
        adapterLaporanBulanan = new AdapterLaporanBulanan(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanBulanan);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new LaporanBulananFactory(getContext(), getRequestBulanan())).get(LaporanBulananViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isNotif) {
            mViewModel.fetchFromApi(getRequestBulananAll());
        } else {
            mViewModel.fetchFromApi(getRequestBulanan());
        }
        observe(mViewModel);
    }

    private void observe(LaporanBulananViewModel mViewModel) {
        if (isNotif) {
            mViewModel.initNotifikasi().observe(getViewLifecycleOwner(), laporanBulananObjects -> {
                binding.setIsLoading(false);
                if (laporanBulananObjects.size() >= 1) {

                    adapterLaporanBulanan.setData(laporanBulananObjects);
                    adapterLaporanBulanan.notifyDataSetChanged();
                }
            });
        } else {
            mViewModel.init().observe(getViewLifecycleOwner(), laporanBulananObjects -> {
                binding.setIsLoading(false);
                if (laporanBulananObjects.size() >= 1) {

                    adapterLaporanBulanan.setData(laporanBulananObjects);
                    adapterLaporanBulanan.notifyDataSetChanged();
                }
            });
        }

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

            LaporanBulananObject obj = adapterLaporanBulanan.getFromPosition(pos);

            Bundle bundle = new Bundle();
            bundle.putString("idlaporanbulanan", obj.getIdLaporanbulanan());
            bundle.putString("statuslaporanbulanan", obj.getStatusLaporanbulanan());
            DetailBulananAdmin bulanan = new DetailBulananAdmin();
            bulanan.setArguments(bundle);
            replaceFragment(bulanan, null);
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            if (isNotif) {
                mViewModel.fetchFromApi(getRequestBulananAll());
            } else {
                mViewModel.fetchFromApi(getRequestBulanan());
            }
            binding.setIsLoading(false);
        }
    };
}
