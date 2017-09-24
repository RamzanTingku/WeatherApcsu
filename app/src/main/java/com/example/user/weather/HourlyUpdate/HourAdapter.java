package com.example.user.weather.HourlyUpdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.weather.R;

import java.util.ArrayList;

/**
 * Created by RamzanUllah on 25-Sep-17.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder>  {

    private Context context;
    private ArrayList<HourUpdate>hourUpdates;

    public HourAdapter(Context context, ArrayList<HourUpdate> hourUpdates) {
        this.context = context;
        this.hourUpdates = hourUpdates;
    }

    @Override
    public HourAdapter.HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.hour_update_row,parent,false);
        return new HourViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HourAdapter.HourViewHolder holder, int position) {

        holder.timeTV.setText(hourUpdates.get(position).getTime());
        holder.tempTV.setText(hourUpdates.get(position).getTemp());
        holder.iconIV.setImageResource(hourUpdates.get(position).getConditionIcon());

    }

    @Override
    public int getItemCount() {
        return hourUpdates.size();
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {

        ImageView iconIV;
        TextView timeTV;
        TextView tempTV;

        public HourViewHolder(View itemView) {
            super(itemView);
            iconIV = itemView.findViewById(R.id.cond_icon);
            timeTV = itemView.findViewById(R.id.time);
            tempTV = itemView.findViewById(R.id.temp);
        }
    }
}
