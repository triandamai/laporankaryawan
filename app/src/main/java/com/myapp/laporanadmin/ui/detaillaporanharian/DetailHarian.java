package com.myapp.laporanadmin.ui.detaillaporanharian;

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
import com.myapp.databinding.DetailHarianFragmentBinding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;

public class DetailHarian extends BaseFragment {

    private DetailHarianViewModel mViewModel;
    private DetailHarianFragmentBinding binding;
    private LaporanHarianObject laporanHarianObject;

    public DetailHarian(LaporanHarianObject obj) {
        this.laporanHarianObject = obj;
    }

    public static DetailHarian newInstance(LaporanHarianObject obj) {
        return new DetailHarian(obj);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_harian_fragment, container, false);
        setActionBar(binding.toolbar, "Laporan Harian " + laporanHarianObject.getNamaUser(), "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(), new DetailHarianFactory(getContext(), laporanHarianObject)).get(DetailHarianViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        binding.setVm(mViewModel);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailHarianViewModel mViewModel) {
        mViewModel.getLaporanHarianObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanHarianObject>() {
            @Override
            public void onChanged(LaporanHarianObject laporanHarianObject) {
                if (laporanHarianObject != null) {
                    binding.setData(laporanHarianObject);
                    if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("1")) {
                        binding.setISrejected(false);
                    } else if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("2")) {
                        binding.setISrejected(true);
                    } else if (laporanHarianObject.getStatusLaporanharian().equalsIgnoreCase("3")) {
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
