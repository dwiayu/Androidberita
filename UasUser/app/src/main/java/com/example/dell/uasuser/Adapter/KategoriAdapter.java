package com.example.dell.uasuser.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.uasuser.Model.Kategori;
import com.example.dell.uasuser.R;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {
    List<Kategori> listKategori;

    public KategoriAdapter(List<Kategori> listKategori) {
        this.listKategori = listKategori;
    }


    @NonNull
    @Override
    public KategoriAdapter.KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori, parent, false);
        KategoriViewHolder mHolder = new KategoriViewHolder(view);
        return mHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, final int position) {
        holder.tvNama.setText(listKategori.get(position).getKategori());
        holder.tvJudul.setText(listKategori.get(position).getJudul());
        holder.tvIsi.setText(listKategori.get(position).getIsi());
        holder.tvSumber.setText(listKategori.get(position).getNamaSumber());

        if (listKategori.get(position).getPhotoUrl() != null ){

            Glide.with(holder.itemView.getContext()).load(listKategori.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {

            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
                    .mPhotoURL);
        }

    }

    @Override
    public int getItemCount() {
        return listKategori.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView  tvNama,tvJudul,tvIsi,tvSumber;


        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgKategori);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tvIsi = (TextView) itemView.findViewById(R.id.tvIsi);
            tvSumber =(TextView) itemView.findViewById(R.id.tvSumber);

        }
    }
}
