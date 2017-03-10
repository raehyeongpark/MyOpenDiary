package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class ModifyDiaryViewModel extends BaseObservable {

    private Diary mDiary;

    private Activity mActivity;

    private ModifyDiaryContract.View mView;

    private ModifyDiaryContract.Request mRequest;

    public ModifyDiaryViewModel(Activity activity, ModifyDiaryContract.View view, ModifyDiaryContract.Request request, Diary diary) {
        mDiary = diary;
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

    public String getTitle() {
        return mDiary.getTitle();
    }

    public String getDesc(){
        return mDiary.getDesc();
    }

    public void clickModifyDiary() {
        mRequest.requestModifyDiary(mDiary, mActivity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mView.successModifyDiary();
                } else {
                    mView.failureModifyDiary(task.getException());
                }
            }
        });
    }
}
