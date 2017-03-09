package com.bicos.myopendiary.diary.data;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class Diary {

    private String mTitle;

    private String mDesc;

    private String mUid;

    public Diary() {
    }

    public Diary(String title, String desc, String uid) {
        mTitle = title;
        mDesc = desc;
        mUid = uid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }
}
