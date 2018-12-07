package com.example.dell.portal.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.portal.LayarEditKategori;
import com.example.dell.portal.Model.Kategori;
import com.example.dell.portal.R;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>  {
    List<Kategori> listKategori;
    public KategoriAdapter(List<Kategori> listKategori) {
        this.listKategori = listKategori;
    }


    @Override
    public KategoriAdapter.KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori, parent, false);
        KategoriViewHolder mHolder = new KategoriViewHolder(view);
        return mHolder;

    }

    @Override
    public void onBindViewHolder(KategoriAdapter.KategoriViewHolder holder, final int position) {
        holder.tvIdKategori.setText(listKategori.get(position).getIdKategori());
        holder.tvNama.setText(listKategori.get(position).getKategori());
        if (listKategori.get(position).getPhotoUrl() != null ){
            Glide.with(holder.itemView.getContext()).load(listKategori.get
                    (position).getPhotoUrl()).into(holder.mPhotoURL);
        }
        else{
            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
                    .mPhotoURL);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LayarEditKategori.class);
                intent.putExtra("id_kategori",listKategori.get(position).getIdKategori());
                intent.putExtra("kategori", listKategori.get(position).getKategori());
                intent.putExtra("photo_url",listKategori.get(position).getPhotoUrl());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKategori.size();
    }


    public class KategoriViewHolder extends RecyclerView.ViewHolder {
       ImageView mPhotoURL;
        TextView tvIdKategori, tvNama;

        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgKategori);
            tvIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
        }
    }
}
