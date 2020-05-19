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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.R;
import com.myapp.databinding.DetailHarianFragmentBinding;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;

public class DetailHarian extends BaseFragment {

    private DetailHarianViewModel mViewModel;
    private DetailHarianFragmentBinding binding;
    private String id;

    public static DetailHarian newInstance() {
        return new DetailHarian();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_harian_fragment, container, false);

        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(getActivity(), new DetailHarianFactory(getContext())).get(DetailHarianViewModel.class);
        mViewModel.setSendDataListener(sendDataListener);
        binding.setVm(mViewModel);
        builder = new MaterialAlertDialogBuilder(getContext(), R.style.dialog);
        builder.create();
        Bundle bundle = getArguments();
        if (bundle != null){

            this.id = bundle.getString("idlaporanharian");

            String status = bundle.getString("statuslaporanharian");
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

            setActionBar(binding.toolbar, "Laporan Harian " , "");
        }else {

        }
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailHarianViewModel mViewModel) {
        mViewModel.getObject(id);
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
}
