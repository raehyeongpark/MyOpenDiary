package com.bicos.myopendiary.sidemenu;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class SideMenuViewModel extends BaseObservable {

    private SideMenuContract.View mView;

    private FirebaseAuth mAuth;

    private FirebaseUser mUser;

    public SideMenuViewModel(SideMenuContract.View view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Bindable
    public String getId(){
        return  mUser != null ? mUser.getEmail() : "-";
    }

    @Bindable
    public String getName() {
        return mUser != null ? mUser.getDisplayName() : "-";
    }

    public void clickLogout(){
        mAuth.signOut();
        mView.successLogout();
    }
}
