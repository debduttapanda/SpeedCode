package com.coderusk.speedcode.app;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParamData implements Serializable, Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<ParamData> CREATOR = new Creator<ParamData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ParamData createFromParcel(Parcel in) {
            return new ParamData(in);
        }

        public ParamData[] newArray(int size) {
            return (new ParamData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7083714657345334720L;

    protected ParamData(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ParamData() {
    }

    /**
     *
     * @param title
     * @param value
     */
    public ParamData(String title, String value) {
        super();
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}
