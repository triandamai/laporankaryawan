package com.myapp.laporanadmin.ui.laporanharian;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.myapp.R;
import com.myapp.databinding.LaporanHarianFragmentBinding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.detaillaporanharian.DetailHarianAdmin;

;

public class LaporanHarian extends BaseAdminFragment {
    public static String TAG = "LaporanHarianObject";
    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragmentBinding binding;
    private AdapterLaporanHarian adapterLaporanHarian;
    private boolean isNotif = false;

    public LaporanHarian() {

    }


    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_harian_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar, "Laporan Harian", "");
        binding.setIsLoading(true);
        Bundle bundle = getArguments();

        isNotif = bundle.getBoolean("isNotif");
        Log.e("isNotif", String.valueOf(bundle.getBoolean("isNotif")));
        adapterLaporanHarian = new AdapterLaporanHarian(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanHarian);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity(), new LaporanHarianFactory(getContext(), getRequestHarian())).get(LaporanHarianViewModel.class);
        if (isNotif) {
            mViewModel.fetchFromApi(getRequestHarian());
        } else {
            mViewModel.fetchFromApi(getRequestHarianAll());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setIsLoading(true);
        if (isNotif) {
            mViewModel.fetchFromApi(getRequestHarian());
            observe(mViewModel);
        } else {
            mViewModel.fetchFromApi(getRequestHarianAll());
            observeAll(mViewModel);
        }


    }

    private void observeAll(LaporanHarianViewModel mViewModel) {
        mViewModel.init().observe(getViewLifecycleOwner(), laporanHarianObjects -> {
            if (laporanHarianObjects.size() >= 1) {
                binding.setIsLoading(false);
                adapterLaporanHarian.setData(laporanHarianObjects);
            }
        });
    }

    private void observe(LaporanHarianViewModel mViewModel) {

        mViewModel.initnotifikasi().observe(getViewLifecycleOwner(), laporanHarianObjects -> {
            if (laporanHarianObjects.size() >= 1) {
                binding.setIsLoading(false);
                adapterLaporanHarian.setData(laporanHarianObjects);
            }
        });


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
            LaporanHarianObject obj = adapterLaporanHarian.getFromPosition(pos);
            Bundle bundle = new Bundle();
            bundle.putString("idlaporanharian", obj.getIdLaporanharian());
            bundle.putString("statuslaporanharian", obj.getStatusLaporanharian());
            DetailHarianAdmin detailHarianAdmin = new DetailHarianAdmin();
            detailHarianAdmin.setArguments(bundle);
            replaceFragment(detailHarianAdmin, null);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (isNotif) {
                mViewModel.fetchFromApi(getRequestHarian());
            } else {
                mViewModel.fetchFromApi(getRequestHarianAll());

            }
            binding.setIsLoading(false);
        }
    };

}
