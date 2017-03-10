package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class ModifyDiaryContract {

    interface View {
        void successModifyDiary();

        void failureModifyDiary(Exception exception);
    }

    interface Request {
        void requestDiary(ValueEventListener listener);
        void requestModifyDiary(Diary diary, Activity activity, OnCompleteListener<Void> listener);
    }
}
