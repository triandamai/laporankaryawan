package com.myapp.laporanadmin.ui.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myapp.R;
import com.myapp.databinding.FragmentSheetKotaBinding;
import com.myapp.domain.realmobject.KotaObject;


import java.util.List;


public class SheetKota extends BottomSheetDialogFragment {
    private FragmentSheetKotaBinding binding;
    private SheetKotaViewModel viewModel;
    private BottomSheetBehavior behavior;
    private AdapterSheetKota adapterSheetKota;
    private BottomSheetListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_sheet_kota, null, false);
        binding.setIsLoading(true);
        adapterSheetKota = new AdapterSheetKota(itemClicked);
        binding.shimmerRecyclerDesa.setAdapter(adapterSheetKota);


        dialog.setContentView(binding.getRoot());
        behavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());


        return dialog;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SheetKotaViewModel.class);
        viewModel.setContext(getContext());
        viewModel.sync();

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setIsLoading(true);
        observe(viewModel);

    }

    private void observe(SheetKotaViewModel viewModel) {
        viewModel.getResponseGetPendudukRtLiveData().observe(this, new Observer<List<KotaObject>>() {
            @Override
            public void onChanged(List<KotaObject> kotaObjects) {
                if(kotaObjects.size() >= 1){
                    binding.setIsLoading(false);
                    adapterSheetKota.setData(kotaObjects);
                    adapterSheetKota.notifyDataSetChanged();
                }
            }
        });
    }

    private AdapterSheetKota.ItemClicked itemClicked = new AdapterSheetKota.ItemClicked() {
        @Override
        public void onClick(KotaObject kotaModel) {
            listener.onOptionClick(kotaModel);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        binding.setIsLoading(true);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    public void setOnSheetListener(BottomSheetListener listener) {
        this.listener = listener;
    }

    public interface BottomSheetListener {
        void onOptionClick(KotaObject kotaModel);
    }


}