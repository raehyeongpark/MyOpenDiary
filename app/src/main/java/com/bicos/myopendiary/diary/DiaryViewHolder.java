package com.bicos.myopendiary.diary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bicos.myopendiary.R;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class DiaryViewHolder extends RecyclerView.ViewHolder {

    TextView title;

    TextView desc;

    public DiaryViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_title);
        desc = (TextView) itemView.findViewById(R.id.tv_desc);
    }


}
