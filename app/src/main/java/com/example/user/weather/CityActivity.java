package com.example.user.weather;

import android.content.Context;
import android.content.res.Resources;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weather.activities.SampleActivityBase;
import com.example.user.weather.data.CityDml;
import com.example.user.weather.data.ContactRecyclerAdapter;
import com.example.user.weather.data.Ddl;
import com.example.user.weather.data.Weather;
import com.example.user.weather.google_pid_model.Example;
import com.example.user.weather.google_pid_model.RetrofitPid;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityActivity extends SampleActivityBase implements PlaceSelectionListener {



    private TextView mPlaceDetailsText;

    private TextView mPlaceAttribution;


    private ContactRecyclerAdapter adapter;
    public ListView locationList;
    ArrayList<Weather> weathers;
    ArrayList<String> weatherst;
    private Ddl mDbHelper;
    CityDml cityDml;
    Context dcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city);

        // Retrieve the PlaceAutocompleteFragment.
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);

        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);






        locationList = (ListView) findViewById(R.id.location_listc);

        mDbHelper = new Ddl(this);
        dcontext=this;

        // TODO-1: Getting Arraylist from database//
        weathers=new ArrayList<>();
        cityDml=new CityDml(this);
       weatherst=cityDml.selectData();

        ListView simpleList;
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, weatherst);

        simpleList = (ListView) findViewById(R.id.location_listc);
        simpleList.setAdapter(arrayAdapter);






    }

    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    @Override
    public void onPlaceSelected(Place place) {




        // Format the returned place's details and display them in the TextView.
        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

        googlePid(place.getId());


        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
            mPlaceAttribution.setText("");
        }
    }

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    @Override
    public void onError(Status status) {

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to format information about a place nicely.
     */
    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {

        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }




    public void googlePid( String place_id ){

        String url = "https://maps.googleapis.com/maps/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitPid service = retrofit.create(RetrofitPid.class);

        Call<Example> call = service.getLocation(place_id);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<com.example.user.weather.google_pid_model.Example> call, Response<Example> response) {
                String city=response.body().getResult().getName();
                double lot=response.body().getResult().getGeometry().getLocation().getLat();
                double log=response.body().getResult().getGeometry().getLocation().getLat();
                mDbHelper = new Ddl(dcontext);
                CityDml cityDml2=new CityDml(dcontext);
                cityDml2.insertData(city,"demu",lot,log);
                Toast.makeText(getApplication(),response.body().getResult().getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<com.example.user.weather.google_pid_model.Example> call, Throwable t) {

            }
        });




    }



}
