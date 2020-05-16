package com.coderusk.speedcode.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrashReported implements Serializable, Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private String data;
    public final static Creator<CrashReported> CREATOR = new Creator<CrashReported>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CrashReported createFromParcel(Parcel in) {
            return new CrashReported(in);
        }

        public CrashReported[] newArray(int size) {
            return (new CrashReported[size]);
        }

    };
    private final static long serialVersionUID = -6222989242578364130L;

    protected CrashReported(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CrashReported() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public CrashReported(String status, String message, String data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}