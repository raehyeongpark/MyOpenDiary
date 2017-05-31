package com.bicos.myopendiary.diary.detail;

import android.app.Activity;

import com.bicos.myopendiary.diary.data.Comment;
import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by raehyeong.park on 2017. 4. 19..
 */

public class DetailDiaryContract {

    public interface View {
        void goModifyPage(Diary diary, String key);

        void successDeleteDiary();

        void failureDeleteDiary(Exception exception);

        void successWriteComment();

        void failureWriteComment(Exception exception);
    }

    public interface Request {
        void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener);

        void setData(Diary diary, String key, ValueEventListener listener, ValueEventListener listener2);

        String getTitle();

        String getDesc();

        Diary getDiary();

        String getKey();

        void setDiary(Diary diary);

        void setCommentList(List<Comment> commentList);

        void setWriteComment(String comment);

        String getWriteComment();

        void requestWriteComment(Activity activity, OnCompleteListener<Void> listener);

        List<Comment> getCommentList();

        void cleanUp();
    }
}
