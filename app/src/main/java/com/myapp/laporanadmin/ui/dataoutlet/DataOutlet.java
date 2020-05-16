package com.myapp.laporanadmin.ui.dataoutlet;

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
import com.myapp.databinding.DataOutletFragmentBinding;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.datakota.DataKotaFactory;

import java.util.List;

public class DataOutlet extends Fragment {
    public static String TAG = "Data Outlet";
    private DataOutletViewModel mViewModel;
    private DataOutletFragmentBinding binding;
    private AdapterDataOutlet adapterDataOutlet;

    public static DataOutlet newInstance() {
        return new DataOutlet();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_outlet_fragment, container, false);
        binding.setIsLoading(true);
        adapterDataOutlet = new AdapterDataOutlet(adapterItemClicked);
        binding.rv.setAdapter(adapterDataOutlet);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataOutletFactory(getContext())).get(DataOutletViewModel.class);
        mViewModel.init();
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataOutletViewModel mViewModel) {
        mViewModel.getOutletData().observe(getViewLifecycleOwner(), new Observer<List<OutletObject>>() {
            @Override
            public void onChanged(List<OutletObject> outletObjects) {
                if(outletObjects != null){
                    binding.setIsLoading(false);
                    adapterDataOutlet.setData(outletObjects);
                    adapterDataOutlet.notifyDataSetChanged();
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
