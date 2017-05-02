package com.bicos.myopendiary.diary.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class Comment extends BaseObservable {

    private String uid;
    private String msg;
    private long date;

    public Comment() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Bindable
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
