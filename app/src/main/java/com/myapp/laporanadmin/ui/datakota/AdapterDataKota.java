package com.myapp.laporanadmin.ui.datakota;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemKotaBinding;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.laporanadmin.callback.AdapterItemClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterDataKota extends RecyclerView.Adapter<AdapterDataKota.MyViewHolder> {
    private AdapterItemClicked adapterItemClicked;
    private List<KotaObject> dalist = new ArrayList<>();

    public AdapterDataKota(AdapterItemClicked adapterItemClicked) {
        this.adapterItemClicked = adapterItemClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKotaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_kota, parent, false);
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

    public void setData(List<KotaObject> data) {
        if (this.dalist == null) {
            this.dalist.addAll(data);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterDataKota.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterDataKota.this.dalist.get(oldItemPosition).getIdKota() == data.get(newItemPosition).getIdKota();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    KotaObject lama = data.get(oldItemPosition);
                    KotaObject baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
        }
    }

    public KotaObject getFromPosition(int position) {
        return dalist.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemKotaBinding binding;

        public MyViewHolder(ItemKotaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
