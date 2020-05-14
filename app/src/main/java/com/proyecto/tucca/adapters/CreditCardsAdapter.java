package com.proyecto.tucca.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tucca.activities.CreditCardActivity;
import com.proyecto.tucca.model.CreditCard;
import com.proyecto.tucca.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CreditCardsAdapter extends RecyclerView.Adapter<CreditCardsAdapter.CreditCardsViewHolder> {
    private ArrayList<CreditCard> itemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CreditCardsViewHolder extends RecyclerView.ViewHolder{
        public TextView user;
        public TextView textNumber;
        public TextView cad;

        public CreditCardsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            user = itemView.findViewById(R.id.text_view_user_credit_card);
            textNumber = itemView.findViewById(R.id.text_view_number_credit_card);
            cad = itemView.findViewById(R.id.text_view_cad_credit_card);
            System.out.println("llega");
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //System.out.println("entra");
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                            //System.out.println(position);
                            /*new AlertDialog.Builder(mContext)
                                    .setTitle("Saldo")
                                    .setMessage("Saldo")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Toast.makeText(CreditCardsAdapter.this, "Se creó la tarjeta", Toast.LENGTH_SHORT).show();
                                            System.out.println("Ok");
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dialog.cancel();
                                            //Toast.makeText(CreditCardsAdapter.this, "No se creó la tarjeta", Toast.LENGTH_SHORT).show();
                                            System.out.println("no Ok");
                                        }
                                    })
                                    .show();*/
                        }
                    }
                }
            });
        }
    }

    public CreditCardsAdapter(ArrayList<CreditCard> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CreditCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_card_item, parent, false);
        CreditCardsViewHolder viewHolder = new CreditCardsViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditCardsViewHolder holder, int position) {
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
