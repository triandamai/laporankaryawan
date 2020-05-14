package com.myapp.laporankaryawan.ui.laporanharian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanRequestData;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.databinding.LaporanHarianFragmentBinding;

import java.util.Calendar;
import java.util.List;

public class LaporanHarian extends BaseFragment {
    public static String TAG = "LaporanHarianObject";
    private LaporanHarianViewModel mViewModel;
    private LaporanHarianFragmentBinding binding;
    private AdapterLaporanHarian adapterLaporanHarian;

    public static LaporanHarian newInstance() {
        return new LaporanHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater,R.layout.laporan_harian_fragment, container, false);
         binding.setIsLoading(true);
         adapterLaporanHarian = new AdapterLaporanHarian(adapterItemClicked);
         binding.rv.setAdapter(adapterLaporanHarian);

         return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        LaporanRequestData l = new LaporanRequestData();
        l.setBulanLaporanharian(month+1);
        l.setTahunLaporanharian(year);

        mViewModel = new ViewModelProvider(requireActivity(),new LaporanHarianFactory(getContext(),l)).get(LaporanHarianViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.init();
        observe(mViewModel);
    }

    private void observe(LaporanHarianViewModel mViewModel) {
        mViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<List<LaporanHarianObject>>() {
            @Override
            public void onChanged(List<LaporanHarianObject> laporanHarianObjects) {
                if (laporanHarianObjects.size() >= 1){
                    binding.setIsLoading(false);
                    adapterLaporanHarian.setData(laporanHarianObjects);
                    adapterLaporanHarian.notifyDataSetChanged();
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
