package com.bicos.myopendiary.diary.detail;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.bicos.myopendiary.BR;
import com.bicos.myopendiary.diary.data.Comment;
import com.bicos.myopendiary.diary.data.Diary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class DetailDiaryViewModel extends BaseObservable {

    private DetailDiaryContract.View mView;

    private DetailDiaryContract.Request mRequest;

    public DetailDiaryViewModel(DetailDiaryContract.View view) {
        this.mView = view;
        this.mRequest = new DetailDiaryRequest();
    }

    public void setData(Diary data, String key) {
        mRequest.setData(data, key, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Diary diary = dataSnapshot.getValue(Diary.class);
                if (diary != null) {
                    mRequest.setDiary(diary);
                    notifyPropertyChanged(BR.dataList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.failureDeleteDiary(databaseError.toException());
            }
        }, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> commentList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    commentList.add(snapshot.getValue(Comment.class));
                }

                mRequest.setCommentList(commentList);
                notifyPropertyChanged(BR.dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.failureWriteComment(databaseError.toException());
            }
        });
    }

    public void clickModifyDiary() {
        mView.goModifyPage(mRequest.getDiary(), mRequest.getKey());
    }

    public void clickDeleteDiary(Activity activity) {
        mRequest.requestDeleteDiary(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mView.successDeleteDiary();
                } else {
                    mView.failureDeleteDiary(task.getException());
                }
            }
        });
    }

    public void clickWriteComment(Context context) {
        mRequest.requestWriteComment((Activity) context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                clearWriteComment();
                if (task.isSuccessful()) {
                    mView.successWriteComment();
                } else {
                    mView.failureWriteComment(task.getException());
                }
            }
        });
    }

    private void clearWriteComment() {
        mRequest.setWriteComment("");
        notifyPropertyChanged(BR.writeComment);
    }

    @Bindable
    public List<DetailDiaryAdapter.ItemWrapper> getDataList() {
        return mRequest.getDataList();
    }

    @Bindable
    public String getWriteComment() {
        return mRequest.getWriteComment();
    }

    public void inputComment(CharSequence comment) {
        if (comment != null) {
            mRequest.setWriteComment(comment.toString());
        }
        notifyPropertyChanged(BR.commentIsEmpty);
    }

    @Bindable
    public boolean getCommentIsEmpty() {
        return TextUtils.isEmpty(mRequest.getWriteComment());
    }

    @BindingAdapter("bind:setAdapter")
    public static void setAdapter(RecyclerView recyclerView, List<DetailDiaryAdapter.ItemWrapper> itemWrapperList) {
        if (recyclerView.getAdapter() != null) {
            DetailDiaryAdapter adapter = (DetailDiaryAdapter) recyclerView.getAdapter();
            adapter.setItemList(itemWrapperList);
        } else {
            DetailDiaryAdapter adapter = new DetailDiaryAdapter();
            adapter.setItemList(itemWrapperList);
            recyclerView.setAdapter(adapter);
        }
    }
}
