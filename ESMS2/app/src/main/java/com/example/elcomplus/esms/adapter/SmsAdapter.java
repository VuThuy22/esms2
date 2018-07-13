package com.example.elcomplus.esms.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elcomplus.esms.R;
import com.example.elcomplus.esms.models.Sms;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Sms> smsList;

    public SmsAdapter(List<Sms> smsList) {
        this.smsList = smsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Holder holder1=(Holder) holder;
        holder1.txt_bankName.setText(smsList.get(position).getBankName());
        holder1.txt_content.setText(smsList.get(position).getContent());
        holder1.txt_status.setText(smsList.get(position).getStatus()+"");
        holder1.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView txt_bankName, txt_content, txt_status;
        public Holder(View itemView) {
            super(itemView);
            txt_bankName=(TextView) itemView.findViewById(R.id.txt_bankName);
            txt_content=(TextView) itemView.findViewById(R.id.txt_content);
            txt_status=(TextView) itemView.findViewById(R.id.txt_status);

        }
    }

}
