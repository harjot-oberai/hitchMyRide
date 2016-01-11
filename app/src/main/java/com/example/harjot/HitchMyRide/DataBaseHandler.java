package com.example.harjot.HitchMyRide;

/**
 * Created by Harjot on 31-Dec-15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Ride_Manager2";
    // Contacts table name
    private static final String TABLE_RIDE = "contacts";
    private static final String key_id="id";
    private static final String key_name="name";
    private static final String key_gender="gender";
    private static final String key_pref_comp="pref_comp";
    private static final String key_age="age";
    private static final String key_contact="contact";
    private static final String key_hour="hour";
    private static final String key_min="min";
    private static final String key_year="year";
    private static final String key_month="month";
    private static final String key_day="day";
    private static final String key_src_lat="src_lat";
    private static final String key_src_long="src_long";
    private static final String key_dest_lat="dest_lat";
    private static final String key_dest_long="dest_long";
    private static final String key_src_name="src_name";
    private static final String key_dest_name="dest_name";
    private static final String key_img_path="img_path";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_ride="CREATE TABLE "+TABLE_RIDE+"("+key_id+" INTEGER PRIMARY KEY,"+key_name+" TEXT,"+key_gender+" TEXT,"+key_pref_comp+" TEXT,"+key_age+" INTEGER,"+key_contact+" INTEGER,"+key_hour+" INTEGER,"+key_min+" INTEGER,"+key_year+" INTEGER,"+key_month+" INTEGER,"+key_day+" INTEGER,"+key_src_lat+" REAL,"+key_src_long+" REAL,"+key_dest_lat+" REAL,"+key_dest_long+" REAL,"+key_src_name+" TEXT,"+key_dest_name+" TEXT,"+key_img_path+" TEXT"+")";
        db.execSQL(create_table_ride);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RIDE);

        // Create tables again
        onCreate(db);
    }
    public void addRide(RideDetails rd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(key_name,rd.getName());
        values.put(key_gender,rd.getGender());
        values.put(key_pref_comp,rd.getPref_comp());
        values.put(key_age,rd.getAge());
        values.put(key_contact,rd.getContact());
        values.put(key_hour,rd.getHour());
        values.put(key_min,rd.getMin());
        values.put(key_year,rd.getYear());
        values.put(key_month,rd.getMonth());
        values.put(key_day,rd.getDay());
        values.put(key_src_lat,rd.getSrc_lat());
        values.put(key_src_long,rd.getSrc_long());
        values.put(key_dest_lat,rd.getDest_lat());
        values.put(key_dest_long,rd.getDest_long());
        values.put(key_src_name,rd.getSrc_name_x());
        values.put(key_dest_name,rd.getDest_name_x());
        values.put(key_img_path,rd.getImg_path());
        db.insert(TABLE_RIDE, null, values);
        db.close();
    }
    public RideDetails getRide(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_RIDE,new String[]{key_id,key_name,key_gender,key_pref_comp,key_age,key_contact,key_hour,key_min,key_year,key_month,key_day,key_src_lat,key_src_long,key_dest_lat,key_dest_long,key_src_name,key_dest_name,key_img_path}, key_id+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        RideDetails rd=new RideDetails(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),Long.parseLong(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(8)),Integer.parseInt(cursor.getString(9)),Integer.parseInt(cursor.getString(10)),Double.parseDouble(cursor.getString(11)),Double.parseDouble(cursor.getString(12)),Double.parseDouble(cursor.getString(13)),Double.parseDouble(cursor.getString(14)),cursor.getString(15),cursor.getString(16),cursor.getString(17));
        return rd;
    }
    public List<RideDetails> getAllContacts() {
        List<RideDetails> contactList = new ArrayList<RideDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RIDE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RideDetails rd=new RideDetails(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),Long.parseLong(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(8)),Integer.parseInt(cursor.getString(9)),Integer.parseInt(cursor.getString(10)),Double.parseDouble(cursor.getString(11)),Double.parseDouble(cursor.getString(12)),Double.parseDouble(cursor.getString(13)),Double.parseDouble(cursor.getString(14)),cursor.getString(15),cursor.getString(16),cursor.getString(17));
                // Adding Ride to list
                contactList.add(rd);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
