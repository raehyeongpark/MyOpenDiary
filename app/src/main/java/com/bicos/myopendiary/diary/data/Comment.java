package com.bicos.myopendiary.diary.data;

import android.databinding.Bindable;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class Comment {

    private String key;
    private String uid;
    private String msg;
    private long date;

    public Comment() {
    }

    public Comment(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
