package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.bicos.myopendiary.R;
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

    public boolean longClickComment(final View view) {
        if (getIsCommentWrittenByMe()) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_comment, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_modify:
                            showEditCommentDialog((Activity) view.getContext());
                            return true;
                        case R.id.menu_delete:
                            clickDeleteComment(view.getContext());
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
            return true;
        } else {
            return false;
        }
    }

    private void showEditCommentDialog(final Activity activity) {
        view.showEditCommentDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                model.requestModifyComment(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            DetailDiaryCommentViewModel.this.view.setUiSuccessModifyComment();
                        } else {
                            DetailDiaryCommentViewModel.this.view.setUiFailure(task.getException());
                        }
                    }
                });
            }
        });
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

    public void modifyComment(CharSequence comment) {
        if (comment != null) {
            model.setCommentMsg(comment.toString());
            notifyPropertyChanged(BR.commentMsg);
        }
    }
}
