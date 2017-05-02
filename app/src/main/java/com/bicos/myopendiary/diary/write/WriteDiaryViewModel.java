package com.bicos.myopendiary.diary.write;

import android.app.Activity;
import android.databinding.BaseObservable;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryViewModel extends BaseObservable {

    private Diary mDiary;

    private Activity mActivity;

    private WriteDiaryContract.View mView;

    private WriteDiaryContract.Request mRequest;

    public WriteDiaryViewModel(Activity activity, WriteDiaryContract.View view, WriteDiaryContract.Request request) {
        mDiary = new Diary();
        mActivity = activity;
        mView = view;
        mRequest = request;
    }

    public void inputTitle(CharSequence title) {
        if (title == null)
            return;

        mDiary.setTitle(title.toString());
    }

    public void inputDesc(CharSequence desc) {
        if (desc == null)
            return;

        mDiary.setDesc(desc.toString());
    }

    public void clickWriteDiary() {
        mRequest.requestWriteDiary(mDiary, true, mActivity, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mView.successWriteDiary();
                } else {
                    mView.failureWriteDiary(databaseError.toException());
                }
            }
        });
    }
}
