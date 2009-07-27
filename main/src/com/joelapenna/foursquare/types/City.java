/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquare.types;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2009-07-26 20:59:17.636506
 * 
 * @author Joe LaPenna (joe@joelapenna.com)
 */
public class City implements Parcelable, FoursquareType {

    private String mGeolat;
    private String mGeolong;
    private String mId;
    private String mName;
    private String mShortname;
    private String mTimezone;

    public City() {
    }

    public String getGeolat() {
        return mGeolat;
    }

    public void setGeolat(String geolat) {
        mGeolat = geolat;
    }

    public String getGeolong() {
        return mGeolong;
    }

    public void setGeolong(String geolong) {
        mGeolong = geolong;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getShortname() {
        return mShortname;
    }

    public void setShortname(String shortname) {
        mShortname = shortname;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    /* For Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        boolean[] booleanArray = {

        };
        dest.writeBooleanArray(booleanArray);
        dest.writeString(this.mGeolat);
        dest.writeString(this.mGeolong);
        dest.writeString(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mShortname);
        dest.writeString(this.mTimezone);
    }

    private void readFromParcel(Parcel source) {
        boolean[] booleanArray = new boolean[0];
        source.readBooleanArray(booleanArray);
        this.mGeolat = source.readString();
        this.mGeolong = source.readString();
        this.mId = source.readString();
        this.mName = source.readString();
        this.mShortname = source.readString();
        this.mTimezone = source.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {

        @Override
        public City createFromParcel(Parcel source) {
            City instance = new City();
            instance.readFromParcel(source);
            return instance;
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }

    };

}
