package com.example.harjot.HitchMyRide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Harjot on 30-Dec-15.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<RideDetails> contactList;
    static Activity mAct;
    public ContactAdapter(List<RideDetails> contactList, Activity activity) {
        mAct=activity;
        this.contactList = contactList;
    }
    @Override
    public int getItemCount() {

        return contactList.size();
    }
    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        RideDetails rd = contactList.get(i);
        contactViewHolder.vName.setText(rd.getName().toString());
        contactViewHolder.vGender.setText(rd.getGender().toString());
        if(rd.getGender().toString().equals("Male")){
            contactViewHolder.vName.setTextColor(Color.parseColor("#3F51B5"));
        }
        else{
            contactViewHolder.vName.setTextColor(Color.parseColor("#FF4080"));
        }
        contactViewHolder.vPre_Comp.setText(rd.getPref_comp().toString());
        contactViewHolder.vDate_txt.setText(String.valueOf(rd.getDay()+"/"+rd.getMonth()+"/"+rd.getYear()));
        contactViewHolder.vTime_txt.setText(String.valueOf(rd.getHour()+":"+rd.getMin()));
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }
    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView vName;
        protected TextView vGender;
        protected TextView vPre_Comp;
        protected TextView vDate_txt;
        protected TextView vTime_txt;
        public ContactViewHolder(View vi) {
            super(vi);
            vName= (TextView) vi.findViewById(R.id.txt_name_c);
            vGender=(TextView)vi.findViewById(R.id.txt_gender_c);
            vPre_Comp=(TextView) vi.findViewById(R.id.txt_pref_comp_c);
            vDate_txt= (TextView) vi.findViewById(R.id.txt_date_c);
            vTime_txt= (TextView) vi.findViewById(R.id.txt_time_c);
            vi.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            Intent intent = new Intent(mAct, View_ride.class);
            intent.putExtra("pos",getPosition());
            // Define the view that the animation will start from
            //Pair<View,String> p1=Pair.create(view.findViewById(R.id.txt_name_c),"qwerty");
            Pair<View,String> p2=Pair.create(view.findViewById(R.id.cardView),"qwerty2");

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mAct,p2);
            //Start the Intent
            ActivityCompat.startActivity(mAct, intent, options.toBundle());
            //mAct.startActivity(intent);

        }

    }
}
