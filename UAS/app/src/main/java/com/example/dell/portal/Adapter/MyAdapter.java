package com.example.dell.portal.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.portal.LayarDetail;
import com.example.dell.portal.Model.Berita;
import com.example.dell.portal.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Berita> mBeritaList;
    public MyAdapter(List<Berita> beritaList) {
        mBeritaList = beritaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mTextViewIdBerita.setText("Id Berita :  " + mBeritaList.get(position)
                .getId_berita());
        holder.mTextViewIdSumber.setText("Id Sumber :  " + mBeritaList.get(position)
                .getId_sumber());
        holder.mTextViewIdKategori.setText("Id Kategori :  " + mBeritaList.get(position)
                .getId_kategori());
        holder.mTextViewJudul.setText("Judul :  " + mBeritaList.get(position)
                .getJudul());
        holder.mTextViewIsi.setText("Isi :  " + mBeritaList.get(position)
                .getIsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LayarDetail.class);
                mIntent.putExtra("id_berita",mBeritaList.get(position).getId_berita());
                mIntent.putExtra("id_sumber",mBeritaList.get(position).getId_sumber());
                mIntent.putExtra("id_kategori",mBeritaList.get(position).getId_kategori());
                mIntent.putExtra("judul",mBeritaList.get(position).getJudul());
                mIntent.putExtra("isi",mBeritaList.get(position).getIsi());
                view.getContext().startActivity(mIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mBeritaList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdBerita, mTextViewIdSumber, mTextViewIdKategori,mTextViewJudul,mTextViewIsi;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdBerita = (TextView) itemView.findViewById(R.id.tvIdBerita);
            mTextViewIdSumber = (TextView) itemView.findViewById(R.id.tvIdSumber);
            mTextViewIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            mTextViewJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            mTextViewIsi = (TextView) itemView.findViewById(R.id.tvIsi);



        }
    }
}
