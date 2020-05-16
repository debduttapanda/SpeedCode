package com.coderusk.speedcode.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrashData implements Serializable, Parcelable
{

    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("user_device")
    @Expose
    private String userDevice;
    @SerializedName("data")
    @Expose
    private String data;
    public final static Creator<CrashData> CREATOR = new Creator<CrashData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CrashData createFromParcel(Parcel in) {
            return new CrashData(in);
        }

        public CrashData[] newArray(int size) {
            return (new CrashData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5164215477424990433L;

    protected CrashData(Parcel in) {
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.userMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.userDevice = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CrashData() {
    }

    /**
     *
     * @param data
     * @param userMobile
     * @param userDevice
     * @param userType
     * @param userId
     */
    public CrashData(String userType, String userId, String userMobile, String userDevice, String data) {
        super();
        this.userType = userType;
        this.userId = userId;
        this.userMobile = userMobile;
        this.userDevice = userDevice;
        this.data = data;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userType);
        dest.writeValue(userId);
        dest.writeValue(userMobile);
        dest.writeValue(userDevice);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}
