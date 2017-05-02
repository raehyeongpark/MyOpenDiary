package com.bicos.myopendiary.diary.detail;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bicos.myopendiary.databinding.ItemDetailDiaryCommentBinding;
import com.bicos.myopendiary.diary.data.Comment;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentViewHolder extends RecyclerView.ViewHolder implements DetailDiaryCommentContract.View {

    private ItemDetailDiaryCommentBinding binding;

    public DetailDiaryCommentViewHolder(ItemDetailDiaryCommentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setData(Comment comment) {
        binding.setComment(comment);
    }

    @Override
    public void setUiSuccessModifyComment() {
        Toast.makeText(itemView.getContext(), "댓글 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUiSuccessDeleteComment() {
        Toast.makeText(itemView.getContext(), "댓글 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUiFailure(Exception e) {
        Toast.makeText(itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
