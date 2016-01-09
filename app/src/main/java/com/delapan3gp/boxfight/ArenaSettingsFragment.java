package com.delapan3gp.boxfight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArenaSettingsFragment extends Fragment {


    public static final String EXTRA_ARENA_ID = "extra_arena_id";
    private ImageButton arenaSettingsImageBtn;

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
        arenaSettingsImageBtn.setBackgroundResource(arenaSettings.getImagePath());

        return v;
    }

    public static ArenaSettingsFragment setNewInstanceId(UUID idPecahan){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ARENA_ID, idPecahan);

        ArenaSettingsFragment fragment = new ArenaSettingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
