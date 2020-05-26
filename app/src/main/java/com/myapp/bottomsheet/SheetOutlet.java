package com.myapp.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myapp.R;
import com.myapp.databinding.FragmentSheetOutletBinding;
import com.myapp.domain.realmobject.OutletObject;


public class SheetOutlet extends BottomSheetDialogFragment {
    private FragmentSheetOutletBinding binding;
    private SheetOutletViewModel viewModel;
    private BottomSheetBehavior behavior;
    private AdapterSheetOutlet adapterSheetKota;
    private BottomSheetListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_sheet_outlet, null, false);
        binding.setIsLoading(true);
        adapterSheetKota = new AdapterSheetOutlet(itemClicked);
        binding.shimmerRecyclerDesa.setAdapter(adapterSheetKota);


        dialog.setContentView(binding.getRoot());
        behavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());


        return dialog;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SheetOutletViewModel.class);
        viewModel.setContext(getContext());
        viewModel.sync();

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setIsLoading(true);
        observe(viewModel);

    }

    private void observe(SheetOutletViewModel viewModel) {
        viewModel.getResponseGetPendudukRtLiveData().observe(this, outletObjects -> {
            if (outletObjects.size() >= 1) {
                binding.setIsLoading(false);
                adapterSheetKota.setData(outletObjects);
                adapterSheetKota.notifyDataSetChanged();
            }
        });
    }

    private AdapterSheetOutlet.ItemClicked itemClicked = new AdapterSheetOutlet.ItemClicked() {
        @Override
        public void onClick(OutletObject kotaModel) {
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
        void onOptionClick(OutletObject kotaModel);
    }


}