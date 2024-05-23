package com.example.crud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private final ArrayList user_id;
    private final ArrayList username;
    private final ArrayList regNo;
    private final ArrayList email;

    CustomAdapter(Activity activity, Context context, ArrayList user_id, ArrayList username, ArrayList regNo,
                  ArrayList email){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.username = username;
        this.regNo = regNo;
        this.email = email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_username_txt.setText(String.valueOf(username.get(position)));
        holder.user_regNo_txt.setText(String.valueOf(regNo.get(position)));
        holder.user_email_txt.setText(String.valueOf(email.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(user_id.get(position)));
            intent.putExtra("username", String.valueOf(username.get(position)));
            intent.putExtra("regNo", String.valueOf(regNo.get(position)));
            intent.putExtra("email", String.valueOf(email.get(position)));
            activity.startActivityForResult(intent, 1);
        });


    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_username_txt, user_regNo_txt, user_email_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            user_username_txt = itemView.findViewById(R.id.user_username_txt);
            user_regNo_txt = itemView.findViewById(R.id.user_regNo_txt);
            user_email_txt = itemView.findViewById(R.id.user_email_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
