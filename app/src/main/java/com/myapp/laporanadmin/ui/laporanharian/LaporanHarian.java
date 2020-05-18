package com.myapp.laporanadmin.ui.laporanharian;

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
import com.myapp.databinding.LaporanHarianFragmentBinding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.detaillaporanharian.DetailHarian;

;

public class LaporanHarian extends BaseFragment {
    public static String TAG = "LaporanHarianObject";
    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragmentBinding binding;
    private AdapterLaporanHarian adapterLaporanHarian;
    private boolean isNotif = false;

    public LaporanHarian() {

    }

    public LaporanHarian(boolean p) {
        super();
        this.isNotif = p;
    }

    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }

    public static LaporanHarian newInstance(boolean isNotif) {
        return new LaporanHarian(isNotif);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.laporan_harian_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar, "Laporan Harian", "");
        binding.setIsLoading(true);
        adapterLaporanHarian = new AdapterLaporanHarian(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanHarian);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity(), new LaporanHarianFactory(getContext(), getRequestHarian())).get(LaporanHarianViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchFromApi(getRequestHarian());
        if (isNotif) {
            mViewModel.initnotifikasi();
        } else {
            mViewModel.init();
        }
        observe(mViewModel);

    }

    private void observe(LaporanHarianViewModel mViewModel) {
        mViewModel.getListLiveData().observe(getViewLifecycleOwner(), laporanHarianObjects -> {
            binding.setIsLoading(false);
            if (laporanHarianObjects.size() >= 1) {
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
            bundle.putString("idlaporanharian",obj.getIdLaporanharian());
            bundle.putString("statuslaporanharian",obj.getStatusLaporanharian());
            DetailHarian detailHarian = new DetailHarian();
            detailHarian.setArguments(bundle);
            replaceFragment(detailHarian, null);
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.fetchFromApi(getRequestHarian());
        }
    };

}
