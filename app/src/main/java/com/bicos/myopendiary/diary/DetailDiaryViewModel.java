package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.bicos.myopendiary.MainActivity;
import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class DetailDiaryViewModel extends BaseObservable implements ValueEventListener {

    private DetailDiaryContract.View mView;

    private DetailDiaryContract.Request mRequest;

    public DetailDiaryViewModel(DetailDiaryContract.View view, Diary diary, String key) {
        this.mView = view;
        this.mRequest = new DetailDiaryRequest(diary, key, this);
    }

    @Bindable
    public String getTitle() {
        return mRequest.getTitle();
    }

    @Bindable
    public String getDesc() {
        return mRequest.getDesc();
    }

    public void clickModifyDiary(){
        mView.goModifyPage(mRequest.getDiary(), mRequest.getKey());
    }

    public void clickDeleteDiary(Activity activity) {
        mRequest.requestDeleteDiary(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mView.successDeleteDiary();
                } else {
                    mView.failureDeleteDiary(task.getException());
                }
            }
        });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Diary diary = dataSnapshot.getValue(Diary.class);
        if(diary != null) {
            mRequest.setDiary(diary);
            notifyChange();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        mView.failureDeleteDiary(databaseError.toException());
    }
}
