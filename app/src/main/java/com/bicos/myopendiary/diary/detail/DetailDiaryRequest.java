package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.text.TextUtils;

import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.data.Comment;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by raehyeong.park on 2017. 4. 19..
 */

public class DetailDiaryRequest implements DetailDiaryContract.Request {

    private DatabaseReference mRef;

    private DatabaseReference mCommentRef;

    private Diary mDiary;

    private String mKey;

    private List<Comment> mCommentList;

    private String mWriteComment;

    public DetailDiaryRequest() {
        mCommentList = new ArrayList<>();
    }

    @Override
    public void setData(Diary diary, String key, ValueEventListener diaryListener, ValueEventListener commentListener) {
        this.mDiary = diary;
        this.mKey = key;
        this.mRef = FirebaseDatabase.getInstance().getReference()
                .child(Constants.REF_DIARY)
                .child(Constants.TYPE_ALL.equals(diary.getType()) ? Constants.TYPE_ALL : diary.getUid())
                .child(DateUtils.getDate(mDiary.getDate()))
                .child(mKey);
        mRef.addValueEventListener(diaryListener);

        if (!TextUtils.isEmpty(mDiary.getCommentKey())){
            mCommentRef = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.REF_COMMENT)
                    .child(mDiary.getCommentKey());
            mCommentRef.addValueEventListener(commentListener);
        }
    }

    @Override
    public void requestDeleteDiary(Activity activity, OnCompleteListener<Void> listener) {
        mRef.removeValue().addOnCompleteListener(activity, listener);
    }

    @Override
    public void setDiary(Diary diary) {
        this.mDiary = diary;
    }

    @Override
    public void setCommentList(List<Comment> commentList) {
        this.mCommentList = commentList;
    }

    @Override
    public List<DetailDiaryAdapter.ItemWrapper> getDataList() {
        List<DetailDiaryAdapter.ItemWrapper> list = new ArrayList<>();

        if (mDiary != null) {
            list.add(new DetailDiaryAdapter.ItemWrapper(mDiary, DetailDiaryAdapter.TYPE_DIARY));
        }

        if (!mCommentList.isEmpty()) {
            for (Comment comment : mCommentList) {
                list.add(new DetailDiaryAdapter.ItemWrapper(comment, DetailDiaryAdapter.TYPE_COMMENT));
            }
        }

        return list;
    }

    @Override
    public void setWriteComment(String comment) {
        mWriteComment = comment;
    }

    @Override
    public String getWriteComment() {
        return mWriteComment;
    }

    @Override
    public void requestWriteComment(Activity activity, OnCompleteListener<Void> listener){

        String key = mCommentRef.push().getKey();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        Comment comment = new Comment();
        comment.setDate(Calendar.getInstance().getTimeInMillis());
        comment.setUid(user.getUid());
        comment.setMsg(mWriteComment);

        mCommentRef.child(key).setValue(comment).addOnCompleteListener(activity, listener);
    }

    @Override
    public String getTitle() {
        return mDiary != null ? mDiary.getTitle() : null;
    }

    @Override
    public String getDesc() {
        return mDiary != null ? mDiary.getDesc() : null;
    }

    @Override
    public Diary getDiary() {
        return mDiary;
    }

    @Override
    public String getKey() {
        return mKey;
    }
}
