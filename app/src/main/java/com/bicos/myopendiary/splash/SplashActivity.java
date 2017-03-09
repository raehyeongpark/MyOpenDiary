package com.bicos.myopendiary.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bicos.myopendiary.MainActivity;
import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by raehyeong.park on 2017. 3. 2..
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View, FirebaseAuth.AuthStateListener {

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new SplashViewModel(this, this, new SplashRequest());

        ActivitySplashBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        dataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.addAuthStatusChangeListener(this);
    }

    @Override
    protected void onStop() {
        mViewModel.removeAuthStatusChangeListener(this);
        super.onStop();
    }

    @Override
    public void successLogin() {
        Toast.makeText(this, "로그인을 성공하였습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureLogin(Exception e) {
        Toast.makeText(this, "로그인 실패! 다시 시도해주세요. " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successSignUp() {
        Toast.makeText(this, "회원가입을 성공하였습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureSignUp(Exception e) {
        Toast.makeText(this, "회원가입 실패! 다시 시도해주세요. " +e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidateId() {
        Toast.makeText(this, "정확한 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidatePwd() {
        Toast.makeText(this, "정확한 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() != null) {
            startMainActivity();
        }
    }

    void startMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
