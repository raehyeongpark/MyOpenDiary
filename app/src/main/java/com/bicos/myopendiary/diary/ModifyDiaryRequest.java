package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.common.FirebaseWrapper;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class ModifyDiaryRequest implements ModifyDiaryContract.Request {

    private DatabaseReference mRef;

    private String mKey;

    private Diary mDiary;

    public ModifyDiaryRequest(String key) {
        mRef = FirebaseWrapper.getDiaryReference(DateUtils.today());
        mKey = key;
    }

    @Override
    public void requestDiary(final DataChangedListener listener) {
        mRef.child(mKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDiary = dataSnapshot.getValue(Diary.class);
                listener.onDataChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onCanceled(databaseError.toException());
            }
        });
    }

    @Override
    public void requestModifyDiary(Activity activity, OnCompleteListener<Void> listener) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(mKey, mDiary.toMap());
        mRef.updateChildren(childUpdates).addOnCompleteListener(activity, listener);
    }

    @Override
    public void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener) {
        mRef.child(mKey).removeValue().addOnCompleteListener(activity, listener);
    }

    @Override
    public void setTitle(String title) {
        if (mDiary != null) {
            mDiary.setTitle(title);
        }
    }

    @Override
    public void setDesc(String desc) {
        if (mDiary != null) {
            mDiary.setDesc(desc);
        }
    }

    @Override
    public String getTitle() {
        return mDiary != null ? mDiary.getTitle() : null;
    }

    @Override
    public String getDesc() {
        return mDiary != null ? mDiary.getDesc() : null;
    }

    public interface DataChangedListener {
        void onDataChanged();
        void onCanceled(Exception e);
    }
}
