package com.bicos.myopendiary.diary.detail;

import com.bicos.myopendiary.diary.data.Comment;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentModel implements DetailDiaryCommentContract.Model {

    private Comment comment;

    @Override
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public void requestModifyComment() {

    }

    @Override
    public void requestDeleteComment() {

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
