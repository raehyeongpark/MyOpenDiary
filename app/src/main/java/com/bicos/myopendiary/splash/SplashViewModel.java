package com.bicos.myopendiary.splash;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by raehyeong.park on 2017. 3. 2..
 */

public class SplashViewModel extends BaseObservable {

    private SplashContract.View mView;

    private SplashContract.Request mRequest;

    private String mId;

    private String mPwd;

    private Activity mActivity;

    public SplashViewModel(Activity activity, SplashContract.View view, SplashContract.Request request) {
        mView = view;
        mRequest = request;
        mActivity = activity;
    }

    @Bindable
    public boolean isLogin() {
        return mRequest.isLogin();
    }

    public void addAuthStatusChangeListener(FirebaseAuth.AuthStateListener listener) {
        mRequest.addAuthStatusChangedListener(listener);
    }

    public void removeAuthStatusChangeListener(FirebaseAuth.AuthStateListener listener) {
        mRequest.removeAuthStatusChangedListener(listener);
    }

    public void setId(CharSequence id) {
        mId = (id != null) ? id.toString() : null;
    }

    public void setPwd(CharSequence pwd) {
        mPwd = (pwd != null) ? pwd.toString() : null;
    }

    public void clickLoginBtn(){

        if (!validateId()) {
            mView.invalidateId();
            return;
        }

        if (!validatePwd()) {
            mView.invalidatePwd();
            return;
        }

        mRequest.requestLogin(mActivity, mId, mPwd, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mView.successLogin();
                } else {
                    mView.failureLogin(task.getException());
                }
            }
        });
    }

    public void clickSignUpBtn(){

        if (!validateId()) {
            mView.invalidateId();
            return;
        }

        if (!validatePwd()) {
            mView.invalidatePwd();
            return;
        }

        mRequest.requestSignUp(mActivity, mId, mPwd, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mView.successSignUp();
                } else {
                    mView.failureSignUp(task.getException());
                }
            }
        });
    }

    private boolean validatePwd() {
        return !TextUtils.isEmpty(mPwd);
    }

    private boolean validateId() {
        return !TextUtils.isEmpty(mId) && mId.contains("@");
    }
}
