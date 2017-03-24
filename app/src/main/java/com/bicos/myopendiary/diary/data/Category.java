package com.bicos.myopendiary.diary.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raehyeong.park on 2017. 3. 24..
 */

public class Category implements Parcelable {

    public String name;
    public String value;

    public Category(String name, String value) {
        this.name = name;
        this.value = value;
    }

    protected Category(Parcel in) {
        name = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
