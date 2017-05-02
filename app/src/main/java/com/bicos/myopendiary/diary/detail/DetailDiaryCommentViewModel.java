package com.bicos.myopendiary.diary.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.bicos.myopendiary.diary.data.Comment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentViewModel extends BaseObservable{

    private DetailDiaryCommentContract.View view;

    private DetailDiaryCommentContract.Model model;

    public DetailDiaryCommentViewModel(DetailDiaryCommentContract.View view, DetailDiaryCommentContract.Model model) {
        this.view = view;
        this.model = model;
    }

    public void setComment(Comment comment) {
        model.setComment(comment);
        notifyChange();
    }

    public void clickDeleteComment() {
        model.requestDeleteComment();
    }

    @Bindable
    public String getCommentMsg(){
        return model.getMsg();
    }

    @Bindable
    public boolean getShowDeleteBtn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null && user.getUid().equalsIgnoreCase(model.getUid());
    }
}
