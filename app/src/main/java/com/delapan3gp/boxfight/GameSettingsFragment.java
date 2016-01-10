package com.delapan3gp.boxfight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GameSettingsFragment extends Fragment {


    private ViewPager arenaViewPager;
    private ArrayList<ArenaSettings> mArenas;

    public GameSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);

        arenaViewPager = (ViewPager)view.findViewById(R.id.arenaViewPager);
        mArenas = ArenaSettingsLab.get(getActivity()).getArenas();

        arenaViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ArenaSettings a = mArenas.get(position);
                return ArenaSettingsFragment.setNewInstanceId(a.getId());
            }

            @Override
            public int getCount() {
                return mArenas.size();
            }
        });

        return view;
    }

}
