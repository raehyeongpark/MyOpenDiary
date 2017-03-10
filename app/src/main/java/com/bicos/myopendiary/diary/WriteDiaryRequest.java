package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.text.TextUtils;

import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryRequest implements WriteDiaryContract.Request {

    private DatabaseReference mRef;

    public WriteDiaryRequest() {
        mRef = FirebaseDatabase.getInstance().getReference()
                .child(Constants.REF_DIARY)
                .child(DateUtils.today());

    }

    public void requestWriteDiary(Diary diary, Activity activity, OnCompleteListener<Void> listener) {

        if (TextUtils.isEmpty(diary.getUid())) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                return;
            }

            diary.setUid(user.getUid());
        }

        mRef.push().setValue(diary).addOnCompleteListener(listener);
    }
}
