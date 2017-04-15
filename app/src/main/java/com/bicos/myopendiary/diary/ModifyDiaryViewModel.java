package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class ModifyDiaryViewModel extends BaseObservable {

    private Activity mActivity;

    private ModifyDiaryContract.View mView;

    private ModifyDiaryContract.Request mRequest;

    public ModifyDiaryViewModel(Activity activity, ModifyDiaryContract.View view, ModifyDiaryContract.Request request) {
        mActivity = activity;
        mView = view;
        mRequest = request;
    }

    public void inputTitle(CharSequence title) {
        if (title == null)
            return;

        mRequest.setTitle(title.toString());
    }

    public void inputDesc(CharSequence desc) {
        if (desc == null)
            return;

        mRequest.setDesc(desc.toString());
    }

    public String getTitle() {
        return mRequest.getTitle();
    }

    public String getDesc(){
        return mRequest.getDesc();
    }

    public void clickModifyDiary() {
        mRequest.requestModifyDiary(mActivity, new OnCompleteListener<Void>() {
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

    public void clickDeleteDiary() {
        mRequest.requestDeleteDiary(mActivity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mView.successDeleteDiary();
                } else {
                    mView.failureModifyDiary(task.getException());
                }
            }
        });
    }

    public void onStart() {
        mRequest.requestDiary(new ModifyDiaryRequest.DataChangedListener() {
            @Override
            public void onDataChanged() {
                notifyChange();
            }

            @Override
            public void onCanceled(Exception e) {
                mView.failureModifyDiary(e);
            }
        });
    }
}
