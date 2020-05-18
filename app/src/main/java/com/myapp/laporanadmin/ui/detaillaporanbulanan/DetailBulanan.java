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

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.myapp.R;
import com.myapp.databinding.DetailBulananFragmentBinding;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.ui.laporanbulanan.LaporanBulanan;

public class DetailBulanan extends BaseFragment {

    private DetailBulananViewModel mViewModel;
    private DetailBulananFragmentBinding binding;
    private String id = "";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_bulanan_fragment, container, false);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(), new DetailBulananFactory(getContext())).get(DetailBulananViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        binding.setVm(mViewModel);

        Bundle bundle = getArguments();
        if (bundle != null){

           this.id = bundle.getString("idlaporanbulanan");

           String status = bundle.getString("statuslaporanbulanan");
            if(status.equalsIgnoreCase("1")){
                binding.setIsEditable(true);

                binding.setISrejected(false);
            }else if(status.equalsIgnoreCase("2")){
                binding.setIsEditable(false);

                binding.setISrejected(false);
            }else {
                binding.setIsEditable(false);

                binding.setISrejected(true);
            }

            setActionBar(binding.toolbar, "Laporan Bulanan " , "");
        }else {

        }





        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailBulananViewModel mViewModel) {
        mViewModel.getObject(id);
        mViewModel.getLaporanBulananObjectLiveData().observe(getViewLifecycleOwner(), new Observer<LaporanBulananObject>() {
            @Override
            public void onChanged(LaporanBulananObject laporanBulananObject) {
                if (laporanBulananObject != null) {
                    binding.setData(laporanBulananObject);
                    if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("1")) {
                        binding.setISrejected(false);
                    } else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("2")) {
                        binding.setISrejected(true);
                    } else if (laporanBulananObject.getStatusLaporanbulanan().equalsIgnoreCase("3")) {
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

            builder.setTitle("Info");
            builder.setMessage(message);
            builder.setPositiveButton("Oke", (dialog, which) ->{
                dialog.dismiss();
                back();
            });
            builder.show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
