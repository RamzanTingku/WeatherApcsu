package com.example.user.weather.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.weather.R;

import java.util.ArrayList;
//----------------------------STEPS----------------------------------------------
// TODO-1: Constructor
// TODO-2: Three override mathod
//        TODO-2.1: create onCreateViewHolder mathod
//        TODO-2.2: create onBindViewHolder mathod
//        TODO-2.3: create getItemCount mathod
// TODO-3: create inner class ContactViewHolder  for return type TODO-2.1
//-------------------------------------------------------------------------------

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<Weather> weathers;

    // TODO-1: Constructor
    public ContactRecyclerAdapter(Context context, ArrayList<Weather> weathers) {
        this.context = context;
        this.weathers = weathers;
    }


    // TODO-2: Three override mathod
           // TODO-2.1: create onCreateViewHolder mathod
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.hourly_weather_row,parent,false);
        return new ContactViewHolder(v);
    }

           // TODO-2.2: create onBindViewHolder mathod
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.sName.setText("hh");

    }

           // TODO-2.3: create getItemCount mathod
    @Override
    public int getItemCount() {
        return weathers.size();
    }


    // TODO-3: create inner class ContactViewHolder
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView sName;


        public ContactViewHolder(View itemView) {
            super(itemView);

              sName=itemView.findViewById(R.id.single_city);


        }


    }


}
