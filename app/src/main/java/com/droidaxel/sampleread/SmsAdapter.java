package com.droidaxel.sampleread;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Abhijeet.J
 */
public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.MyViewHolder> {

    private List<SmsModel> smsModels;
    private ItemClickListener itemClickListener;

    public SmsAdapter(List<SmsModel> smsModels, ItemClickListener itemClickListener) {
        this.smsModels = smsModels;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        MyViewHolder headerViewHolder = new MyViewHolder(itemView, itemClickListener);
        return headerViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SmsModel smsModel = smsModels.get(position);
        holder.id.setText(smsModel.getAddress());
        holder.message.setText(smsModel.getMsg());
        holder.date.setText(smsModel.getDate());
    }

    @Override
    public int getItemCount() {
        return smsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id, message, date;

        public MyViewHolder(View view, final ItemClickListener historyItemClickListener) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    historyItemClickListener.onItemClicked(smsModels.get(
                            getAdapterPosition()));
                }
            });
            id = (TextView) view.findViewById(R.id.sender);
            message = (TextView) view.findViewById(R.id.message);
            date = (TextView) view.findViewById(R.id.date);
        }
    }
}