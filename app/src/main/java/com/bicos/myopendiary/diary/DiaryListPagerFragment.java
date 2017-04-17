package com.bicos.myopendiary.diary;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.bicos.myopendiary.R;
import com.bicos.myopendiary.diary.data.Category;
import com.bicos.myopendiary.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raehyeong.park on 2017. 3. 24..
 */

public class DiaryListPagerFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private String mDate;

    public DiaryListPagerFragment() {
    }

    public static DiaryListPagerFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putParcelable("category",category);

        DiaryListPagerFragment fragment = new DiaryListPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Category category = getArguments().getParcelable("category");

        if (mDate == null) {
            mDate = DateUtils.today();
        }

        View root = inflater.inflate(R.layout.fragment_diary_list_pager, container, false);

        ViewPager pager = (ViewPager) root.findViewById(R.id.pager_diary_list);

        List<Category> items = new ArrayList<>();
        if (category != null) {
            items.add(category);
        }
        items.add(new Category("전체", "all"));

        DiaryListViewPagerAdapter adapter = new DiaryListViewPagerAdapter(getFragmentManager(), items);
        pager.setAdapter(adapter);

        TabLayout layout = (TabLayout) root.findViewById(R.id.tab_layout);
        layout.setupWithViewPager(pager);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDate = DateUtils.getDate(year, month, dayOfMonth);
    }

    private class DiaryListViewPagerAdapter extends FragmentPagerAdapter{

        List<Category> items;

        DiaryListViewPagerAdapter(FragmentManager fm, List<Category> items) {
            super(fm);
            this.items = items;
        }

        @Override
        public Fragment getItem(int position) {
            return DiaryListFragment.newInstance(items.get(position), mDate);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).name;
        }
    }
}
