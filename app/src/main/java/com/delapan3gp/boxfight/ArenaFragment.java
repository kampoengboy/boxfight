package com.delapan3gp.boxfight;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ArenaFragment extends Fragment {
    private boolean isCanceled = false;
    public static float left_p1 = 0;
    public static float right_p1 = 0;
    public static float left_p2 = 0;
    public static float right_p2 = 0;
    public static int click_left = 0;
    public static int click_right = 0;
    public static int hp_player1 = 200;
    public static int hp_player2 = 200;
    public static ImageView imgview;
    public static ImageView imgview2;
    public static Button btn_attack_player1;
    public static Button btn_attack_player2;
    public static ProgressBar pgBar;
    public static ProgressBar pgBar2;
    public static final String KEY = "reset";
    public static final String KEY2 = "left_p1";
    public static final String KEY3 = "right_p1";
    public static final String KEY4 = "left_p2";
    public static final String KEY5 = "right_p2";
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(KEY, true);
        savedInstanceState.putFloat(KEY2, left_p1);
        savedInstanceState.putFloat(KEY3, right_p1);
        savedInstanceState.putFloat(KEY4,left_p2);
        savedInstanceState.putFloat(KEY5,right_p2);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        left_p1 = 0;
        right_p1 = 0;
        left_p2 = 0;
        right_p2 = 0;
        final RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final Button btn_left_player1 = (Button)view.findViewById(R.id.btn_left_player1);
        final Button btn_left_player2 = (Button)view.findViewById(R.id.btn_left_player2);
        final Button btn_right_player1 = (Button)view.findViewById(R.id.btn_right_player1);
        final Button btn_right_player2 = (Button)view.findViewById(R.id.btn_right_player2);
        final TextView text = (TextView)view.findViewById(R.id.timer);
        pgBar2 = (ProgressBar)view.findViewById(R.id.progressBar2);
        pgBar = (ProgressBar)view.findViewById(R.id.progressBar);
        final Button btn_player1 = (Button)view.findViewById(R.id.btn_player1);
        final Button btn_player2 = (Button)view.findViewById(R.id.btn_player2);
        imgview = (ImageView)view.findViewById(R.id.imageView);
        imgview.setImageResource(R.drawable.gloves_1);
        imgview2 = (ImageView)view.findViewById(R.id.imageView2);
        imgview2.setImageResource(R.drawable.gloves_2);
        hp_player1 = 200;
        hp_player2 = 200;
        click_left = 0;
        click_right = 0;
        pgBar.setMax(200);
        pgBar.setProgress(200);
        pgBar2.setRotation(180);
        pgBar2.setMax(200);
        pgBar2.setProgress(200);
        final long millisinfuture = 90000;
        final long countDownInterval = 1000;

        new CountDownTimer(millisinfuture, countDownInterval) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                if(isCanceled)
                {
                    //If user requested to pause or cancel the count down timer
                    cancel();
                }
                else {
                    text.setText("" + millisUntilFinished / 1000);
                    //Put count down timer remaining time in a variable
                }
            }

            public void onFinish() {
                text.setText("0");
                if(pgBar.getProgress() > pgBar2.getProgress()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 1 Win");
                    builder1.setPositiveButton(
                            "CLOSE",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent i = new Intent(getActivity(), HomeActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            });
                    builder1.setCancelable(false);
                    builder1.show();
                    btn_left_player1.setEnabled(false);
                    btn_left_player2.setEnabled(false);
                    btn_right_player1.setEnabled(false);
                    btn_right_player2.setEnabled(false);
                    btn_attack_player1.setEnabled(false);
                    btn_attack_player2.setEnabled(false);
                    pgBar2.setProgress(0);
                }
                else if(pgBar.getProgress() < pgBar2.getProgress()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 2 Win");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "CLOSE",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent i = new Intent(getActivity(), HomeActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            });
                    builder1.show();
                    btn_left_player1.setEnabled(false);
                    btn_left_player2.setEnabled(false);
                    btn_right_player1.setEnabled(false);
                    btn_right_player2.setEnabled(false);
                    btn_attack_player1.setEnabled(false);
                    btn_attack_player2.setEnabled(false);
                    pgBar.setProgress(0);
                }
            }
        }.start();
        btn_attack_player1 = (Button)view.findViewById(R.id.btn_attack_player1);
        btn_attack_player2 = (Button)view.findViewById(R.id.btn_attack_player2);
        btn_attack_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=15)
                {
                    hp_player2-=50;
                    if(hp_player2<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 1 Win");
                        builder1.setCancelable(false);
                        AlertDialog alert = builder1.create();
                        alert.setCanceledOnTouchOutside(false);
                        new CountDownTimer(500, countDownInterval){
                            @Override
                            public void onTick(long l) {

                            }

                            public void onFinish(){
                                text.setText("Win");
                                isCanceled = true;
                            }
                        }.start();
                        builder1.setPositiveButton(
                                "CLOSE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Intent i = new Intent(getActivity(),HomeActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                });
                        builder1.show();
                        btn_left_player1.setEnabled(false);
                        btn_left_player2.setEnabled(false);
                        btn_right_player1.setEnabled(false);
                        btn_right_player2.setEnabled(false);
                        btn_attack_player1.setEnabled(false);
                        btn_attack_player2.setEnabled(false);
                        pgBar2.setProgress(0);
                    } else {
                        pgBar2.setProgress(hp_player2);
                    }
                }
            }
        });
        btn_attack_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=15)
                {
                    hp_player1-=50;
                    if(hp_player1<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 2 Win");
                        builder1.setCancelable(false);
                        AlertDialog alert = builder1.create();
                        alert.setCanceledOnTouchOutside(false);
                        new CountDownTimer(500, countDownInterval){
                            @Override
                            public void onTick(long l) {

                            }

                            public void onFinish(){
                                text.setText("Win");
                                isCanceled = true;
                            }
                        }.start();
                        builder1.setPositiveButton(
                                "CLOSE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Intent i = new Intent(getActivity(),HomeActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                });
                        builder1.show();
                        btn_left_player1.setEnabled(false);
                        btn_left_player2.setEnabled(false);
                        btn_right_player1.setEnabled(false);
                        btn_right_player2.setEnabled(false);
                        btn_attack_player1.setEnabled(false);
                        btn_attack_player2.setEnabled(false);
                        pgBar.setProgress(0);
                    } else {
                        pgBar.setProgress(hp_player1);
                    }
                }
            }
        });
        //btn_player1.setLayoutParams(rel_btn);
        btn_left_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(right_p1<=-50){
                    right_p1=-50;
                    left_p1=-50;
                } else {
                    left_p1 -= 50;
                    right_p1 -= 50;
                }
                if (click_left <= -1)
                    click_left = -1;
                else
                    click_left -= 1;
                TranslateAnimation animation = new TranslateAnimation(left_p1, right_p1, 0, 0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player1.startAnimation(animation);
                imgview.startAnimation(animation);
            }
        });
        btn_right_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click_left+click_right>=16){


                } else {
                    right_p1 += 50;
                    left_p1 += 50;
                    click_left+=1;
                }
                TranslateAnimation animation = new TranslateAnimation(left_p1,right_p1,0,0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player1.startAnimation(animation);
                imgview.startAnimation(animation);
            }
        });
        btn_left_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=16){


                } else {
                    right_p2 -= 50;
                    left_p2 -= 50;
                    click_right+=1;
                }
                TranslateAnimation animation = new TranslateAnimation(left_p2, right_p2,0,0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player2.startAnimation(animation);
                imgview2.startAnimation(animation);

            }
        });
        btn_right_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(left_p2>=50){
                    right_p2=50;
                    left_p2=50;
                } else {
                    left_p2 += 50;
                    right_p2 += 50;
                }
                if(click_right<=-1)
                    click_right = -1;
                else
                    click_right-=1;
                TranslateAnimation animation = new TranslateAnimation(left_p2,right_p2,0,0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player2.startAnimation(animation);
                imgview2.startAnimation(animation);
            }
        });
        return view;
    }
}
