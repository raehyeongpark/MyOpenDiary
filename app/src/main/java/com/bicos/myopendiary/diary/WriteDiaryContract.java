package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryContract {

    interface View {

        void successWriteDiary();

        void failureWriteDiary(Exception exception);
    }

    interface Request {
        void requestWriteDiary(Diary diary, boolean isPublic,  Activity activity, DatabaseReference.CompletionListener listener);
    }
}
