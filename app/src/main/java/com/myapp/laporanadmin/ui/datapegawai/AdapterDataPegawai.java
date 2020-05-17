package com.myapp.laporanadmin.ui.datapegawai;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemKaryawanBinding;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterDataPegawai extends RecyclerView.Adapter<AdapterDataPegawai.MyViewHolder> {
    private AdapterItemClicked adapterItemClicked;
    private List<KaryawanObject> dalist = new ArrayList<>();
    public AdapterDataPegawai(AdapterItemClicked adapterItemClicked){
        this.adapterItemClicked = adapterItemClicked;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKaryawanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_karyawan,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setPosition(position);
        holder.binding.setData(dalist.get(position));
        holder.binding.setEvent(adapterItemClicked);

    }

    @Override
    public int getItemCount() {
        return dalist == null ? 0 : dalist.size();
    }
    public void setData(List<KaryawanObject> data){
        if(this.dalist == null){
            this.dalist.addAll(data);
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterDataPegawai.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterDataPegawai.this.dalist.get(oldItemPosition).getIdUser() == data.get(newItemPosition).getIdUser();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    KaryawanObject lama = data.get(oldItemPosition);
                    KaryawanObject baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama,baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }
    public KaryawanObject getFromPosition(int position){
        return dalist.get(position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemKaryawanBinding binding;
        public MyViewHolder(ItemKaryawanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
