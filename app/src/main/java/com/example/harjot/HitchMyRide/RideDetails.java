package com.example.harjot.HitchMyRide;

/**
 * Created by Harjot on 30-Dec-15.
 */
public class RideDetails {
    private int id;
    private String name;
    private String gender;
    private String pref_comp;
    private int age;
    private long contact;
    private int hour;
    private int min;
    private int year;
    private int month;
    private int day;
    private double src_lat;
    private double src_long;
    private double dest_lat;
    private double dest_long;
    private String src_name_x;
    private String dest_name_x;
    public RideDetails(int id,String name, String gender, String pref_comp, int age, long contact, int hour, int min, int year, int month, int day, double src_lat, double src_lang, double dest_lat, double dest_long, String src_name_x,String dest_name_x) {
        this.id=id;
        this.name = name;
        this.gender = gender;
        this.pref_comp = pref_comp;
        this.age = age;
        this.contact = contact;
        this.hour = hour;
        this.min = min;
        this.year = year;
        this.month = month;
        this.day = day;
        this.src_lat = src_lat;
        this.src_long = src_lang;
        this.dest_lat = dest_lat;
        this.dest_long = dest_long;
        this.src_name_x=src_name_x;
        this.dest_name_x=dest_name_x;
    }

    public String getSrc_name_x() {
        return src_name_x;
    }

    public void setSrc_name_x(String src_name_x) {
        this.src_name_x = src_name_x;
    }

    public String getDest_name_x() {
        return dest_name_x;
    }

    public void setDest_name_x(String dest_name_x) {
        this.dest_name_x = dest_name_x;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSrc_lat() {
        return src_lat;
    }

    public void setSrc_lat(double src_lat) {
        this.src_lat = src_lat;
    }

    public double getSrc_long() {
        return src_long;
    }

    public void setSrc_long(double src_lang) {
        this.src_long = src_lang;
    }

    public double getDest_lat() {
        return dest_lat;
    }

    public void setDest_lat(double dest_lat) {
        this.dest_lat = dest_lat;
    }

    public double getDest_long() {
        return dest_long;
    }

    public void setDest_long(double dest_long) {
        this.dest_long = dest_long;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPref_comp() {
        return pref_comp;
    }

    public void setPref_comp(String pref_comp) {
        this.pref_comp = pref_comp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
