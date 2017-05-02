package com.bicos.myopendiary.diary.detail;

import com.bicos.myopendiary.diary.data.Comment;

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

        void setComment(Comment comment);

        void requestModifyComment();

        void requestDeleteComment();

        String getMsg();

        String getUid();
    }
}
