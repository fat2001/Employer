package com.example.searchprojet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Anonce_adapter extends RecyclerView.Adapter<Anonce_adapter.ViewHolder> {
    ArrayList<Activite> list;
    Context context;

    public Anonce_adapter(ArrayList<Activite> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Anonce_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Anonce_adapter.ViewHolder holder, int position) {
        Activite activite = list.get(position);
        holder.txt_sujet.setText(activite.getSujet());
        holder.txt_addresse.setText(activite.getLieu());
        holder.txt_date.setText(activite.getDate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_sujet ,txt_date,txt_addresse;
        LinearLayout img_annonce;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sujet= (TextView) itemView.findViewById(R.id.txt_sujet);
            txt_date= (TextView) itemView.findViewById(R.id.txt_date);
            txt_addresse= (TextView) itemView.findViewById(R.id.txt_address);
            img_annonce = (LinearLayout) itemView.findViewById(R.id.imgannonce);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
