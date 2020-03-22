package com.proyecto.tucca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopsViewHolder> {
    private ArrayList<StopsItem> itemList;

    public static class StopsViewHolder extends RecyclerView.ViewHolder{
        public TextView stopsName;

        public StopsViewHolder(@NonNull View itemView) {
            super(itemView);
            stopsName = itemView.findViewById(R.id.stopName);
        }
    }

    public StopsAdapter(ArrayList<StopsItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public StopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stops_item, parent, false);
        StopsAdapter.StopsViewHolder viewHolder = new StopsAdapter.StopsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StopsViewHolder holder, int position) {
        StopsItem currentItem = itemList.get(position);
        holder.stopsName.setText(currentItem.getStopName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
