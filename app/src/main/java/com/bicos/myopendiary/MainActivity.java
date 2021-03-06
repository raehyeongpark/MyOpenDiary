package com.bicos.myopendiary;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.DiaryListFragment;
import com.bicos.myopendiary.diary.WriteDiaryActivity;
import com.bicos.myopendiary.diary.WriteDiaryFragment;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.sidemenu.SideMenuFragment;
import com.bicos.myopendiary.util.ActivityUtils;
import com.bicos.myopendiary.util.DateUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // side menu setting
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), SideMenuFragment.newInstance(), R.id.container_side);
        DrawerLayout layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, layout, R.string.drawer_open, R.string.drawer_close);
        layout.addDrawerListener(mToggle);

        // main setting
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), DiaryListFragment.newInstance(), R.id.container_main);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WriteDiaryActivity.class));
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
