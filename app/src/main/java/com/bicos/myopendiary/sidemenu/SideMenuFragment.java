package com.bicos.myopendiary.sidemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicos.myopendiary.databinding.FragmentSidemenuBinding;
import com.bicos.myopendiary.splash.SplashActivity;

/**
 * Created by raehyeong.park on 2017. 3. 7..
 */

public class SideMenuFragment extends Fragment implements SideMenuContract.View {

    public static SideMenuFragment newInstance() {

        Bundle args = new Bundle();

        SideMenuFragment fragment = new SideMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SideMenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSidemenuBinding binding = FragmentSidemenuBinding.inflate(inflater, container, false);
        binding.setViewModel(new SideMenuViewModel(this));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void successLogout() {
        getContext().startActivity(new Intent(getContext(), SplashActivity.class));
        getActivity().finish();
    }
}