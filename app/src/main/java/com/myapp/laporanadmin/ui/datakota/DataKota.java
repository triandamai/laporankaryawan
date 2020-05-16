package com.myapp.laporanadmin.ui.datakota;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.R;
import com.myapp.databinding.DataKotaFragmentBinding;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;

import java.util.List;

public class DataKota extends Fragment {
    public static String TAG = "Data Kota";
    private DataKotaViewModel mViewModel;
    private DataKotaFragmentBinding binding;
    private AdapterDataKota adapterDataKota;

    public static DataKota newInstance() {
        return new DataKota();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.data_kota_fragment, container, false);
       binding.setIsLoading(true);
       adapterDataKota = new AdapterDataKota(adapterItemClicked);
       binding.rv.setAdapter(adapterDataKota);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataKotaFactory(getContext())).get(DataKotaViewModel.class);
        mViewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataKotaViewModel mViewModel) {
        mViewModel.getListKota().observe(getViewLifecycleOwner(), new Observer<List<KotaObject>>() {
            @Override
            public void onChanged(List<KotaObject> kotaObjects) {
                if(kotaObjects != null){
                    binding.setIsLoading(false);
                    adapterDataKota.setData(kotaObjects);
                    adapterDataKota.notifyDataSetChanged();
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
