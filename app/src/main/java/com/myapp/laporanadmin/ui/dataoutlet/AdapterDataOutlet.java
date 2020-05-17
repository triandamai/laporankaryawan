package com.myapp.laporanadmin.ui.dataoutlet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemKaryawanBinding;
import com.myapp.databinding.ItemOutletBinding;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterDataOutlet extends RecyclerView.Adapter<AdapterDataOutlet.MyViewHolder> {
    private AdapterItemClicked adapterItemClicked;
    private List<OutletObject> dalist = new ArrayList<>();
    public AdapterDataOutlet(AdapterItemClicked adapterItemClicked){
        this.adapterItemClicked = adapterItemClicked;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOutletBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_outlet,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setPosition(position);
        holder.binding.setData(dalist.get(position));
        holder.binding.setCallback(adapterItemClicked);

    }

    @Override
    public int getItemCount() {
        return dalist == null ? 0 : dalist.size();
    }
    public void setData(List<OutletObject> data){
        if(this.dalist == null){
            this.dalist.addAll(data);
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterDataOutlet.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterDataOutlet.this.dalist.get(oldItemPosition).getIdOutlet() == data.get(newItemPosition).getIdOutlet();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    OutletObject lama = data.get(oldItemPosition);
                    OutletObject baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama,baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }
    public OutletObject getFromPosition(int position){
        return dalist.get(position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemOutletBinding binding;
        public MyViewHolder(ItemOutletBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
