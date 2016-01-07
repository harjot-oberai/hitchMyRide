package com.example.harjot.HitchMyRide;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Calendar;

public class newRide extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btn_src;
    Button btn_dest;
    TextView name;
    TextView age;
    TextView contact;
    TextView time_txt;
    TextView date_txt;
    Button btn_time;
    Button btn_date;
    Button btn_fully_done;
    TextView src_txt;
    TextView dest_txt;
    final Calendar c = Calendar.getInstance();
    int hour;
    int min;
    int day;
    int month;
    int year_x;
    RadioGroup rbg_gender;
    RadioGroup rbg_pref;
    int Source_PICKER_REQUEST=0;
    int Destination_PICKER_REQUEST=1;
    double curr_long;
    double curr_lat;
    GPSTracker gps;
    LatLngBounds bound;
    String gender_x;
    String pref_comp_x;
    double src_lat_x=-1;
    double src_long_x=-1;
    double dest_lat_x=-1;
    double dest_long_x=-1;
    DataBaseHandler data_base;
    String src_name_x;
    String dest_name_x;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0&&resultCode== Activity.RESULT_OK){
            Place place=PlacePicker.getPlace(data,this);
            src_txt.setTextColor(Color.BLACK);
            src_txt.setText(place.getName().toString());
            LatLng ll1=place.getLatLng();
            src_lat_x=ll1.latitude;
            src_long_x=ll1.longitude;
            src_name_x=place.getName().toString();
        }
        if (requestCode==1&&resultCode==Activity.RESULT_OK){
            Place place=PlacePicker.getPlace(data,this);
            dest_txt.setTextColor(Color.BLACK);
            dest_txt.setText(place.getName().toString());
            LatLng ll1=place.getLatLng();
            dest_lat_x=ll1.latitude;
            dest_long_x=ll1.longitude;
            dest_name_x=place.getName().toString();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_aride);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toast.makeText(this,String.valueOf(500),Toast.LENGTH_SHORT).show();
        data_base= new DataBaseHandler(this);
       // btn= (Button) findViewById(R.id.btn_save_jrny11);
        gps=new GPSTracker(newRide.this);
        if (gps.canGetLocation()) {
            curr_lat=gps.getLatitude();
            curr_long=gps.getLongitude();
        } else {
            gps.showSettingsAlert();
        }
        bound=new LatLngBounds(new LatLng(curr_lat-0.005,curr_long-0.005),new LatLng(curr_lat+0.005,curr_long+0.005));
        btn_src= (Button) findViewById(R.id.src_place_btn);
        btn_dest= (Button) findViewById(R.id.dest_place_btn);
        src_txt= (TextView) findViewById(R.id.src_place_txt);
        dest_txt= (TextView) findViewById(R.id.dest_place_txt);
        name= (TextView) findViewById(R.id.name_txt11);
        age= (TextView) findViewById(R.id.age_txt11);
        contact= (TextView) findViewById(R.id.contact_txt11);
        btn_time = (Button) findViewById(R.id.btn_time);
        btn_date = (Button) findViewById(R.id.btn_date);
        year_x=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        min=c.get(Calendar.MINUTE);
        time_txt= (TextView) findViewById(R.id.time_txt);
        time_txt.setText(String.valueOf(hour+":"+min));
        date_txt= (TextView) findViewById(R.id.date_txt);
        date_txt.setText(String.valueOf(day+"/"+(month+1)+"/"+year_x));
        btn_fully_done= (Button) findViewById(R.id.btn_save_jrny_full);
        rbg_gender= (RadioGroup) findViewById(R.id.rbg_gender_x);
        rbg_pref= (RadioGroup) findViewById(R.id.rbg_pref_x);
        onTimePickListener();
        onDatePickListener();
        OnClickListener2();
        dest_placeListener();
        src_placeListener();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.Profile){
            /*Intent intent=new Intent("com.example.harjot.HitchMyRide.Profile_setUp");
            startActivityForResult(intent,1);*/
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onTimePickListener() {
        btn_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(0);
                    }
                }
        );
    }
    public void onDatePickListener() {
        btn_date.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(1);
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 0)
            return new TimePickerDialog(newRide.this, time_listener, hour, min, false);
        if (id == 1)
            return new DatePickerDialog(newRide.this, date_listener, year_x, month, day);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(year_x==c.get(Calendar.YEAR)&&month==c.get(Calendar.MONTH)&&day==c.get(Calendar.DAY_OF_MONTH)){
                if(hourOfDay>hour){
                    hour = hourOfDay;
                    min = minute;
                }
                else if(hourOfDay==hour&&minute>=min){
                    hour = hourOfDay;
                    min = minute;
                }
                else{
                    Toast.makeText(newRide.this,"Please choose a Valid Time !",Toast.LENGTH_SHORT).show();
                    showDialog(0);
                }
            }
            else{
                hour = hourOfDay;
                min = minute;
            }
            time_txt.setText(String.valueOf(hour+":"+min));
        }
    };
    protected DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if(year>year_x){
                year_x = year;
                month = monthOfYear+1;
                day = dayOfMonth;
                date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
            }
            else if(year==year_x&&monthOfYear>month){
                year_x = year;
                month = monthOfYear+1;
                day = dayOfMonth;
                date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
            }
            else if(year==year_x&&monthOfYear==month&&dayOfMonth>day){
                year_x = year;
                month = monthOfYear+1;
                day = dayOfMonth;
                date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
            }
            else if(year==year_x&&monthOfYear==month&&dayOfMonth==day){
                if(hour>c.get(Calendar.HOUR_OF_DAY)){
                    year_x = year;
                    month = monthOfYear+1;
                    day = dayOfMonth;
                    date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
                }
                else if(hour==c.get(Calendar.HOUR_OF_DAY)&&min>c.get(Calendar.MINUTE)){
                    year_x = year;
                    month = monthOfYear+1;
                    day = dayOfMonth;
                    date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
                }
                else{
                    Toast.makeText(newRide.this,"Please choose a Valid Time",Toast.LENGTH_SHORT).show();
                    date_txt.setText(String.valueOf(day+"/"+(month)+"/"+year_x));
                    showDialog(0);
                }
            }
            else{
                Toast.makeText(newRide.this,"Please choose a Valid Date",Toast.LENGTH_SHORT).show();
                date_txt.setText(String.valueOf(day+"/"+(month+1)+"/"+year_x));
                showDialog(1);
            }
        }
    };
    public boolean validate(){
        boolean flag=true;
        if(name.getText().toString().isEmpty()){
            flag=false;
            name.setError("Enter Name");
        }
        if(age.getText().toString().isEmpty()){
            flag=false;
            age.setError("Enter Age");
        }
        if(contact.getText().toString().isEmpty()){
            flag=false;
            contact.setError("Enter Contact No.");
        }
        if(src_lat_x==-1){
            flag=false;
            src_txt.setTextColor(Color.RED);
        }
        if(dest_lat_x==-1){
            flag=false;
            dest_txt.setTextColor(Color.RED);
        }
        return flag;
    }
    public void src_placeListener(){
        btn_src.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            PlacePicker.IntentBuilder intentBuilder =
                                    new PlacePicker.IntentBuilder();
                            intentBuilder.setLatLngBounds(bound);
                            Intent intent = intentBuilder.build(newRide.this);
                            startActivityForResult(intent, Source_PICKER_REQUEST);

                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
    public void dest_placeListener(){
        btn_dest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            PlacePicker.IntentBuilder intentBuilder =
                                    new PlacePicker.IntentBuilder();
                            intentBuilder.setLatLngBounds(bound);
                            Intent intent = intentBuilder.build(newRide.this);
                            startActivityForResult(intent, Destination_PICKER_REQUEST);

                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
    public void OnClickListener2(){
        btn_fully_done.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validate()){
                            MainActivity.g_id=rbg_gender.getCheckedRadioButtonId();
                            View radioButton=rbg_gender.findViewById(MainActivity.g_id);
                            int gender_id=rbg_gender.indexOfChild(radioButton);
                            if(gender_id==0){
                                gender_x="Male";
                            }
                            else{
                                gender_x="Female";
                            }
                            MainActivity.p_id=rbg_pref.getCheckedRadioButtonId();
                            View radioButton2=rbg_pref.findViewById(MainActivity.p_id);
                            int gender_pref=rbg_pref.indexOfChild(radioButton2);
                            if(gender_pref==0){
                                pref_comp_x="Male Youth";
                            }
                            else if(gender_pref==1){
                                pref_comp_x="Female Youth";
                            }
                            else{
                                pref_comp_x="Senior Citizen";
                            }
                            RideDetails rd=new RideDetails(MainActivity.num,name.getText().toString(),gender_x,pref_comp_x,Integer.parseInt(age.getText().toString()),Long.parseLong(contact.getText().toString()),hour,min,year_x,month,day,src_lat_x,src_long_x,dest_lat_x,dest_long_x,src_name_x,dest_name_x);
                            data_base.addRide(rd);
                            Intent intent=new Intent();
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                        else{
                            Toast.makeText(newRide.this,"Fill details Properly",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
