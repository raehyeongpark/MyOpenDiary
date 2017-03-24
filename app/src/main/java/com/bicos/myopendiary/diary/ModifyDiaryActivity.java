package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.ActivityModifyDiaryBinding;
import com.bicos.myopendiary.diary.data.Category;
import com.bicos.myopendiary.diary.data.Diary;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class ModifyDiaryActivity extends AppCompatActivity implements ValueEventListener, ModifyDiaryContract.View {

    private static final String EXTRA_DIARY_KEY = "diary_key";
    private static final String EXTRA_CATEGORY = "category";

    public static final String TRANSITION_NAME_DIARY_CONTAINER = "diary_container";

    private ActivityModifyDiaryBinding mDataBinding;

    private ModifyDiaryContract.Request mRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent == null || !intent.hasExtra(EXTRA_DIARY_KEY)) {
            return;
        }

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify_diary);

        String category = intent.getStringExtra(EXTRA_CATEGORY);
        String diaryKey = intent.getStringExtra(EXTRA_DIARY_KEY);
        mRequest = new ModifyDiaryRequest(category, diaryKey);

        mRequest.requestDiary(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Diary diary = dataSnapshot.getValue(Diary.class);
        mDataBinding.setViewModel(new ModifyDiaryViewModel(this, this, mRequest, diary));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successModifyDiary() {
        Toast.makeText(getApplicationContext(), "일기를 수정하였습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failureModifyDiary(Exception exception) {
        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static void startModifyDiaryActivityWithAnim(Activity activity, Category category, String key, View container) {
        Intent intent = new Intent(activity, ModifyDiaryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category.value);
        intent.putExtra(EXTRA_DIARY_KEY, key);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && container != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, container, TRANSITION_NAME_DIARY_CONTAINER);
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
