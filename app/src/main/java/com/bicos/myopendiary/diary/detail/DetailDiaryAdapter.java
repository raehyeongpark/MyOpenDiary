package com.bicos.myopendiary.diary.detail;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bicos.myopendiary.databinding.ItemDetailDiaryBinding;
import com.bicos.myopendiary.databinding.ItemDetailDiaryCommentBinding;
import com.bicos.myopendiary.diary.data.Comment;
import com.bicos.myopendiary.diary.data.Diary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raehyeong.park on 2017. 5. 2..
 */

public class DetailDiaryAdapter extends Adapter {

    public static int TYPE_DIARY = 1;

    public static int TYPE_COMMENT = 2;

    private List<ItemWrapper> itemList;

    public DetailDiaryAdapter() {
        itemList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DIARY) {
            ItemDetailDiaryBinding binding = ItemDetailDiaryBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false);
            return new DetailDiaryViewHolder(binding);
        } else {
            ItemDetailDiaryCommentBinding binding = ItemDetailDiaryCommentBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false);
            return new DetailDiaryCommentViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DetailDiaryViewHolder) {
            ((DetailDiaryViewHolder) holder).setData((Diary) itemList.get(position).getObject());
        } else {
            ((DetailDiaryCommentViewHolder) holder).setData((Comment) itemList.get(position).getObject());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getViewType();
    }

    public void addItemList(ItemWrapper item) {
        this.itemList.add(item);
        notifyItemInserted(itemList.size());
    }

    public void setDataList(List<ItemWrapper> dataList) {
        this.itemList = dataList;
        notifyDataSetChanged();
    }

    public static class ItemWrapper {

        private Object object;
        private int viewType;

        public ItemWrapper(Object object, int viewType) {
            this.viewType = viewType;
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public int getViewType() {
            return viewType;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
