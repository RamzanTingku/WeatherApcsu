package com.example.user.weather;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weather.activities.Log;
import com.example.user.weather.apcsu.ApiTool;
import com.example.user.weather.data.CityDml;


import com.example.user.weather.data.Weather;
import com.example.user.weather.google_pid_model.RetrofitPid;

import com.example.user.weather.service_interface.DayStatus;

import com.example.user.weather.yahoo_weather.Yweather;
import com.example.user.weather.service_interface.WeatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.user.weather.R.id.toolbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public Toolbar mToolbar;
    private ActionBar mActionBar;
    public TextView locationTitle;

    WeatherService weatherService;
    DayWeatherAdapter dayWeatherAdapter;
    public ArrayList<DayWeather> dayWeathers;
    RecyclerView contactRecyclerView;
//    String[] hourTempC,hourTempF,hourCondCode,hourCondIcon,hourCondText;
    AlertDialog alertDialog1;
    //CharSequence[] values = {"Celsius","Fahrenheit"};

    public String  rising,risingNormal,risingDown,risingUp,day0Condition,day1Condition,day2Condition,day3Condition,day4Condition,currentCondition,currentTemp,crntTempUnit,lastUpdate,detailsText, minTemp,maxTemp,avgTemp,humidity,pressure,visivility,uv,wind,windDir, currentConditionText,sunrise,sunset,location,time,feelTmp,day0,day0date,day0high,day0low,
            day1,day1date,day1high,day1low,day2,day2date,day2high,day2low,day3,day3date,day3high,day3low,day4,day4date,day4high,day4low,hourTempC0,hourTempC1,hourTempC2,hourTempC3,hourTempC4,hourTempC5,hourTempC6,hourTempC7,hourTempC8,hourTempC9,hourTempC10,hourTempC11;
    TextView temp0,temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10,temp11,crntTempUnitT,crntTempRangeUnitT,day0TempUnitT,day1TempUnitT,day2TempUnitT,day3TempUnitT,day4TempUnitT, lastUpdateT,detailsTextT,  minTempt,maxTempt,avgTempt,humidityt,pressuret,visivilityt,uvt,windt,windDirt,wConditiontt,sunriset,sunsett,locationt,timet,feelTmpt,day0t,day0datet,day0hight,day0lowt,
            day1t,day1datet,day1hight,day1lowt,day2t,day2datet,day2hight,day2lowt,day3t,day3datet,day3hight,day3lowt,day4t,day4datet,day4hight,day4lowt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.setting);
        mToolbar.setOverflowIcon(drawable);


        getDayStatus();


        //googleService( "school",23.777175,90.399542,1000);
       // googlePid( "v" );
       // yahooLatLong(23.777175,90.399542);

        test();




/*

          dayWeathers=new ArrayList<>();
        dayWeathers.add(new DayWeather("1:00 am","28*",1));
        dayWeathers.add(new DayWeather("2:00 am","27*",1));
        dayWeathers.add(new DayWeather("3:00 am","29*",1));
        dayWeathers.add(new DayWeather("4:00 am","31*",1));
        dayWeathers.add(new DayWeather("5:00 am","28*",1));
        dayWeathers.add(new DayWeather("1:00 am","28*",1));
        dayWeathers.add(new DayWeather("2:00 am","27*",1));
        dayWeathers.add(new DayWeather("3:00 am","29*",1));
        dayWeathers.add(new DayWeather("4:00 am","31*",1));
        dayWeathers.add(new DayWeather("5:00 am","28*",1));


        // TODO-2: Create object of LayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        // TODO-3: Create object of ContactRecyclerAdapter //
        dayWeatherAdapter=new DayWeatherAdapter(this,dayWeathers);


        // TODO-4: Setting LayoutManager and Adapter to RecyclerView of activity_main.xml//
        contactRecyclerView = (RecyclerView) findViewById(R.id.dTimelyWeatherLv);
        contactRecyclerView.setLayoutManager(llm);
        contactRecyclerView.setAdapter(dayWeatherAdapter);
*/



        //location spinner property

        CityDml cityDml;
        cityDml=new CityDml(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_location);
        ArrayList<String> weatherst= cityDml.selectData();
        ArrayAdapter<String> SpinAdapter = new ArrayAdapter<>(this, R.layout.location_spinner_text, weatherst );
        SpinAdapter.setDropDownViewResource(R.layout.location_spinner_dropdown);
        spinner.setAdapter(SpinAdapter);
        spinner.setOnItemSelectedListener(this);

        /*spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplication(),"",Toast.LENGTH_LONG).show();
            }
        });
*/


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        //Toast.makeText(this,"You Selected "+myText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Toast.makeText(getApplicationContext(), "You selected Nothing", Toast.LENGTH_SHORT).show();
    }

    public void onRefresh() {
        getDayStatus();
    }


    public void getDayStatus(){

        DayStatus.Factory.getInstance().getWeather().enqueue(new Callback<Yweather>() {
            @Override
            public void onResponse(Call<Yweather> call, Response<Yweather> response) {

                /*detailsText = response.body().getQuery().getResults().getChannel().getItem().;
                detailsTextT = (TextView) findViewById(R.id.details_text);
                detailsTextT.setText(detailsText);*/

                avgTemp=response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp();
                crntTempUnit = response.body().getQuery().getResults().getChannel().getUnits().getTemperature();
                maxTemp=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getHigh();
                minTemp=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getLow();
                humidity=response.body().getQuery().getResults().getChannel().getAtmosphere().getHumidity();
                pressure=response.body().getQuery().getResults().getChannel().getAtmosphere().getPressure();
                rising = response.body().getQuery().getResults().getChannel().getAtmosphere().getRising();
                wind=response.body().getQuery().getResults().getChannel().getWind().getSpeed();
                windDir=response.body().getQuery().getResults().getChannel().getWind().getDirection();
                visivility=response.body().getQuery().getResults().getChannel().getAtmosphere().getVisibility();
                sunrise=response.body().getQuery().getResults().getChannel().getAstronomy().getSunrise();
                sunset=response.body().getQuery().getResults().getChannel().getAstronomy().getSunset();
                location=response.body().getQuery().getResults().getChannel().getLocation().getCity();
                currentConditionText =response.body().getQuery().getResults().getChannel().getItem().getCondition().getText();
                currentCondition =response.body().getQuery().getResults().getChannel().getItem().getCondition().getCode();
                lastUpdate = response.body().getQuery().getResults().getChannel().getLastBuildDate();

                day0=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getDay();
                day1=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getDay();
                day2=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getDay();
                day3=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getDay();
                day4=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getDay();

                day0date=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getDate().substring(0,6);
                day1date=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getDate().substring(0,6);
                day2date=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getDate().substring(0,6);
                day3date=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getDate().substring(0,6);
                day4date=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getDate().substring(0,6);

                day0high=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getHigh();
                day1high=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getHigh();
                day2high=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getHigh();
                day3high=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getHigh();
                day4high=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getHigh();

                day0low=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getLow();
                day1low=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getLow();
                day2low=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getLow();
                day3low=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getLow();
                day4low=response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getLow();

                day0Condition =response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getCode() ;
                //Toast.makeText(MainActivity.this,response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getText() , Toast.LENGTH_SHORT).show();
                day1Condition =response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getCode() ;
                day2Condition =response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getCode() ;
                day3Condition =response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getCode() ;
                day4Condition =response.body().getQuery().getResults().getChannel().getItem().getForecast().get(6).getCode() ;


                avgTempt=(TextView)findViewById(R.id.current_temp);
                avgTempt.setText(FtoC(avgTemp));
                int currentImage = getResources().getIdentifier("icon_" + currentCondition, "drawable", getPackageName());
                ImageView currentImageView = (ImageView) findViewById(R.id.current_image);
                currentImageView.setImageResource(currentImage);

                crntTempUnitT = (TextView) findViewById(R.id.current_temp_unit);
                crntTempUnitT.setText(SFtoC(crntTempUnit));
                maxTempt=(TextView)findViewById(R.id.current_high_temp);
                maxTempt.setText(FtoC(maxTemp));
                minTempt=(TextView)findViewById(R.id.current_low_temp);
                minTempt.setText(FtoC(minTemp));
                crntTempRangeUnitT = (TextView) findViewById(R.id.current_range_temp_unit);
                crntTempRangeUnitT.setText(SFtoC(crntTempUnit));
                humidityt=(TextView)findViewById(R.id.current_humidity);
                humidityt.setText(humidity);
                pressuret=(TextView)findViewById(R.id.pressure);
                pressuret.setText(pressure);

                ImageView risingImageView = (ImageView) findViewById(R.id.pressure_rising);
                if (rising == "2"){
                    risingImageView.setImageResource(R.drawable.down_arrow);
                }else if(rising == "1"){
                    risingImageView.setImageResource(R.drawable.up_arrow);
                }

                visivilityt=(TextView)findViewById(R.id.visibility);
                visivilityt.setText(visivility);
                windt=(TextView)findViewById(R.id.speed);
                windt.setText(wind);
                windDirt=(TextView)findViewById(R.id.wind_direction);
                windDirt.setText(windDir);
                sunriset=(TextView)findViewById(R.id.sunrise);
                sunriset.setText(sunrise);
                sunsett=(TextView)findViewById(R.id.sunset);
                sunsett.setText(sunset);
                //locationt=(TextView)findViewById(R.id.);
                //locationt.setText(location);
                wConditiontt=(TextView)findViewById(R.id.current_condition);
                wConditiontt.setText(currentConditionText);
                lastUpdateT = (TextView) findViewById(R.id.last_update_time);
                lastUpdateT.setText(lastUpdate);

                day0t = (TextView) findViewById(R.id.day_0);
                day0t.setText(day0);
                day0datet = (TextView) findViewById(R.id.day_0_date);
                day0datet.setText(day0date);
                day0hight = (TextView) findViewById(R.id.day_0_high_temp);
                day0hight.setText(FtoC(day0high));
                day0lowt = (TextView) findViewById(R.id.day_0_low_temp);
                day0lowt.setText(FtoC(day0low));
                day0TempUnitT = (TextView) findViewById(R.id.day_0_temp_unit);
                day0TempUnitT.setText(SFtoC(crntTempUnit));
                int day0Img = getResources().getIdentifier("icon_" +day0Condition, "drawable", getPackageName());
                ImageView day0ImgView  = (ImageView) findViewById(R.id.day_0_image);
                day0ImgView.setImageResource(day0Img);

                day1t = (TextView) findViewById(R.id.day_1);
                day1t.setText(day1);
                day1datet = (TextView) findViewById(R.id.day_1_date);
                day1datet.setText(day1date);
                day1hight = (TextView) findViewById(R.id.day_1_high_temp);
                day1hight.setText(FtoC(day1high));
                day1lowt = (TextView) findViewById(R.id.day_1_low_temp);
                day1lowt.setText(FtoC(day1low));
                day1TempUnitT = (TextView) findViewById(R.id.day_1_temp_unit);
                day1TempUnitT.setText(SFtoC(crntTempUnit));
                int day1Img = getResources().getIdentifier("icon_" +day1Condition, "drawable", getPackageName());
                ImageView day1ImgView  = (ImageView) findViewById(R.id.day_1_image);
                day1ImgView.setImageResource(day1Img);

                day2t = (TextView) findViewById(R.id.day_2);
                day2t.setText(day2);
                day2datet = (TextView) findViewById(R.id.day_2_date);
                day2datet.setText(day2date);
                day2hight = (TextView) findViewById(R.id.day_2_high_temp);
                day2hight.setText(FtoC(day2high));
                day2lowt = (TextView) findViewById(R.id.day_2_low_temp);
                day2lowt.setText(FtoC(day2low));
                day2TempUnitT = (TextView) findViewById(R.id.day_2_temp_unit);
                day2TempUnitT.setText(SFtoC(crntTempUnit));
                int day2Img = getResources().getIdentifier("icon_" +day2Condition, "drawable", getPackageName());
                ImageView day2ImgView  = (ImageView) findViewById(R.id.day_2_image);
                day2ImgView.setImageResource(day2Img);

                day3t = (TextView) findViewById(R.id.day_3);
                day3t.setText(day3);
                day3datet = (TextView) findViewById(R.id.day_3_date);
                day3datet.setText(day3date);
                day3hight = (TextView) findViewById(R.id.day_3_high_temp);
                day3hight.setText(FtoC(day3high));
                day3lowt = (TextView) findViewById(R.id.day_3_low_temp);
                day3lowt.setText(FtoC(day3low));
                day3TempUnitT = (TextView) findViewById(R.id.day_3_temp_unit);
                day3TempUnitT.setText(SFtoC(crntTempUnit));
                int day3Img = getResources().getIdentifier("icon_" +day3Condition, "drawable", getPackageName());
                ImageView day3ImgView  = (ImageView) findViewById(R.id.day_3_image);
                day3ImgView.setImageResource(day3Img);

                day4t = (TextView) findViewById(R.id.day_4);
                day4t.setText(day4);
                day4datet = (TextView) findViewById(R.id.day_4_date);
                day4datet.setText(day4date);
                day4hight = (TextView) findViewById(R.id.day_4_high_temp);
                day4hight.setText(FtoC(day4high));
                day4lowt = (TextView) findViewById(R.id.day_4_low_temp);
                day4lowt.setText(FtoC(day4low));
                day4TempUnitT = (TextView) findViewById(R.id.day_4_temp_unit);
                day4TempUnitT.setText(SFtoC(crntTempUnit));
                int day4Img = getResources().getIdentifier("icon_" +day4Condition, "drawable", getPackageName());
                ImageView day4ImgView  = (ImageView) findViewById(R.id.day_4_image);
                day4ImgView.setImageResource(day4Img);



            }

            @Override
            public void onFailure(Call<Yweather> call, Throwable t) {

            }
        });


    }

    public void test(){

//

        List<Double> loc=new ArrayList<>();
        loc.add(23.777175);
        loc.add(90.399542);
        String url = "http://api.apixu.com/v1/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiTool service = retrofit.create(ApiTool.class);

        Call<com.example.user.weather.apcsu.Example> call = service.getAWeather(loc);
        call.enqueue(new Callback<com.example.user.weather.apcsu.Example>() {
            @Override
            public void onResponse(Call<com.example.user.weather.apcsu.Example> call, Response<com.example.user.weather.apcsu.Example> response) {

                String crntTempC = response.body().getCurrent().getTempC().toString();
                String crntTempF = response.body().getCurrent().getTempF().toString();
                String crntmaxTempC = response.body().getForecast().getForecastday().get(0).getDay().getMaxtempC().toString();
                String crntmaxTempF = response.body().getForecast().getForecastday().get(0).getDay().getMaxtempF().toString();
                String crntmiTempC = response.body().getForecast().getForecastday().get(0).getDay().getMintempC().toString();
                String crntminTempF = response.body().getForecast().getForecastday().get(0).getDay().getMintempF().toString();
                String humidity = response.body().getCurrent().getHumidity().toString();
                String pressureIn = response.body().getCurrent().getPressureIn().toString();
                String pressureMb = response.body().getCurrent().getPressureMb().toString();
                String windKph = response.body().getCurrent().getWindKph().toString();
                String windMph = response.body().getCurrent().getWindMph().toString();
                String windDir = response.body().getCurrent().getWindDir().toString();
                String visivilityKm = response.body().getCurrent().getVisKm().toString();
                String visivilityM = response.body().getCurrent().getVisMiles().toString();
                String sunrise = response.body().getForecast().getForecastday().get(0).getAstro().getSunrise().toString();
                String sunset = response.body().getForecast().getForecastday().get(0).getAstro().getSunset().toString();
                String location = response.body().getLocation().toString();
                String condition = response.body().getCurrent().getCondition().toString();
                String feelC = response.body().getCurrent().getFeelslikeC().toString();
                String feelF = response.body().getCurrent().getFeelslikeC().toString();


                 hourTempC0 = response.body().getForecast().getForecastday().get(0).getHour().get(0).getTempC().toString();
                 hourTempC1 = response.body().getForecast().getForecastday().get(0).getHour().get(1).getTempC().toString();
                 hourTempC2 = response.body().getForecast().getForecastday().get(0).getHour().get(4).getTempC().toString();
                 hourTempC3 = response.body().getForecast().getForecastday().get(0).getHour().get(6).getTempC().toString();
                 hourTempC4 = response.body().getForecast().getForecastday().get(0).getHour().get(8).getTempC().toString();
                 hourTempC5 = response.body().getForecast().getForecastday().get(0).getHour().get(10).getTempC().toString();
                 hourTempC6 = response.body().getForecast().getForecastday().get(0).getHour().get(12).getTempC().toString();
                 hourTempC7 = response.body().getForecast().getForecastday().get(0).getHour().get(14).getTempC().toString();
                 hourTempC8 = response.body().getForecast().getForecastday().get(0).getHour().get(16).getTempC().toString();
                 hourTempC9 = response.body().getForecast().getForecastday().get(0).getHour().get(18).getTempC().toString();
                 hourTempC10 = response.body().getForecast().getForecastday().get(0).getHour().get(20).getTempC().toString();
                 hourTempC11 = response.body().getForecast().getForecastday().get(0).getHour().get(22).getTempC().toString();

                String hourCondText0 = response.body().getForecast().getForecastday().get(0).getHour().get(0).getCondition().getText().toString();
                String hourCondText1 = response.body().getForecast().getForecastday().get(0).getHour().get(2).getCondition().getText().toString();
                String hourCondText2 = response.body().getForecast().getForecastday().get(0).getHour().get(4).getCondition().getText().toString();
                String hourCondText3 = response.body().getForecast().getForecastday().get(0).getHour().get(6).getCondition().getText().toString();
                String hourCondText4 = response.body().getForecast().getForecastday().get(0).getHour().get(8).getCondition().getText().toString();
                String hourCondText5 = response.body().getForecast().getForecastday().get(0).getHour().get(10).getCondition().getText().toString();
                String hourCondText6 = response.body().getForecast().getForecastday().get(0).getHour().get(12).getCondition().getText().toString();
                String hourCondText7 = response.body().getForecast().getForecastday().get(0).getHour().get(14).getCondition().getText().toString();
                String hourCondText8 = response.body().getForecast().getForecastday().get(0).getHour().get(16).getCondition().getText().toString();
                String hourCondText9 = response.body().getForecast().getForecastday().get(0).getHour().get(18).getCondition().getText().toString();
                String hourCondText10 = response.body().getForecast().getForecastday().get(0).getHour().get(20).getCondition().getText().toString();
                String hourCondText11 = response.body().getForecast().getForecastday().get(0).getHour().get(22).getCondition().getText().toString();


                String hour0 = response.body().getForecast().getForecastday().get(0).getHour().get(0).getTime().substring(11,16).toString();
                String hour1 = response.body().getForecast().getForecastday().get(0).getHour().get(2).getTime().substring(11,16).toString();
                String hour2 = response.body().getForecast().getForecastday().get(0).getHour().get(4).getTime().substring(11,16).toString();
                String hour3 = response.body().getForecast().getForecastday().get(0).getHour().get(6).getTime().substring(11,16).toString();
                String hour4 = response.body().getForecast().getForecastday().get(0).getHour().get(8).getTime().substring(11,16).toString();
                String hour5 = response.body().getForecast().getForecastday().get(0).getHour().get(10).getTime().substring(11,16).toString();
                String hour6 = response.body().getForecast().getForecastday().get(0).getHour().get(12).getTime().substring(11,16).toString();
                String hour7 = response.body().getForecast().getForecastday().get(0).getHour().get(14).getTime().substring(11,16).toString();
                String hour8 = response.body().getForecast().getForecastday().get(0).getHour().get(16).getTime().substring(11,16).toString();
                String hour9 = response.body().getForecast().getForecastday().get(0).getHour().get(18).getTime().substring(11,16).toString();
                String hour10 = response.body().getForecast().getForecastday().get(0).getHour().get(20).getTime().substring(11,16).toString();
                String hour11 = response.body().getForecast().getForecastday().get(0).getHour().get(22).getTime().substring(11,16).toString();




               /* for (int i = 1; i < 24; i++) {
                    hourTempC[i] = response.body().getForecast().getForecastday().get(0).getHour().get(i).getTempC().toString();
                    hourTempF[i] = response.body().getForecast().getForecastday().get(0).getHour().get(i).getTempF().toString();
                    hourCondText[i] = response.body().getForecast().getForecastday().get(0).getHour().get(i).getCondition().getText().toString();
                    hourCondIcon[i] = response.body().getForecast().getForecastday().get(0).getHour().get(i).getCondition().getIcon().toString();
                    hourCondCode[i] = response.body().getForecast().getForecastday().get(0).getHour().get(i).getCondition().getCode().toString();
                    i++;
                }*/


                TextView time0 = (TextView) findViewById(R.id.time0);
                TextView time1 = (TextView) findViewById(R.id.time1);
                TextView time2 = (TextView) findViewById(R.id.time2);
                TextView time3 = (TextView) findViewById(R.id.time3);
                TextView time4 = (TextView) findViewById(R.id.time4);
                TextView time5 = (TextView) findViewById(R.id.time5);
                TextView time6 = (TextView) findViewById(R.id.time6);
                TextView time7 = (TextView) findViewById(R.id.time7);
                TextView time8 = (TextView) findViewById(R.id.time8);
                TextView time9 = (TextView) findViewById(R.id.time9);
                TextView time10 = (TextView) findViewById(R.id.time10);
                TextView time11 = (TextView) findViewById(R.id.time11);

                time0.setText(hour0);
                time1.setText(hour1);
                time2.setText(hour2);
                time3.setText(hour3);
                time4.setText(hour4);
                time5.setText(hour5);
                time6.setText(hour6);
                time7.setText(hour7);
                time8.setText(hour8);
                time9.setText(hour9);
                time10.setText(hour10);
                time11.setText(hour11);

                TextView cond0 = (TextView) findViewById(R.id.condition0);
                TextView cond1 = (TextView) findViewById(R.id.condition1);
                TextView cond2 = (TextView) findViewById(R.id.condition2);
                TextView cond3 = (TextView) findViewById(R.id.condition3);
                TextView cond4 = (TextView) findViewById(R.id.condition4);
                TextView cond5 = (TextView) findViewById(R.id.condition5);
                TextView cond6 = (TextView) findViewById(R.id.condition6);
                TextView cond7 = (TextView) findViewById(R.id.condition7);
                TextView cond8 = (TextView) findViewById(R.id.condition8);
                TextView cond9 = (TextView) findViewById(R.id.condition9);
                TextView cond10 = (TextView) findViewById(R.id.condition10);
                TextView cond11 = (TextView) findViewById(R.id.condition11);

                cond0.setText(hourCondText0);
                cond1.setText(hourCondText1);
                cond2.setText(hourCondText2);
                cond3.setText(hourCondText3);
                cond4.setText(hourCondText4);
                cond5.setText(hourCondText5);
                cond6.setText(hourCondText6);
                cond7.setText(hourCondText7);
                cond8.setText(hourCondText8);
                cond9.setText(hourCondText9);
                cond10.setText(hourCondText10);
                cond11.setText(hourCondText11);


                 temp0 = (TextView) findViewById(R.id.temp0);
                 temp1 = (TextView) findViewById(R.id.temp1);
                 temp2 = (TextView) findViewById(R.id.temp2);
                 temp3 = (TextView) findViewById(R.id.temp3);
                 temp4 = (TextView) findViewById(R.id.temp4);
                 temp5 = (TextView) findViewById(R.id.temp5);
                 temp6 = (TextView) findViewById(R.id.temp6);
                 temp7 = (TextView) findViewById(R.id.temp7);
                 temp8 = (TextView) findViewById(R.id.temp8);
                 temp9 = (TextView) findViewById(R.id.temp9);
                 temp10 = (TextView) findViewById(R.id.temp10);
                 temp11 = (TextView) findViewById(R.id.temp11);

                temp0.setText(hourTempC0);
                temp1.setText(hourTempC1);
                temp2.setText(hourTempC2);
                temp3.setText(hourTempC3);
                temp4.setText(hourTempC4);
                temp5.setText(hourTempC5);
                temp6.setText(hourTempC6);
                temp7.setText(hourTempC7);
                temp8.setText(hourTempC8);
                temp9.setText(hourTempC9);
                temp10.setText(hourTempC10);
                temp11.setText(hourTempC11);



                String day1 = response.body().getForecast().getForecastday().get(1).getDay().toString();
                String day2 = response.body().getForecast().getForecastday().get(2).getDay().toString();
                String day3 = response.body().getForecast().getForecastday().get(3).getDay().toString();
                String day4 = response.body().getForecast().getForecastday().get(4).getDay().toString();
                String day5 = response.body().getForecast().getForecastday().get(5).getDay().toString();
                String day6 = response.body().getForecast().getForecastday().get(6).getDay().toString();

                String day1Date = response.body().getForecast().getForecastday().get(1).getDate().toString();
                String day2Date = response.body().getForecast().getForecastday().get(2).getDate().toString();
                String day3Date = response.body().getForecast().getForecastday().get(3).getDate().toString();
                String day4Date = response.body().getForecast().getForecastday().get(4).getDate().toString();
                String day5Date = response.body().getForecast().getForecastday().get(5).getDate().toString();
                String day6Date = response.body().getForecast().getForecastday().get(6).getDate().toString();


                String day1Cond = response.body().getForecast().getForecastday().get(1).toString();


                String day1maxTempC = response.body().getForecast().getForecastday().get(1).getDay().getMaxtempC().toString();
                String day2maxTempC = response.body().getForecast().getForecastday().get(2).getDay().getMaxtempC().toString();
                String day3maxTempC = response.body().getForecast().getForecastday().get(3).getDay().getMaxtempC().toString();
                String day4maxTempC = response.body().getForecast().getForecastday().get(4).getDay().getMaxtempC().toString();
                String day5maxTempC = response.body().getForecast().getForecastday().get(4).getDay().getMaxtempC().toString();
                String day6maxTempC = response.body().getForecast().getForecastday().get(6).getDay().getMaxtempC().toString();

                String day1minTempC = response.body().getForecast().getForecastday().get(1).getDay().getMintempC().toString();
                String day2minTempC = response.body().getForecast().getForecastday().get(2).getDay().getMintempC().toString();
                String day3minTempC = response.body().getForecast().getForecastday().get(3).getDay().getMintempC().toString();
                String day4minTempC = response.body().getForecast().getForecastday().get(4).getDay().getMintempC().toString();
                String day5minTempC = response.body().getForecast().getForecastday().get(5).getDay().getMintempC().toString();
                String day6minTempC = response.body().getForecast().getForecastday().get(6).getDay().getMintempC().toString();


                String day1maxTempF = response.body().getForecast().getForecastday().get(1).getDay().getMaxtempF().toString();
                String day2maxTempF = response.body().getForecast().getForecastday().get(2).getDay().getMaxtempF().toString();
                String day3maxTempF = response.body().getForecast().getForecastday().get(3).getDay().getMaxtempF().toString();
                String day4maxTempF = response.body().getForecast().getForecastday().get(4).getDay().getMaxtempF().toString();
                String day5maxTempF = response.body().getForecast().getForecastday().get(4).getDay().getMaxtempF().toString();
                String day6maxTempF = response.body().getForecast().getForecastday().get(6).getDay().getMaxtempF().toString();

                String day1minTempF = response.body().getForecast().getForecastday().get(1).getDay().getMintempF().toString();
                String day2minTempF = response.body().getForecast().getForecastday().get(2).getDay().getMintempF().toString();
                String day3minTempF = response.body().getForecast().getForecastday().get(3).getDay().getMintempF().toString();
                String day4minTempF = response.body().getForecast().getForecastday().get(4).getDay().getMintempF().toString();
                String day6minTempF = response.body().getForecast().getForecastday().get(6).getDay().getMintempF().toString();




            }

            @Override
            public void onFailure(Call<com.example.user.weather.apcsu.Example> call, Throwable t) {

            }
        });




    }


    public void editLocation(View view) {

        Intent intent = new Intent(MainActivity.this,CityActivity.class);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.temp_unit:
//                showCustomDialog();
                CreateAlertDialogWithRadioButtonGroup() ;

                break;
            case R.id.refresh:
                onRefresh();
                break;

            /*case R.id.speed_unit:
                break;*/

        }
        return super.onOptionsItemSelected(item);
    }

    public String FtoC(String Stemp) {
        int temp = 0;
        temp = Integer.parseInt(Stemp);
        return String.valueOf(Math.round((temp - 32) /1.8000));
    }

    public String CtoF(String Stemp) {
        double temp = 0;
        temp = Double.parseDouble(Stemp);
        return String.valueOf(Math.round(temp * 1.8000 + 32.00));
    }

    public String SFtoC(String Stemp) {
        return "C";
    }

    public String SCtoF(String Stemp) {
        return "F";
    }


    public void googlePid( String place_id ){

               String url = "https://maps.googleapis.com/maps/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitPid service = retrofit.create(RetrofitPid.class);

        Call<com.example.user.weather.google_pid_model.Example> call = service.getLocation("ChIJ23cSxsHAVTcRWS4OcziI0cc");

       call.enqueue(new Callback<com.example.user.weather.google_pid_model.Example>() {
           @Override
           public void onResponse(Call<com.example.user.weather.google_pid_model.Example> call, Response<com.example.user.weather.google_pid_model.Example> response) {
              // Toast.makeText(getApplication(),response.body().getResult().getGeometry().getLocation().getLat().toString(),Toast.LENGTH_LONG).show();
           }

           @Override
           public void onFailure(Call<com.example.user.weather.google_pid_model.Example> call, Throwable t) {

           }
       });



    }


    public void CreateAlertDialogWithRadioButtonGroup(){

        CharSequence[] values = {"Celsius","Fahrenheit"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Select Your Choice");

        builder.setSingleChoiceItems(values,-1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:

                        avgTempt.setText(FtoC(avgTemp));
                        crntTempUnitT.setText(SFtoC(crntTempUnit));
                        maxTempt.setText(FtoC(maxTemp));
                        minTempt.setText(FtoC(minTemp));
                        crntTempRangeUnitT.setText(SFtoC(crntTempUnit));
                        day0hight.setText(FtoC(day0high));
                        day0lowt.setText(FtoC(day0low));
                        day0TempUnitT.setText(SFtoC(crntTempUnit));
                        day1hight.setText(FtoC(day1high));
                        day1lowt.setText(FtoC(day1low));
                        day1TempUnitT.setText(SFtoC(crntTempUnit));
                        day2hight.setText(FtoC(day2high));
                        day2lowt.setText(FtoC(day2low));
                        day2TempUnitT.setText(SFtoC(crntTempUnit));
                        day3hight.setText(FtoC(day3high));
                        day3lowt.setText(FtoC(day3low));
                        day3TempUnitT.setText(SFtoC(crntTempUnit));
                        day4hight.setText(FtoC(day4high));
                        day4lowt.setText(FtoC(day4low));
                        day4TempUnitT.setText(SFtoC(crntTempUnit));
                        temp0.setText(hourTempC0);
                        temp1.setText(hourTempC1);
                        temp2.setText(hourTempC2);
                        temp3.setText(hourTempC3);
                        temp4.setText(hourTempC4);
                        temp5.setText(hourTempC5);
                        temp6.setText(hourTempC6);
                        temp7.setText(hourTempC7);
                        temp8.setText(hourTempC8);
                        temp9.setText(hourTempC9);
                        temp10.setText(hourTempC10);
                        temp11.setText(hourTempC11);


                        break;

                    case 1:
                        avgTempt.setText(avgTemp);
                        crntTempUnitT.setText(crntTempUnit);
                        maxTempt.setText(maxTemp);
                        minTempt.setText(minTemp);
                        crntTempRangeUnitT.setText(crntTempUnit);
                        day0hight.setText(day0high);
                        day0lowt.setText(day0low);
                        day0TempUnitT.setText(crntTempUnit);
                        day1hight.setText(day1high);
                        day1lowt.setText(day1low);
                        day1TempUnitT.setText(crntTempUnit);
                        day2hight.setText(day2high);
                        day2lowt.setText(day2low);
                        day2TempUnitT.setText(crntTempUnit);
                        day3hight.setText(day3high);
                        day3lowt.setText(day3low);
                        day3TempUnitT.setText(crntTempUnit);
                        day4hight.setText(day4high);
                        day4lowt.setText(day4low);
                        day4TempUnitT.setText(crntTempUnit);

                        temp0.setText(CtoF(hourTempC0));
                        temp1.setText(CtoF(hourTempC1));
                        temp2.setText(CtoF(hourTempC2));
                        temp3.setText(CtoF(hourTempC3));
                        temp4.setText(CtoF(hourTempC4));
                        temp5.setText(CtoF(hourTempC5));
                        temp6.setText(CtoF(hourTempC6));
                        temp7.setText(CtoF(hourTempC7));
                        temp8.setText(CtoF(hourTempC8));
                        temp9.setText(CtoF(hourTempC9));
                        temp10.setText(CtoF(hourTempC10));
                        temp11.setText(CtoF(hourTempC11));

                        break;

                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }



}
