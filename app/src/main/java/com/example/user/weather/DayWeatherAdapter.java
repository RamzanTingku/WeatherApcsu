package com.example.user.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 19/9/2017.
 */


public class DayWeatherAdapter extends RecyclerView.Adapter<DayWeatherAdapter.WeatherViewHolder> {

    private Context context;
    private ArrayList<DayWeather> dayWeathers;

    // TODO-1: Constructor
    public DayWeatherAdapter(Context context, ArrayList<DayWeather> dayWeathers) {
        this.context = context;
        this.dayWeathers = dayWeathers;
    }


    // TODO-2: Three override mathod
    // TODO-2.1: create onCreateViewHolder mathod
    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.hourly_weather_row,parent,false);
        return new WeatherViewHolder(v);
    }

    // TODO-2.2: create onBindViewHolder mathod
    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.vhTime.setText(dayWeathers.get(position).getdTime());
        //holder.vhImage.setBackground(dayWeathers.get(position).getdImage());
        holder.vhTemp.setText(dayWeathers.get(position).getdTemp());
    }

    // TODO-2.3: create getItemCount mathod
    @Override
    public int getItemCount() {
        return dayWeathers.size();
    }


    // TODO-3: create inner class ContactViewHolder
    public class WeatherViewHolder extends RecyclerView.ViewHolder{

        TextView vhTime;
        //ImageView vhImage;
        TextView vhTemp;

        public WeatherViewHolder(View itemView) {
            super(itemView);

           /* vhTime=itemView.findViewById(R.id.dTimeTv);
           // vhImage=itemView.findViewById(R.id.dImageTv);
            vhTemp=itemView.findViewById(R.id.dTempTv);*/


        }
    }


}

