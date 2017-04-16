package com.bicos.myopendiary.diary;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.diary.data.Category;
import com.bicos.myopendiary.diary.data.Diary;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private TextView desc;
    private Category category;
    private String key;

    public DiaryViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_title);
        desc = (TextView) itemView.findViewById(R.id.tv_desc);

        itemView.setOnClickListener(this);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void populateViewHolder(Diary diary, String key) {
        title.setText(diary.getTitle());
        desc.setText(diary.getDesc());
        this.key = key;
    }


    @Override
    public void onClick(View view) {
        Context context = itemView.getContext();
        if (context instanceof Activity) {
            ModifyDiaryActivity.startModifyDiaryActivityWithAnim((Activity) context,
                    category,
                    key,
                    itemView);
        }
    }
}
