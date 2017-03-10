package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryContract {

    interface View {

        void successWriteDiary();

        void failureWriteDiary(Exception exception);
    }

    interface Request {
        void requestWriteDiary(Diary diary, Activity activity, OnCompleteListener<Void> listener);
    }
}
