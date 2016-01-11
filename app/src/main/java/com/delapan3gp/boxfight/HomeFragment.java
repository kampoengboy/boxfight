package com.delapan3gp.boxfight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private AudioPlayer mPlayer = new AudioPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPlayer.selectBgMusic(getActivity());
        mPlayer.play();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.AddListener();
    }

    public void AddListener(){
        TextView txtStart = (TextView)getView().findViewById(R.id.txtStart);
        TextView txtExit = (TextView)getView().findViewById(R.id.txtExit);
        TextView txtSettings = (TextView)getView().findViewById(R.id.txtSettings);

        if(!txtStart.hasOnClickListeners()) {
            txtStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ArenaActivity.class);
                    startActivity(i);
                }
            });
        }

        if(!txtExit.hasOnClickListeners()) {
            txtExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    DialogExitFragment dialog = new DialogExitFragment();
                    dialog.show(fm, null);
                }
            });
        }

        if (!txtSettings.hasOnClickListeners()){
            txtSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), GameSettingsActivity.class);
                    startActivity(i);
                }
            });
        }
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

    @Override
    public void onResume(){
        super.onResume();
        mPlayer.selectBgMusic(getActivity());
        mPlayer.play();
    }
}
