package com.proyecto.tucca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinesAdapter extends RecyclerView.Adapter<LinesAdapter.LinesViewHolder> {
    private ArrayList<LinesItem> itemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class LinesViewHolder extends RecyclerView.ViewHolder{
        public TextView textNumber;
        public TextView textStartFinish;

        public LinesViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textNumber = itemView.findViewById(R.id.textViewNumber);
            textStartFinish = itemView.findViewById(R.id.textViewStartFinish);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public LinesAdapter(ArrayList<LinesItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public LinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        LinesViewHolder viewHolder = new LinesViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LinesViewHolder holder, int position) {
        LinesItem currentItem = itemList.get(position);
        holder.textNumber.setText(currentItem.getTextNumber());
        holder.textStartFinish.setText(currentItem.getTextStartFinish());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
