package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by raehyeong.park on 2017. 4. 19..
 */

public class DetailDiaryContract {

    public interface View {
        void goModifyPage(Diary diary, String key);

        void successDeleteDiary();

        void failureDeleteDiary(Exception exception);
    }

    public interface Request {
        void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener);

        void setDiary(Diary diary);

        String getTitle();

        String getDesc();

        Diary getDiary();

        String getKey();
    }
}
