package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.text.TextUtils;

import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryRequest implements WriteDiaryContract.Request {

    private DatabaseReference mRef;

    public WriteDiaryRequest() {
        mRef = FirebaseDatabase.getInstance().getReference().child(Constants.REF_DIARY);

    }

    public void requestWriteDiary(final Diary diary, boolean isPublic, Activity activity, DatabaseReference.CompletionListener listener) {

        if (TextUtils.isEmpty(diary.getUid())) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                return;
            }

            diary.setUid(user.getUid());
        }

        String key = mRef.child(diary.getUid()).child(DateUtils.today()).push().getKey();
        Map<String, Object> diaryValues = diary.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/"+diary.getUid() +"/" + DateUtils.today() + "/" + key, diaryValues);
        if (isPublic) {
            childUpdates.put("/all/" + DateUtils.today() + "/" + key, diaryValues);
        }
        mRef.updateChildren(childUpdates, listener);
    }
}
