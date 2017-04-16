package com.bicos.myopendiary.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.common.Constants;
import com.bicos.myopendiary.diary.data.Category;
import com.bicos.myopendiary.diary.data.Diary;
import com.bicos.myopendiary.util.DateUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class DiaryListFragment extends Fragment {

    public DiaryListFragment() {
    }

    public static DiaryListFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putParcelable("category", category);

        DiaryListFragment fragment = new DiaryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FirebaseRecyclerAdapter<Diary, DiaryViewHolder> mAdapter;

    private Category category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.category = getArguments().getParcelable("category");

        View view = inflater.inflate(R.layout.fragment_diary_list, container, false);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference()
                .child(Constants.REF_DIARY)
                .child(category.value)
                .child(DateUtils.today());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_diary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new FirebaseRecyclerAdapter<Diary, DiaryViewHolder>(Diary.class,
                R.layout.item_diray_list,
                DiaryViewHolder.class,
                ref) {

            @Override
            public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                DiaryViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setCategory(category);
                return viewHolder;
            }

            @Override
            protected void populateViewHolder(DiaryViewHolder viewHolder, final Diary model, int position) {
                viewHolder.populateViewHolder(model, getRef(position).getKey());
            }
        };
        recyclerView.setAdapter(mAdapter);

        return view.getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
