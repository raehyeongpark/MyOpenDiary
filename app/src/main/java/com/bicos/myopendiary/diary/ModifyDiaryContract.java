package com.bicos.myopendiary.diary;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class ModifyDiaryContract {

    interface View {

        void successModifyDiary();

        void successDeleteDiary();

        void failureModifyDiary(Exception exception);
    }

    interface Request {

        void requestDiary(ModifyDiaryRequest.DataChangedListener listener);

        void requestModifyDiary(Activity activity, OnCompleteListener<Void> listener);

        void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener);

        void setTitle(String title);

        void setDesc(String desc);

        String getTitle();

        String getDesc();
    }
}
