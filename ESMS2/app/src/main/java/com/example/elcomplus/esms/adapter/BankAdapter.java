package com.example.elcomplus.esms.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elcomplus.esms.R;
import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Bank> bankList;
    private OnItemClick itemClick;
    public void setOnItemClick(OnItemClick itemClick){
        this.itemClick= itemClick;
    }


    public BankAdapter(List<Bank> bankList) {
        this.bankList = bankList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Holder holder1=(Holder) holder;
        holder1.txt_phone.setText(bankList.get(position).getBankPhone());
        holder1.txt_name.setText(bankList.get(position).getBankName());
        holder1.setIsRecyclable(false);
        holder1.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClick(bankList.get(position).getId());
            }
        });
    }
    public interface OnItemClick {
        void onClick(int id);
    }
    @Override
    public int getItemCount() {
        return bankList.size();
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView txt_name, txt_phone;
        ImageView image_delete;
        public Holder(View itemView) {
            super(itemView);
            txt_name=(TextView) itemView.findViewById(R.id.txt_name);
            txt_phone=(TextView) itemView.findViewById(R.id.txt_phone);
            image_delete = (ImageView) itemView.findViewById(R.id.image_delete);
        }
    }
}
