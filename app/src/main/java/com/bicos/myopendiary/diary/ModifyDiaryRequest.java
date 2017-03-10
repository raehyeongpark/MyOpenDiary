package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.common.FirebaseWrapper;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.android.gms.tasks.OnCompleteListener;
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

    public ModifyDiaryRequest(String key) {
        mRef = FirebaseWrapper.getDiaryReference(DateUtils.today());
        mKey = key;
    }

    @Override
    public void requestModifyDiary(Diary diary, Activity activity, OnCompleteListener<Void> listener) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(mKey, diary.toMap());
        mRef.updateChildren(childUpdates).addOnCompleteListener(activity, listener);
    }

    @Override
    public void requestDiary(ValueEventListener listener) {
        mRef.child(mKey).addListenerForSingleValueEvent(listener);
    }
}
