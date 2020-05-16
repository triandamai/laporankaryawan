package com.myapp.laporanadmin.ui.tambahkota;

import android.os.Bundle;
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
import com.myapp.databinding.TambahKotaFragmentBinding;
import com.myapp.domain.model.KotaModel;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporanadmin.ui.tambahoutlet.TambahOutlet;

public class TambahKota extends BaseFragment {
    public static String TAG = "Tambah Kota";
    private TambahKotaViewModel mViewModel;
    private TambahKotaFragmentBinding binding;
    private String tipe ;
    private KotaModel kotaModel;
    public TambahKota(){

    }
    public TambahKota(String tipe, KotaModel kotaModel){
        this.tipe = tipe;
        this.kotaModel = kotaModel;
    }
    public static TambahKota newInstance() {
        return new TambahKota();
    }
    public static TambahKota newInstance(String tipe,KotaModel kotaModel) {
        return new TambahKota(tipe,kotaModel);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.tambah_kota_fragment, container, false);
        if(tipe == null){
            this.tipe = getContext().getString(R.string.AKSI_TAMBAH);
        }
        MyUser.getInstance(getContext()).setTipeFormKota(tipe);
        binding.setTipe(this.tipe);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar,"Tambah Kota","");
        mViewModel = new ViewModelProvider(requireActivity(),new TambahKotaFactory(getContext())).get(TambahKotaViewModel.class);
        binding.setVm(mViewModel);
        mViewModel.setOnSendData(sendDataListener);
        return binding.getRoot();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyUser.getInstance(getContext()).setTipeFormKota(null);
    }
}
