package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.bicos.myopendiary.diary.data.Comment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentViewModel extends BaseObservable {

    private DetailDiaryCommentContract.View view;

    private DetailDiaryCommentContract.Model model;

    public DetailDiaryCommentViewModel(DetailDiaryCommentContract.View view, DetailDiaryCommentContract.Model model) {
        this.view = view;
        this.model = model;
    }

    public void setComment(Comment comment, String commentKey) {
        model.setComment(comment, commentKey);
        notifyChange();
    }

    public void clickDeleteComment(Context context) {
        model.requestDeleteComment((Activity) context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    view.setUiSuccessDeleteComment();
                } else {
                    view.setUiFailure(task.getException());
                }
            }
        });
    }

    @Bindable
    public String getCommentMsg() {
        return model.getMsg();
    }

    @Bindable
    public boolean getShowDeleteBtn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null && user.getUid().equalsIgnoreCase(model.getUid());
    }
}
