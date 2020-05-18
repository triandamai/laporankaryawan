package com.myapp.laporanadmin.ui.rekapan;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemBulananRekapBinding;
import com.myapp.databinding.ItemHarianRekapBinding;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.laporanbulanan.LaporanBulanan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterLaporanBulananRekapan extends RecyclerView.Adapter<AdapterLaporanBulananRekapan.MyViewHolder> {

    private List<LaporanBulananModel> dalist = new ArrayList<>();
    private AdapterItemClicked adapterItemClicked;

    public AdapterLaporanBulananRekapan(AdapterItemClicked adapterItemClicked) {
        this.adapterItemClicked = adapterItemClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBulananRekapBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_bulanan_rekap, parent, false);
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

    public void setData(List<LaporanBulananModel> data) {
        if (this.dalist == null) {
            this.dalist.addAll(data);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterLaporanBulananRekapan.this.dalist.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterLaporanBulananRekapan.this.dalist.get(oldItemPosition).getIdUser() == data.get(newItemPosition).getIdUser();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    LaporanBulananModel lama = data.get(oldItemPosition);
                    LaporanBulananModel baru = data.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.dalist = data;
            result.dispatchUpdatesTo(this);
        }
    }

    public LaporanBulananModel getFromPosition(int position) {
        return dalist.get(position);
    }

    public void clearData() {
        this.dalist.clear();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemBulananRekapBinding binding;

        public MyViewHolder(ItemBulananRekapBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
