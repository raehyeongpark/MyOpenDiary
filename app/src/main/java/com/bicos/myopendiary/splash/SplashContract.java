package com.bicos.myopendiary.splash;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by raehyeong.park on 2017. 3. 2..
 */

public class SplashContract {

    interface View {

        void successLogin();

        void failureLogin(Exception e);

        void successSignUp();

        void failureSignUp(Exception e);

        void invalidateId();

        void invalidatePwd();
    }

    interface Request {

        boolean isLogin();

        void addAuthStatusChangedListener(FirebaseAuth.AuthStateListener authStateListener);

        void removeAuthStatusChangedListener(FirebaseAuth.AuthStateListener authStateListener);

        void requestLogin(Activity activity, String id, String pwd, OnCompleteListener<AuthResult> listener);

        void requestSignUp(Activity activity, String id, String pwd, OnCompleteListener<AuthResult> listener);
    }
}
