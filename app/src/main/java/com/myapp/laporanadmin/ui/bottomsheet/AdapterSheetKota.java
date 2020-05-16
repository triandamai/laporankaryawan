package com.myapp.laporanadmin.ui.bottomsheet;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemKotaBinding;
import com.myapp.domain.realmobject.KotaObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterSheetKota extends RecyclerView.Adapter<AdapterSheetKota.MyViewHolder> {
    private List<KotaObject> responseRts = new ArrayList<>();
    private ItemClicked callback;

    public AdapterSheetKota(ItemClicked callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKotaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_sheet_kota, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("data", responseRts.get(position).toString());
        holder.binding.setData(responseRts.get(position));
        holder.binding.setCallback(callback);
    }

    public void setData(List<KotaObject> penduduk_models) {
        if (this.responseRts == null) {
            this.responseRts.addAll(penduduk_models);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterSheetKota.this.responseRts.size();
                }

                @Override
                public int getNewListSize() {
                    return responseRts.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterSheetKota.this.responseRts.get(oldItemPosition).getIdKota() == responseRts.get(newItemPosition).getIdKota();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    KotaObject lama = responseRts.get(oldItemPosition);
                    KotaObject baru = responseRts.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);

                }
            });
            this.responseRts = penduduk_models;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public int getItemCount() {
        return responseRts == null ? 0 : responseRts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemKotaBinding binding;

        public MyViewHolder(ItemKotaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface ItemClicked{
        void onClick(KotaObject kotaModel);
    }
}
