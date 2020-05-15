package com.myapp.laporankaryawan.ui.tambahoutlet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.KotaModel;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.SendDataListener;
import com.myapp.laporankaryawan.callback.SheetShow;
import com.myapp.laporankaryawan.databinding.TambahOutletFragmentBinding;
import com.myapp.laporankaryawan.ui.bottomsheet.SheetKota;

public class TambahOutlet extends BaseFragment {
    public static String TAG = "Tambah Outlet";
    private TambahOutletViewModel mViewModel;
    private TambahOutletFragmentBinding binding;
    private SheetKota sheetKota;

    public static TambahOutlet newInstance() {
        return new TambahOutlet();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_outlet_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(),new TambahOutletFactory(getContext())).get(TambahOutletViewModel.class);
        mViewModel.setOnListener(sendDataListener);
        binding.setEvent(sheetShow);
        binding.setVm(mViewModel);
        sheetKota = new SheetKota();
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar,"Tambah Outlet","");
        sheetKota = new SheetKota();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        sheetKota.setOnSheetListener(sheetListener);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.toolbarformnav,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String message) {
            binding.setIsLoading(false);
            dialogBerhasil(message);
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }
    };
    private SheetShow sheetShow = new SheetShow() {
        @Override
        public void sheetShow(View v) {
            sheetKota.show(getActivity().getSupportFragmentManager(),"Show Outlet");
        }
    };
    private SheetKota.BottomSheetListener sheetListener = new SheetKota.BottomSheetListener() {
        @Override
        public void onOptionClick(KotaObject kotaModel) {
            Log.e("on sheet click",kotaModel.getNamaKota());
            sheetKota.dismiss();
            KotaModel kotaModel1 = new KotaModel();
            kotaModel1.setIdKota(kotaModel.getIdKota());
            kotaModel1.setCreatedAt(kotaModel.getCreatedAt());
            kotaModel1.setUpdatedAt(kotaModel.getUpdatedAt());
            kotaModel1.setNamaKota(kotaModel.getNamaKota());
            binding.setKota(kotaModel1);

        }
    };
}
