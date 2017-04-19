package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.ActivityModifyDiaryBinding;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class ModifyDiaryActivity extends AppCompatActivity implements ModifyDiaryContract.View {

    private static final String EXTRA_DIARY = "diary";
    private static final String EXTRA_DIARY_KEY = "diary_key";

    public static final String TRANSITION_NAME_DIARY_CONTAINER = "diary_container";

    private ModifyDiaryViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent == null || !intent.hasExtra(EXTRA_DIARY_KEY) || !intent.hasExtra(EXTRA_DIARY)) {
            return;
        }

        ActivityModifyDiaryBinding mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify_diary);

        Diary diary = intent.getParcelableExtra(EXTRA_DIARY);
        String diaryKey = intent.getStringExtra(EXTRA_DIARY_KEY);

        ModifyDiaryRequest request = new ModifyDiaryRequest(diary.getCategory().value, diaryKey, DateUtils.getDate(diary.getDate()));
        mViewModel = new ModifyDiaryViewModel(this, this, request);
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onStart();
    }

    @Override
    public void successModifyDiary() {
        Toast.makeText(getApplicationContext(), R.string.toast_diary_modify_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void successDeleteDiary() {
        Toast.makeText(getApplicationContext(), R.string.toast_diary_delete_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failureModifyDiary(Exception exception) {
        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mViewModel.clickDeleteDiary();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static void startModifyDiaryActivityWithAnim(Activity activity, Diary diary, String key, View container) {
        Intent intent = new Intent(activity, ModifyDiaryActivity.class);
        intent.putExtra(EXTRA_DIARY, diary);
        intent.putExtra(EXTRA_DIARY_KEY, key);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && container != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, container, TRANSITION_NAME_DIARY_CONTAINER);
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
