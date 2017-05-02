package com.bicos.myopendiary.diary.write;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.util.ActivityUtils;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_write_diary);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                WriteDiaryFragment.newInstance(),
                R.id.container_write_diary);
    }

}
