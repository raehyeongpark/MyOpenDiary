package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.ActivityDetailDiaryBinding;
import com.bicos.myopendiary.diary.modify.ModifyDiaryActivity;
import com.bicos.myopendiary.diary.data.Diary;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class DetailDiaryActivity extends AppCompatActivity implements DetailDiaryContract.View {

    public static final String EXTRA_DIARY = "diary";

    public static final String EXTRA_KEY = "key";

    public static final String TRANSITION_NAME_DIARY_CONTAINER = "diary_container";

    private Diary diary;

    private DetailDiaryViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent == null || !intent.hasExtra(EXTRA_DIARY)) {
            return;
        }

        this.diary = intent.getParcelableExtra(EXTRA_DIARY);
        String key = intent.getStringExtra(EXTRA_KEY);

        viewModel = new DetailDiaryViewModel(this);
        viewModel.setData(diary, key);

        ActivityDetailDiaryBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_diary);
        dataBinding.setViewModel(viewModel);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_detail_diary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getUid().equals(diary.getUid())) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_detail_diary, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_modify:
                viewModel.clickModifyDiary();
                return true;
            case R.id.action_delete:
                viewModel.clickDeleteDiary(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goModifyPage(Diary diary, String key) {
        ModifyDiaryActivity.startModifyDiaryActivityWithAnim(this, diary, key, null);
    }

    @Override
    public void successDeleteDiary() {
        Toast.makeText(this, R.string.toast_diary_delete_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failureDeleteDiary(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void successWriteComment() {
        Toast.makeText(this, "댓글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureWriteComment(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static void startDetailDiaryActivity(Activity activity, Diary diary, String key) {
        Intent intent = new Intent(activity, DetailDiaryActivity.class);
        intent.putExtra(EXTRA_DIARY, diary);
        intent.putExtra(EXTRA_KEY, key);
        activity.startActivity(intent);
    }
}
