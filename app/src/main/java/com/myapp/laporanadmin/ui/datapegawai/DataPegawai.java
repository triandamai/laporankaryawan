package com.myapp.laporanadmin.ui.datapegawai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.R;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.databinding.DataPegawaiFragmentBinding;

import java.util.List;

public class DataPegawai extends BaseFragment {
    public static String TAG = "Data Pegawai";
    private DataPegawaiViewModel mViewModel;
    private DataPegawaiFragmentBinding binding;
    private AdapterDataPegawai adapterDataPegawai;

    public static DataPegawai newInstance() {
        return new DataPegawai();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_pegawai_fragment, container, false);
        setActionBar(binding.toolbar,"Data Pegawai","");
        binding.setIsLoading(true);
        adapterDataPegawai = new AdapterDataPegawai(adapterItemClicked);
        binding.rv.setAdapter(adapterDataPegawai);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataPegawaiFactory(getContext())).get(DataPegawaiViewModel.class);
        mViewModel.init();
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataPegawaiViewModel mViewModel) {
        mViewModel.getKaryawanData().observe(getViewLifecycleOwner(), new Observer<List<KaryawanObject>>() {
            @Override
            public void onChanged(List<KaryawanObject> karyawanObjects) {
//                Log.e(TAG,karyawanObjects.toString());
                binding.setIsLoading(false);
                adapterDataPegawai.setData(karyawanObjects);
                adapterDataPegawai.notifyDataSetChanged();
            }
        });
    }
    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {
            KaryawanObject data = adapterDataPegawai.getFromPosition(pos);
        }

        @Override
        public void onDetail(int pos) {

        }
    };
}
