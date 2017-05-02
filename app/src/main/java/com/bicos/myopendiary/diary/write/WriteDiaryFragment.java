package com.bicos.myopendiary.diary.write;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bicos.myopendiary.databinding.FragmentWriteDiaryBinding;

/**
 * Created by raehyeong.park on 2017. 3. 9..
 */

public class WriteDiaryFragment extends Fragment implements WriteDiaryContract.View {

    public static WriteDiaryFragment newInstance() {

        Bundle args = new Bundle();

        WriteDiaryFragment fragment = new WriteDiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public WriteDiaryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentWriteDiaryBinding viewBinding = FragmentWriteDiaryBinding.inflate(inflater, container, false);

        viewBinding.setViewModel(new WriteDiaryViewModel(getActivity(), this, new WriteDiaryRequest()));

        return viewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void successWriteDiary() {
        Toast.makeText(getContext(), "일기를 등록하였습니다.", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void failureWriteDiary(Exception exception) {
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
