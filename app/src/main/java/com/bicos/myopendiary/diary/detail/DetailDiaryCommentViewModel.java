package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public void setComment(Comment comment) {
        model.setComment(comment);
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
    public boolean getIsCommentWrittenByMe() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null && user.getUid().equalsIgnoreCase(model.getUid());
    }

    @BindingAdapter("setLayoutGravity")
    public static void setLayoutGravity(TextView view, boolean isMyComment) {
        RelativeLayout.LayoutParams layoutParam = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (isMyComment) {
            layoutParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            layoutParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        } else {
            layoutParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            layoutParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        }
    }
}
