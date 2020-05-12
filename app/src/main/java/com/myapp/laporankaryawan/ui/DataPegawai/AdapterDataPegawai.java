package com.myapp.laporankaryawan.ui.DataPegawai;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.databinding.ItemKaryawanBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterDataPegawai extends RecyclerView.Adapter<AdapterDataPegawai.MyViewHolder> {
    private AdapterItemClicked adapterItemClicked;
    private List<String> dalist = new ArrayList<>();
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setData(List<String> data){
        if(this.dalist == null){
            this.dalist = data;
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return 0;
                }

                @Override
                public int getNewListSize() {
                    return 0;
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
        }
    }
    public String getFromPosition(int position){
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
