package com.bicos.myopendiary.diary.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bicos.myopendiary.databinding.ItemDetailDiaryCommentBinding;
import com.bicos.myopendiary.diary.data.Comment;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentViewHolder extends RecyclerView.ViewHolder{

    private ItemDetailDiaryCommentBinding binding;

    public DetailDiaryCommentViewHolder(ItemDetailDiaryCommentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setData(Comment comment) {
        binding.setComment(comment);
    }
}
