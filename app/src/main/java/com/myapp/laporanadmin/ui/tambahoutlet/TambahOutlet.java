package com.myapp.laporanadmin.ui.tambahoutlet;

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

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.TambahOutletFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.callback.SheetShow;
import com.myapp.laporanadmin.ui.bottomsheet.SheetKota;

public class TambahOutlet extends BaseFragment {
    public static String TAG = "Tambah Outlet";
    private TambahOutletViewModel mViewModel;
    private TambahOutletFragmentBinding binding;
    private SheetKota sheetKota;
    private String tipe ;
    private OutletModel outletModel;
    public TambahOutlet(){ }
    public TambahOutlet(String tipe, OutletModel outletModel){
        this.tipe = tipe;
        this.outletModel = outletModel;
    }
    public static TambahOutlet newInstance() {
        return new TambahOutlet();
    }
    public static TambahOutlet newInstance(String tipe,OutletModel outletModel) {
        return new TambahOutlet(tipe,outletModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_outlet_fragment, container, false);


        mViewModel = new ViewModelProvider(requireActivity(),new TambahOutletFactory(getContext())).get(TambahOutletViewModel.class);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar,"Tambah Outlet","");
        mViewModel.setOnListener(sendDataListener);
        binding.setEvent(sheetShow);
        binding.setVm(mViewModel);
        if(tipe == null){
            this.tipe = getContext().getString(R.string.AKSI_TAMBAH);
        }
        MyUser.getInstance(getContext()).setTipeFormOutlet(tipe);
        if(outletModel != null){
            PostOutletModel outlet = new PostOutletModel();
            outlet.setIdKota(Integer.parseInt(outletModel.getIdKota()));
            outlet.setNamaOutlet(outletModel.getNamaOutlet());
            mViewModel.outletmodel.set(outlet);
        }
        sheetKota = new SheetKota();

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyUser.getInstance(getContext()).setTipeFormOutlet(null);
    }
}
