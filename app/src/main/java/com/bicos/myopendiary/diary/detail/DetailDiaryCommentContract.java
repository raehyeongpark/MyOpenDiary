package com.bicos.myopendiary.diary.detail;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Comment;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentContract {

    public interface View {

        void setUiSuccessModifyComment();

        void setUiSuccessDeleteComment();

        void setUiFailure(Exception e);
    }

    public interface Model {

        void setComment(Comment comment, String commentKey);

        void requestModifyComment();

        void requestDeleteComment(Activity activity, OnCompleteListener<Void> listener);

        String getMsg();

        String getUid();
    }
}
