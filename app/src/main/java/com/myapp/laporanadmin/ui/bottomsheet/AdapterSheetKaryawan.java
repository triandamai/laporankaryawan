package com.myapp.laporanadmin.ui.bottomsheet;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemSheetKaryawanBinding;
import com.myapp.domain.realmobject.KaryawanObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterSheetKaryawan extends RecyclerView.Adapter<AdapterSheetKaryawan.MyViewHolder> {
    private List<KaryawanObject> responseRts = new ArrayList<>();
    private ItemClicked callback;

    public AdapterSheetKaryawan(ItemClicked callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSheetKaryawanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_sheet_karyawan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("data", responseRts.get(position).toString());
        holder.binding.setData(responseRts.get(position));
        holder.binding.setEvent(callback);
    }

    public void setData(List<KaryawanObject> penduduk_models) {
        if (this.responseRts == null) {
            this.responseRts.addAll(penduduk_models);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterSheetKaryawan.this.responseRts.size();
                }

                @Override
                public int getNewListSize() {
                    return responseRts.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterSheetKaryawan.this.responseRts.get(oldItemPosition).getIdUser() == responseRts.get(newItemPosition).getIdUser();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    KaryawanObject lama = responseRts.get(oldItemPosition);
                    KaryawanObject baru = responseRts.get(newItemPosition);
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
        private ItemSheetKaryawanBinding binding;

        public MyViewHolder(ItemSheetKaryawanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemClicked {
        void onClick(KaryawanObject kotaModel);
    }
}
