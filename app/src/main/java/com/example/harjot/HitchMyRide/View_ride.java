package com.example.harjot.HitchMyRide;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class View_ride extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int pos;
    double src_lat;
    double src_long;
    double dest_lat;
    double dest_long;
    DataBaseHandler data_base;
    TextView tx_contact;
    Button call_btn;
    RideDetails rd;
    TextView src_dest_txt;
    TextView name_rider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"Hello World1",Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ride);
        Bundle data=getIntent().getExtras();
        pos=data.getInt("pos")+1;
        data_base=new DataBaseHandler(this);
        rd=data_base.getRide(pos);
        //RideDetails rd=MainActivity.list_x.get(pos);
        tx_contact= (TextView) findViewById(R.id.tx_contact);
        call_btn= (Button) findViewById(R.id.call_btn);
        src_dest_txt= (TextView) findViewById(R.id.src_dest_txt);
        name_rider= (TextView) findViewById(R.id.name_rider_txt);
        if(rd.getGender().toString().equals("Male")){
            name_rider.setTextColor(Color.parseColor("#3F51B5"));
        }
        else{
            name_rider.setTextColor(Color.parseColor("#FF4081"));
        }
        src_lat=rd.getSrc_lat();
        src_long=rd.getSrc_long();
        dest_lat=rd.getDest_lat();
        dest_long=rd.getDest_long();
        tx_contact.setText("Contact : "+String.valueOf(rd.getContact()));
        name_rider.setText(rd.getName().toString());
        src_dest_txt.setText(String.valueOf("Source : "+rd.getSrc_name_x().toString()+"\nDestination : "+rd.getDest_name_x().toString()));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        call_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+String.valueOf(rd.getContact())));
                        try{
                            startActivity(intent);
                        }

                        catch (android.content.ActivityNotFoundException ex){
                            Toast.makeText(getApplicationContext(),"Error Making Call",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng source = new LatLng(src_lat, src_long);
        LatLng destination=new LatLng(dest_lat,dest_long);
        Marker src_mark=mMap.addMarker(new MarkerOptions().position(source).title("Source").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        Marker dest_mark=mMap.addMarker(new MarkerOptions().position(destination).title("Destination").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        /*mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(source));*/
        LatLngBounds.Builder builder=new LatLngBounds.Builder();
        builder.include(src_mark.getPosition());
        builder.include(dest_mark.getPosition());
        LatLngBounds bounds=builder.build();
        int padding = 151;
        CameraUpdate cu=CameraUpdateFactory.newLatLngBounds(bounds,padding);
        mMap.moveCamera(cu);
    }
}
