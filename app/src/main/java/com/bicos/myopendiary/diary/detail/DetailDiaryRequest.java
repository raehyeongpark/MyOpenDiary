package com.bicos.myopendiary.diary.detail;

import android.app.Activity;

import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by raehyeong.park on 2017. 4. 19..
 */

public class DetailDiaryRequest implements DetailDiaryContract.Request {

    private DatabaseReference mRef;

    private Diary mDiary;

    private String mKey;

    public DetailDiaryRequest(Diary diary, String key, ValueEventListener listener) {
        this.mDiary = diary;
        this.mKey = key;
        this.mRef = FirebaseDatabase.getInstance().getReference()
                .child(Constants.REF_DIARY)
                .child(Constants.TYPE_ALL.equals(diary.getType()) ? Constants.TYPE_ALL : diary.getUid())
                .child(DateUtils.getDate(mDiary.getDate()))
                .child(mKey);
        mRef.addValueEventListener(listener);
    }

    @Override
    public void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener) {
        mRef.removeValue().addOnCompleteListener(activity, listener);
    }

    @Override
    public void setDiary(Diary diary) {
        this.mDiary = diary;
    }

    @Override
    public String getTitle() {
        return mDiary.getTitle();
    }

    @Override
    public String getDesc() {
        return mDiary.getDesc();
    }

    @Override
    public Diary getDiary() {
        return mDiary;
    }

    @Override
    public String getKey() {
        return mKey;
    }
}
