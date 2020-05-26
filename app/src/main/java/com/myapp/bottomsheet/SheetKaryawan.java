package com.myapp.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.myapp.databinding.FragmentSheetKaryawanBinding;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.realmobject.KaryawanObject;

import java.util.List;


public class SheetKaryawan extends BottomSheetDialogFragment {
    private FragmentSheetKaryawanBinding binding;
    private SheetKaryawanViewModel viewModel;
    private BottomSheetBehavior behavior;
    private AdapterSheetKaryawan adapterSheetKaryawan;
    private BottomSheetListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_sheet_karyawan, null, false);
        binding.setIsLoading(true);
        adapterSheetKaryawan = new AdapterSheetKaryawan(itemClicked);
        binding.shimmerRecyclerDesa.setAdapter(adapterSheetKaryawan);
        dialog.setContentView(binding.getRoot());
        behavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());


        return dialog;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SheetKaryawanViewModel.class);
        viewModel.setContext(getContext());
        viewModel.sync();

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setIsLoading(true);
        observe(viewModel);

    }

    private void observe(SheetKaryawanViewModel viewModel) {
        viewModel.getResponseGetPendudukRtLiveData().observe(this, new Observer<List<KaryawanObject>>() {
            @Override
            public void onChanged(List<KaryawanObject> karyawanObjects) {
                if (karyawanObjects.size() >= 1) {
                    binding.setIsLoading(false);
                    adapterSheetKaryawan.setData(karyawanObjects);
                    adapterSheetKaryawan.notifyDataSetChanged();
                }
            }
        });

    }

    private AdapterSheetKaryawan.ItemClicked itemClicked = new AdapterSheetKaryawan.ItemClicked() {
        @Override
        public void onClick(KaryawanObject karyawanObject) {
            Log.e("TAG", karyawanObject.toString());
            UserModel karyawanModel = new UserModel();
            karyawanModel.setIdUser(karyawanObject.getIdUser());
            karyawanModel.setCreatedAt(karyawanObject.getCreatedAt());
            karyawanModel.setFotoUser(karyawanObject.getFotoUser());
            karyawanModel.setLevelUser(karyawanObject.getLevelUser());
            karyawanModel.setPasswordUser(karyawanObject.getPasswordUser());
            karyawanModel.setUsernameUser(karyawanObject.getUsernameUser());
            karyawanModel.setUpdatedAt(karyawanObject.getUpdatedAt());
            karyawanModel.setNamaUser(karyawanObject.getNamaUser());
            karyawanModel.setNipUser(karyawanObject.getNipUser());


            listener.onOptionClick(karyawanModel);
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
        void onOptionClick(UserModel kotaModel);
    }


}