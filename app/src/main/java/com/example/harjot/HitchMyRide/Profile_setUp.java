package com.example.harjot.HitchMyRide;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Profile_setUp extends AppCompatActivity {

    Button save_btn;
    EditText name;
    EditText age;
    EditText contact;
    String gender="";
    String pref_comp="";
    RadioGroup rbg_gender;
    RadioGroup rbg_pref;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_up);
        /*Toast.makeText(ctx,String.valueOf(51),Toast.LENGTH_SHORT).show();
        rbg_gender= (RadioGroup) findViewById(R.id.rbg_gender);
        rbg_pref= (RadioGroup) findViewById(R.id.rbg_pref);
        save_btn= (Button) findViewById(R.id.btn_save);
        name= (EditText) findViewById(R.id.name_txt);
        name.setText(MainActivity.name);
        age= (EditText) findViewById(R.id.age_txt);
        if(MainActivity.age!=0){
            age.setText(String.valueOf(MainActivity.age));
        }
        contact= (EditText) findViewById(R.id.contact_txt);
        if(MainActivity.contact_no!=0){
            contact.setText(String.valueOf(MainActivity.contact_no));
        }
        rbg_gender.check(MainActivity.g_id);
        rbg_pref.check(MainActivity.p_id);
        gender="Male";
        pref_comp="Male Youth";
        OnClickListener();*/
    }
    /*public void OnClickListener(){
        /*save_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int flag=checkData();
                        if(flag==1){
                            MainActivity.name=name.getText().toString();
                            MainActivity.age=Integer.parseInt(age.getText().toString());
                            MainActivity.g_id=rbg_gender.getCheckedRadioButtonId();
                            Toast.makeText(ctx,String.valueOf(MainActivity.g_id),Toast.LENGTH_SHORT).show();
                            View radioButton=rbg_gender.findViewById(MainActivity.g_id);
                            int gender_id=rbg_gender.indexOfChild(radioButton);
                            if(gender_id==0){
                                MainActivity.gender="Male";
                            }
                            else{
                                MainActivity.gender="Female";
                            }
                            MainActivity.p_id=rbg_pref.getCheckedRadioButtonId();
                            Toast.makeText(ctx,String.valueOf(MainActivity.p_id),Toast.LENGTH_SHORT).show();
                            View radioButton2=rbg_pref.findViewById(MainActivity.p_id);
                            int gender_pref=rbg_pref.indexOfChild(radioButton2);
                            if(gender_pref==0){
                                MainActivity.pref_comp="Male Youth";
                            }
                            else if(gender_pref==1){
                                MainActivity.pref_comp="Female Youth";
                            }
                            else{
                                MainActivity.pref_comp="Senior Citizen";
                            }
                            MainActivity.contact_no=Long.parseLong(contact.getText().toString());
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                        else{

                        }
                    }
                }
        );
    }
    public int checkData(){
       /* int res=0;
        if(name.getText().toString()!=""&&age.getText().toString()!=""&&contact.getText().toString()!=""){
            res=1;
            Toast.makeText(ctx,String.valueOf(res),Toast.LENGTH_SHORT).show();
        }
        return res;
    }*/
}
