package com.bicos.myopendiary.diary.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.bicos.myopendiary.BR;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

@IgnoreExtraProperties
public class Diary extends BaseObservable implements Parcelable {

    private String mTitle;

    private String mDesc;

    private String mUid;

    private String mType;

    private long mDate;

    private String mCommentKey;

    public Diary() {
    }

    public Diary(String title, String desc, String uid, Category category, long date) {
        mTitle = title;
        mDesc = desc;
        mUid = uid;
        mDate = date;
    }

    protected Diary(Parcel in) {
        mTitle = in.readString();
        mDesc = in.readString();
        mUid = in.readString();
        mType = in.readString();
        mDate = in.readLong();
        mCommentKey = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDesc);
        dest.writeString(mUid);
        dest.writeString(mType);
        dest.writeLong(mDate);
        dest.writeString(mCommentKey);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Diary> CREATOR = new Creator<Diary>() {
        @Override
        public Diary createFromParcel(Parcel in) {
            return new Diary(in);
        }

        @Override
        public Diary[] newArray(int size) {
            return new Diary[size];
        }
    };

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
        notifyPropertyChanged(BR.desc);
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    @Bindable
    public long getDate() {
        return mDate;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
        notifyPropertyChanged(BR.date);
    }

    public String getCommentKey() {
        return mCommentKey;
    }

    public void setCommentKey(String commentKey) {
        this.mCommentKey = commentKey;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", mTitle);
        result.put("desc", mDesc);
        result.put("uid", mUid);
        result.put("type", mType);
        result.put("date", mDate);
        result.put("commentKey", mCommentKey);
        return result;
    }
}
