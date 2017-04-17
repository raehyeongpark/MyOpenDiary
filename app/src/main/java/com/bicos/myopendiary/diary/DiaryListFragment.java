package com.bicos.myopendiary.diary;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

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

public class DiaryListFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public DiaryListFragment() {
    }

    public static DiaryListFragment newInstance(Category category, String date) {

        Bundle args = new Bundle();
        args.putParcelable("category", category);
        args.putString("date", date);

        DiaryListFragment fragment = new DiaryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private DatePickerDialog mDialog;
    private TextView mTvDate;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<Diary, DiaryViewHolder> mAdapter;

    private Category category;
    private String date;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.category = getArguments().getParcelable("category");

        this.date = getArguments().getString("date");

        if (date == null) {
            date = DateUtils.today();
        }

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]) - 1;
        int day = Integer.parseInt(date.split("-")[2]);

        mDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        View view = inflater.inflate(R.layout.fragment_diary_list, container, false);

        mTvDate = (TextView) view.findViewById(R.id.tv_current_date);
        mTvDate.setText(date);
        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_diary);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = createAdapter(FirebaseDatabase.getInstance()
                .getReference()
                .child(Constants.REF_DIARY)
                .child(category.value)
                .child(date));
        mRecyclerView.setAdapter(mAdapter);

        return view.getRootView();
    }

    FirebaseRecyclerAdapter<Diary, DiaryViewHolder> createAdapter(DatabaseReference ref) {
        return new FirebaseRecyclerAdapter<Diary, DiaryViewHolder>(Diary.class,
                R.layout.item_diray_list,
                DiaryViewHolder.class,
                ref) {

            @Override
            public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                DiaryViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setCategory(category);
                viewHolder.setDate(date);
                return viewHolder;
            }

            @Override
            protected void populateViewHolder(DiaryViewHolder viewHolder, final Diary model, int position) {
                viewHolder.populateViewHolder(model, getRef(position).getKey());
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = DateUtils.getDate(year, month, dayOfMonth);

        mTvDate.setText(date);
        mAdapter = createAdapter(FirebaseDatabase.getInstance()
                .getReference()
                .child(Constants.REF_DIARY)
                .child(category.value)
                .child(date));

        mRecyclerView.setAdapter(mAdapter);
    }
}
