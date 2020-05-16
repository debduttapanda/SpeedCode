package com.coderusk.speedcode.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharp {
    private static final String PREFS_NAME = "COM_BIKRIMART_PARTNER_PREFERENCE";
    private static final String ID = "HASH";
    private static final String STORE_VERIFIED = "STORE_VERIFIED";
    private static final String STORE_NAME = "STORE_NAME";
    private static final String STORE_OWNER_NAME = "STORE_OWNER_NAME";
    private static final String PAYMENT_OPTIONS = "PAYMENT_OPTIONS";
    private static final String UPI_SAVED = "UPI_SAVED";
    private static final String LATE_UPI_PENDING = "LATE_UPI_PENDING";
    private SharedPreferences sharedPreferences;
    private Context context;
    ///////////////////////////////
    private static final String MOBILE = "MOBILE";

    public Sharp(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    }

    public void setMobile(String mobile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public String getMobile() {
        return sharedPreferences.getString(MOBILE,"");
    }

    public void clear()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void setId(String hash) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, hash);
        editor.commit();
    }
    public String getId() {
        return sharedPreferences.getString(ID,"");
    }


    public void setStoreVerified(boolean yes) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(STORE_VERIFIED, yes);
        editor.commit();
    }
    public boolean storeVerified() {
        return sharedPreferences.getBoolean(STORE_VERIFIED,false);
    }

    public void setStoreName(String storeName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORE_NAME, storeName);
        editor.commit();
    }

    public String getStoreName() {
        return sharedPreferences.getString(STORE_NAME,"");
    }

    public void setStoreOwnerName(String store_owner_name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORE_OWNER_NAME, store_owner_name);
        editor.commit();
    }

    public String getStoreOwnerName() {
        return sharedPreferences.getString(STORE_OWNER_NAME,"");
    }
    //////////////////////////////////////////////////////////
    public void setPaymentOptions(String paymentOptions) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PAYMENT_OPTIONS, paymentOptions);
        editor.commit();
    }

    public String getPaymentOptions() {
        return sharedPreferences.getString(PAYMENT_OPTIONS,"");
    }
///////////////////////
    public void setPaymentDetailsSaved(boolean b) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(UPI_SAVED, b);
        editor.commit();
    }

    public boolean getPaymentDetailsSaved() {
        return sharedPreferences.getBoolean(UPI_SAVED,false);
    }

    public void setLateUpiDetailsPending(boolean b) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LATE_UPI_PENDING, b);
        editor.commit();
    }

    public boolean getLateUpiDetailsPending() {
        return sharedPreferences.getBoolean(LATE_UPI_PENDING,false);
    }
}