package com.bicos.myopendiary.diary;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.bicos.myopendiary.diary.data.Diary;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class DetailDiaryViewModel extends BaseObservable {

    private Diary mDiary;

    public DetailDiaryViewModel(Diary mDiary) {
        this.mDiary = mDiary;
    }

    public void setDiary(Diary diary) {
        mDiary = diary;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mDiary.getTitle();
    }

    @Bindable
    public String getDesc() {
        return mDiary.getDesc();
    }
}
