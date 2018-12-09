package com.example.dell.uasuser.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.uasuser.LayarEditUser;
import com.example.dell.uasuser.Model.Kategori;
import com.example.dell.uasuser.Model.User;
import com.example.dell.uasuser.R;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> listUser;
    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }
    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        UserAdapter.UserViewHolder mHolder = new UserAdapter.UserViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        holder.tvNama.setText(listUser.get(position).getNama());
        holder.tvAlamat.setText(listUser.get(position).getAlamat());
        holder.tvEmail.setText(listUser.get(position).getEmail());
        if (listUser.get(position).getPhotoUrl() != null ){

            Glide.with(holder.itemView.getContext()).load(listUser.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {

            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
                    .mPhotoURL);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), LayarEditUser.class);
                in.putExtra("nama",listUser.get(position).getNama());
                in.putExtra("alamat",listUser.get(position).getAlamat());
                in.putExtra("email",listUser.get(position).getEmail());
                in.putExtra("photo_url",listUser.get(position).getPhotoUrl());
                view.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvNama,tvAlamat,tvEmail;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgUser);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);

        }
    }
}
