package com.example.dell.berita.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.berita.LayarEditBerita;
import com.example.dell.berita.Model.Berita;
import com.example.dell.berita.R;
import com.example.dell.berita.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>{


    @NonNull
    List<Berita> listBerita;
    public BeritaAdapter(List<Berita> listBerita) {
        this.listBerita = listBerita;
    }
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_berita, parent, false);
        BeritaViewHolder mHolder = new BeritaViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, final int position) {
        holder.tvIdKategori.setText(listBerita.get(position).getId_kategori());
        holder.tvJudul.setText(listBerita.get(position).getJudul());
        holder.isi.setText(listBerita.get(position).getIsi());
        if (listBerita.get(position).getGambar() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(listBerita.get
                    (position).getGambar()).into(holder.mGambar);
        } else {
//          Picasso.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.ic_action_username).into(holder.mGambar);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),LayarEditBerita.class);

                intent.putExtra("id_kategori", listBerita.get(position).getId_kategori());
                intent.putExtra("judul", listBerita.get(position).getJudul());
                intent.putExtra("isi", listBerita.get(position).getIsi());
                intent.putExtra("gambar", listBerita.get(position).getGambar());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        ImageView mGambar;
        TextView tvIdKategori, tvJudul, isi;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            mGambar = (ImageView) itemView.findViewById(R.id.img);
            tvIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            isi = (TextView) itemView.findViewById(R.id.isi);


        }
    }
}