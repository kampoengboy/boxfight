package com.delapan3gp.boxfight;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ArenaFragment extends Fragment {

    public static float left_p1 = 0;
    public static float right_p1 = 0;
    public static float left_p2 = 0;
    public static float right_p2 = 0;
    public static int click_left = 0;
    public static int click_right = 0;
    public static int hp_player1 = 200;
    public static int hp_player2 = 200;
    public static int score_p1 = 0;
    public static int score_p2 = 0;
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
    public static final String SCORE1_KEY = "";
    public static final String SCORE2_KEY = "";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(SCORE1_KEY, score_p1);
        savedInstanceState.putInt(SCORE2_KEY, score_p2);
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
        pgBar2.setMax(200);
        pgBar2.setProgress(200);
        btn_attack_player1 = (Button)view.findViewById(R.id.btn_attack_player1);
        btn_attack_player2 = (Button)view.findViewById(R.id.btn_attack_player2);
        btn_attack_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click_left + click_right >= 16) {
                    hp_player2 -= 50;
                    if (hp_player2 <= 0) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 1 Win");
                        builder1.setCancelable(true);

                        //Increment score and set text
                        score_p1 += 1;
                        ((TextView) getView().findViewById(R.id.txtScoreP1)).setText(String.valueOf(score_p1));

                        builder1.setPositiveButton(
                                "Again",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ArenaFragment.this.pgBar.setProgress(200);
                                        ArenaFragment.this.pgBar2.setProgress(200);
                                        ArenaFragment.this.left_p1 = 0;
                                        ArenaFragment.this.right_p1 = 0;
                                        ArenaFragment.this.left_p2 = 0;
                                        ArenaFragment.this.right_p2 = 0;

                                        TranslateAnimation animation1 = new TranslateAnimation(left_p1, right_p1, 0, 0);
                                        animation1.setDuration(1000);
                                        animation1.setFillAfter(true);
                                        btn_player1.startAnimation(animation1);
                                        imgview.startAnimation(animation1);

                                        TranslateAnimation animation2 = new TranslateAnimation(left_p2, right_p2, 0, 0);
                                        animation2.setDuration(1000);
                                        animation2.setFillAfter(true);
                                        btn_player2.startAnimation(animation2);
                                        imgview2.startAnimation(animation2);

                                    }
                                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
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
                if (click_left + click_right >= 16) {
                    hp_player1 -= 50;
                    if (hp_player1 <= 0) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 2 Win");
                        builder1.setCancelable(true);

                        //Increment score and set text
                        score_p2 += 1;
                        ((TextView) getView().findViewById(R.id.txtScoreP1)).setText(String.valueOf(score_p2));

                        builder1.setPositiveButton(
                                "Again",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
//                                        getActivity().recreate();
                                        ArenaFragment.this.pgBar.setProgress(200);
                                        ArenaFragment.this.pgBar2.setProgress(200);
                                        ArenaFragment.this.left_p1 = 0;
                                        ArenaFragment.this.right_p1 = 0;
                                        ArenaFragment.this.left_p2 = 0;
                                        ArenaFragment.this.right_p2 = 0;

                                        TranslateAnimation animation1 = new TranslateAnimation(left_p1, right_p1,0,0);
                                        animation1.setDuration(1000);
                                        animation1.setFillAfter(true);
                                        btn_player1.startAnimation(animation1);
                                        imgview.startAnimation(animation1);

                                        TranslateAnimation animation2 = new TranslateAnimation(left_p2,right_p2,0,0);
                                        animation2.setDuration(1000);
                                        animation2.setFillAfter(true);
                                        btn_player2.startAnimation(animation2);
                                        imgview2.startAnimation(animation2);
                                    }
                                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
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
                if (right_p1 <= -50) {
                    right_p1 = -50;
                    left_p1 = -50;
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
                if(click_left+click_right>=16){


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
