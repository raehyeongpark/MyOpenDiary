package com.bicos.myopendiary.diary.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.databinding.DialogEditDialogBinding;
import com.bicos.myopendiary.databinding.ItemDetailDiaryCommentBinding;
import com.bicos.myopendiary.diary.data.Comment;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryCommentViewHolder extends RecyclerView.ViewHolder implements DetailDiaryCommentContract.View {

    private DetailDiaryCommentViewModel viewModel;

    public DetailDiaryCommentViewHolder(ItemDetailDiaryCommentBinding binding) {
        super(binding.getRoot());
        viewModel = new DetailDiaryCommentViewModel(this, new DetailDiaryCommentModel());
        binding.setViewModel(viewModel);
    }

    public void setData(Comment comment) {
        viewModel.setComment(comment);
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

    @Override
    public void showEditCommentDialog(DialogInterface.OnClickListener onClickListener) {

        DialogEditDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(itemView.getContext()),
                R.layout.dialog_edit_dialog,null, false);
        binding.setViewModel(viewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext())
                .setView(binding.getRoot())
                .setPositiveButton("수정", onClickListener)
                .setNegativeButton("취소", null);
        builder.create().show();

    }
}
