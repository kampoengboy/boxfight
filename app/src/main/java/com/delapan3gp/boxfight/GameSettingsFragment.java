package com.delapan3gp.boxfight;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class GameSettingsFragment extends Fragment {


    private ViewPager arenaViewPager;
    private ArrayList<ArenaSettings> mArenas;
    private Button OKBtn;
    private Integer currentPos;
    private AudioPlayer mPlayer = new AudioPlayer();

    public GameSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);

        mPlayer.selectBgMusic(getActivity());
        mPlayer.play();

        arenaViewPager = (ViewPager)view.findViewById(R.id.arenaViewPager);
        mArenas = ArenaSettingsLab.get(getActivity()).getArenas();
        OKBtn = (Button)view.findViewById(R.id.OKBtn);

        arenaViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ArenaSettings a = mArenas.get(position);
                a.setPositionActive(position);
                return ArenaSettingsFragment.setNewInstanceId(a.getId());
            }

            @Override
            public int getCount() {
                return mArenas.size();
            }
        });

        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSettings = new AlertDialog.Builder(getActivity());

                builderSettings.setMessage("Arena Changed!");
                builderSettings.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentPos = arenaViewPager.getCurrentItem();
                        ArenaSettingsLab.get(getActivity()).setCurrentArena(currentPos);
                    }
                });
                builderSettings.setCancelable(false);
                builderSettings.show();
            }
        });

        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }

    @Override
    public void onPause(){
        super.onPause();
        mPlayer.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
        mPlayer.stop();
    }

}
