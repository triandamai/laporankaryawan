package com.myapp.laporanadmin.ui.rekapan;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemHarianRekapBinding;
import com.myapp.domain.model.LaporanHarianModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterLaporanHarianRekapan extends RecyclerView.Adapter<AdapterLaporanHarianRekapan.MyViewHolder> {

    private List<LaporanHarianModel> dalist = new ArrayList<>();

    public AdapterLaporanHarianRekapan() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHarianRekapBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_harian_rekap, parent, false);
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

    public void setData(List<LaporanHarianModel> data) {
        if (this.dalist == null) {
            this.dalist.addAll(data);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterLaporanHarianRekapan.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterLaporanHarianRekapan.this.dalist.get(oldItemPosition).getIdUser() == data.get(newItemPosition).getIdUser();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    LaporanHarianModel lama = data.get(oldItemPosition);
                    LaporanHarianModel baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
        }
    }

    public LaporanHarianModel getFromPosition(int position) {
        return dalist.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemHarianRekapBinding binding;

        public MyViewHolder(ItemHarianRekapBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
