package com.bicos.myopendiary.splash;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by raehyeong.park on 2017. 3. 2..
 */

public class SplashRequest implements SplashContract.Request {

    private FirebaseAuth mAuth;

    public SplashRequest() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isLogin() {
        return mAuth.getCurrentUser() != null;
    }

    public void addAuthStatusChangedListener(FirebaseAuth.AuthStateListener authStateListener) {
        mAuth.addAuthStateListener(authStateListener);
    }

    public void removeAuthStatusChangedListener(FirebaseAuth.AuthStateListener authStateListener) {
        mAuth.removeAuthStateListener(authStateListener);
    }

    public void requestLogin(Activity activity, String id, String pwd, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(id, pwd)
                .addOnCompleteListener(activity, listener);
    }

    public void requestSignUp(Activity activity, String id, String pwd, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(id, pwd)
                .addOnCompleteListener(activity, listener);
    }
}
