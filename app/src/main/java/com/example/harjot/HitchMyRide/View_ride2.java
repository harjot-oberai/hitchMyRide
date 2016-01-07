package com.example.harjot.HitchMyRide;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class View_ride2 extends AppCompatActivity {

    int pos;
    TextView ttxx;
    DataBaseHandler data_base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ride2);
        //data_base=new DataBaseHandler(this);
        ttxx= (TextView) findViewById(R.id.txt_x);
        Bundle data=getIntent().getExtras();
        pos=data.getInt("pos")+1;
        RideDetails rd=data_base.getRide(pos);
        //RideDetails rd=MainActivity.list_x.get(pos);
        ttxx.setText(rd.getName()+" : "+rd.getGender()+" : "+rd.getPref_comp());
    }
}
