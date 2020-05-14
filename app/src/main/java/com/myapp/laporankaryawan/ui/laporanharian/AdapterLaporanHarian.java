package com.myapp.laporankaryawan.ui.laporanharian;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.realmobject.LaporanObject;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.databinding.ItemHarianBinding;
import com.myapp.laporankaryawan.databinding.ItemKaryawanBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterLaporanHarian extends RecyclerView.Adapter<AdapterLaporanHarian.MyViewHolder> {
    private AdapterItemClicked adapterItemClicked;
    private List<LaporanObject> dalist = new ArrayList<>();
    public AdapterLaporanHarian(AdapterItemClicked adapterItemClicked){
        this.adapterItemClicked = adapterItemClicked;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHarianBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_harian,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setPosition(position);
        holder.binding.setData(dalist.get(position));

    }

    @Override
    public int getItemCount() {
        return dalist == null ? 0 : dalist.size();
    }
    public void setData(List<LaporanObject> data){
        if(this.dalist == null){
            this.dalist.addAll(data);
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterLaporanHarian.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterLaporanHarian.this.dalist.get(oldItemPosition).getIdUser() == data.get(newItemPosition).getIdUser();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    LaporanObject lama = data.get(oldItemPosition);
                    LaporanObject baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama,baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
        }
    }
    public LaporanObject getFromPosition(int position){
        return dalist.get(position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemHarianBinding binding;
        public MyViewHolder(ItemHarianBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
