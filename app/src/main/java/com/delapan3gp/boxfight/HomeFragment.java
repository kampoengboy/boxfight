package com.delapan3gp.boxfight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.AddListener();
    }

    public void AddListener(){
        ImageButton imgbtnSound = (ImageButton)getView().findViewById(R.id.imgbtnSound);
        TextView txtStart = (TextView)getView().findViewById(R.id.txtStart);

        if(!imgbtnSound.hasOnClickListeners()) {
            imgbtnSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        if(!txtStart.hasOnClickListeners()) {
            txtStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ArenaActivity.class);
                    startActivity(i);
                }
            });
        }


    }
}
