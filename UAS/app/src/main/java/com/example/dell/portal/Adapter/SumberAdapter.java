package com.example.dell.portal.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.portal.LayarEditKategori;
import com.example.dell.portal.LayarEditSumber;
import com.example.dell.portal.Model.Kategori;
import com.example.dell.portal.Model.Sumber;
import com.example.dell.portal.R;

import java.util.List;

public class SumberAdapter extends RecyclerView.Adapter<SumberAdapter.SumberViewHolder> {
    List<Sumber> listSumber;
    public SumberAdapter(List<Sumber> listSumber) {
        this.listSumber = listSumber;
    }

    @NonNull
    @Override
    public SumberAdapter.SumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sumber, parent, false);
        SumberAdapter.SumberViewHolder mHolder = new SumberAdapter.SumberViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SumberAdapter.SumberViewHolder holder, final int position) {
        holder.tvIdSumber.setText(listSumber.get(position).getIdSumber());
        holder.tvNama.setText(listSumber.get(position).getNamaSumber());
       holder.tvPj.setText(listSumber.get(position).getPjSumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LayarEditSumber.class);
                intent.putExtra("id_sumber",listSumber.get(position).getIdSumber());
                intent.putExtra("nama_sumber", listSumber.get(position).getNamaSumber());
                intent.putExtra("pj_sumber",listSumber.get(position).getPjSumber());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSumber.size();    }

    public class SumberViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdSumber, tvNama, tvPj;
        public SumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdSumber = (TextView) itemView.findViewById(R.id.tvIdSumber);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvPj= (TextView) itemView.findViewById(R.id.tvPj);
        }
    }
}
