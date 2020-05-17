package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.DetailBulananFragmentBinding;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;

public class DetailBulanan extends BaseFragment {

    private DetailBulananViewModel mViewModel;
    private DetailBulananFragmentBinding binding;
    private LaporanBulananObject laporanBulananObject;

    public DetailBulanan(LaporanBulananObject obj) {
        this.laporanBulananObject = obj;
    }

    public static DetailBulanan newInstance(LaporanBulananObject obj) {
        return new DetailBulanan(obj);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_bulanan_fragment, container, false);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar, "Laporan Bulanan " + laporanBulananObject.getNamaUser(), "");


        mViewModel = new ViewModelProvider(getActivity(), new DetailBulananFactory(getContext(), laporanBulananObject)).get(DetailBulananViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        binding.setVm(mViewModel);
        binding.setData(laporanBulananObject);

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailBulananViewModel mViewModel) {
        mViewModel.getLaporanBulananObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanBulananObject>() {
            @Override
            public void onChanged(LaporanBulananObject laporanBulananObject) {
                if (laporanBulananObject != null) {
                    binding.setData(laporanBulananObject);
                    if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("1")){
                        binding.setISrejected(false);
                    }else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("2")){
                        binding.setISrejected(true);
                    }else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("3")){
                        binding.setISrejected(false);
                    }
                }
            }
        });
    }

    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            showProgress("Memproses...");
        }

        @Override
        public void onSuccess(String message) {
            dismissProgress();
            dialogBerhasil(message);
        }

        @Override
        public void onFailed(String message) {
            dismissProgress();
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            dismissProgress();
            dialogGagal(message);
        }
    };
}
