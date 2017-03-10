package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.ActivityDetailDiaryBinding;
import com.bicos.myopendiary.diary.data.Diary;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class DetailDiaryActivity extends AppCompatActivity {

    public static final String EXTRA_DIARY = "diary";

    public static final String TRANSITION_NAME_DIARY_CONTAINER = "diary_container";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent == null || !intent.hasExtra(EXTRA_DIARY)) {
            return;
        }

        Diary diary = intent.getParcelableExtra(EXTRA_DIARY);

        ActivityDetailDiaryBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_diary);

        dataBinding.setViewModel(new DetailDiaryViewModel(diary));
    }

    public static void startDetailDiaryActivity(Activity activity, Diary diary) {
        startDetailDiaryActivityWithAnim(activity, diary, null);
    }

    public static void startDetailDiaryActivityWithAnim(Activity activity, Diary diary, View container) {
        Intent intent = new Intent(activity, DetailDiaryActivity.class);
        intent.putExtra(EXTRA_DIARY, diary);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && container != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, container, TRANSITION_NAME_DIARY_CONTAINER);
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
