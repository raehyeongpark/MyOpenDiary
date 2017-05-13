package com.bicos.myopendiary.diary.detail;

import android.app.Activity;

import com.bicos.myopendiary.common.FirebaseWrapper;
import com.bicos.myopendiary.diary.data.Comment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentModel implements DetailDiaryCommentContract.Model {

    private Comment comment;

    private DatabaseReference reference;

    public DetailDiaryCommentModel() {

    }

    @Override
    public void setComment(Comment comment) {
        this.comment = comment;
        this.reference = FirebaseWrapper.getCommentReference(comment.getParentKey()).child(comment.getKey());
    }

    @Override
    public void requestModifyComment() {

    }

    @Override
    public void requestDeleteComment(Activity activity, OnCompleteListener<Void> listener) {
        reference.removeValue().addOnCompleteListener(activity, listener);
    }

    @Override
    public String getMsg() {
        return comment != null ? comment.getMsg() : null;
    }

    @Override
    public String getUid() {
        return comment != null ? comment.getUid() : null;
    }
}
