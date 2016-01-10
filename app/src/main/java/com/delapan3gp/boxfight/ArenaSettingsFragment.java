package com.delapan3gp.boxfight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArenaSettingsFragment extends Fragment {


    public static final String EXTRA_ARENA_ID = "extra_arena_id";
    private ImageButton arenaSettingsImageBtn;
    private RadioButton firstRadioBtn, secondRadioBtn, thirdRadioBtn, fourthtRadioBtn, fifthRadioBtn;

    public ArenaSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_arena_settings, container, false);

        UUID idArena = (UUID)getArguments().getSerializable(EXTRA_ARENA_ID);
        ArenaSettings arenaSettings = ArenaSettingsLab.get(getActivity()).getArena(idArena);

        arenaSettingsImageBtn = (ImageButton)v.findViewById(R.id.arenaSettingsImageBtn);
        firstRadioBtn = (RadioButton)v.findViewById(R.id.firstRadioBtn);
        secondRadioBtn = (RadioButton)v.findViewById(R.id.secondRadioBtn);
        thirdRadioBtn = (RadioButton)v.findViewById(R.id.thirdRadioBtn);
        fourthtRadioBtn = (RadioButton)v.findViewById(R.id.fourthRadioBtn);
        fifthRadioBtn = (RadioButton)v.findViewById(R.id.fifthRadioBtn);

        firstRadioBtn.setEnabled(false);
        secondRadioBtn.setEnabled(false);
        thirdRadioBtn.setEnabled(false);
        fourthtRadioBtn.setEnabled(false);
        fifthRadioBtn.setEnabled(false);

        if (arenaSettings.getPositonActive().equals(0)){
            firstRadioBtn.setChecked(true);
        }
        else if(arenaSettings.getPositonActive().equals(1)){
            secondRadioBtn.setChecked(true);
        }
        else if (arenaSettings.getPositonActive().equals(2)){
            thirdRadioBtn.setChecked(true);
        }
        else if(arenaSettings.getPositonActive().equals(3)){
            fourthtRadioBtn.setChecked(true);
        }
        else{
            fifthRadioBtn.setChecked(true);
        }

        arenaSettingsImageBtn.setBackgroundResource(arenaSettings.getImagePath());

        return v;
    }

    public static ArenaSettingsFragment setNewInstanceId(UUID idArena){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ARENA_ID, idArena);

        ArenaSettingsFragment fragment = new ArenaSettingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
