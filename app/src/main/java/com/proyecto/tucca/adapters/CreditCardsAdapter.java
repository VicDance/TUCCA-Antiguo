package com.proyecto.tucca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tucca.model.CreditCard;
import com.proyecto.tucca.R;

import java.util.ArrayList;

public class CreditCardsAdapter extends RecyclerView.Adapter<CreditCardsAdapter.CreditCardsViewHolder> {
    private ArrayList<CreditCard> itemList;
    private CreditCardsAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(CreditCardsAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public static class CreditCardsViewHolder extends RecyclerView.ViewHolder{
        public TextView user;
        public TextView textNumber;
        public TextView cad;

        public CreditCardsViewHolder(@NonNull View itemView, final CreditCardsAdapter.OnItemClickListener listener) {
            super(itemView);
            user = itemView.findViewById(R.id.text_view_user_credit_card);
            textNumber = itemView.findViewById(R.id.text_view_number_credit_card);
            cad = itemView.findViewById(R.id.text_view_cad_credit_card);
            /*itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        //System.out.println("Posicion " + position);
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }

    public CreditCardsAdapter(ArrayList<CreditCard> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CreditCardsAdapter.CreditCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_card_item, parent, false);
        CreditCardsAdapter.CreditCardsViewHolder viewHolder = new CreditCardsAdapter.CreditCardsViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditCardsAdapter.CreditCardsViewHolder holder, int position) {
        CreditCard currentItem = itemList.get(position);
        holder.user.setText(currentItem.getCardUser());
        holder.textNumber.setText(currentItem.getTextNumber());
        holder.cad.setText(currentItem.getCad());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
