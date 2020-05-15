package com.myapp.laporanadmin.ui.laporanbulanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.R;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.databinding.LaporanBulananFragmentBinding;

import java.util.Calendar;
import java.util.List;

public class LaporanBulanan extends BaseFragment {
    public static String TAG = "LaporanHarianObject";
    private LaporanBulananViewModel mViewModel;
    private LaporanBulananFragmentBinding binding;
    private AdapterLaporanBulanan adapterLaporanBulanan;
    public static LaporanBulanan newInstance() {
        return new LaporanBulanan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater,R.layout.laporan_bulanan_fragment, container, false);
         binding.setIsLoading(true);
         adapterLaporanBulanan = new AdapterLaporanBulanan(adapterItemClicked);
         binding.rv.setAdapter(adapterLaporanBulanan);
         return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        LaporanBulananRequestData l = new LaporanBulananRequestData();
        l.setBulanLaporanbulanan(month+1);
        l.setTahunLaporanbulanan(year);
        mViewModel = new ViewModelProvider(requireActivity(),new LaporanBulananFactory(getContext(),l)).get(LaporanBulananViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.init();
        observe(mViewModel);
    }

    private void observe(LaporanBulananViewModel mViewModel) {
        mViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<List<LaporanBulananObject>>() {
            @Override
            public void onChanged(List<LaporanBulananObject> laporanBulananObjects) {
                if (laporanBulananObjects.size() >= 1){
                    binding.setIsLoading(false);
                    adapterLaporanBulanan.setData(laporanBulananObjects);
                    adapterLaporanBulanan.notifyDataSetChanged();
                }
            }
        });
    }
    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onDetail(int pos) {

        }
    };
}
