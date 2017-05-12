package com.bicos.myopendiary.diary.detail;

import android.support.v7.widget.RecyclerView;

import com.bicos.myopendiary.databinding.ItemDetailDiaryBinding;
import com.bicos.myopendiary.diary.data.Diary;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryViewHolder extends RecyclerView.ViewHolder {

    private ItemDetailDiaryBinding binding;

    public DetailDiaryViewHolder(ItemDetailDiaryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setData(Diary diary) {
        binding.setDiary(diary);
    }
}
