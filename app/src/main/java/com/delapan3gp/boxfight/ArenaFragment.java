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
import android.widget.Toast;


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
    public static int score_p1 = 0;
    public static int score_p2 = 0;
    public static ImageView imgview;
    public static ImageView imgview2;
    public static Button btn_attack_player1;
    public static Button btn_attack_player2;
    public static Button btn_player1;
    public static Button btn_player2;
    public static Button btn_left_player1;
    public static Button btn_left_player2;
    public static Button btn_right_player1;
    public static Button btn_right_player2;
    public static TextView text;
    public static ProgressBar pgBar;
    public static ProgressBar pgBar2;
    public static CountDownTimer timer;
    public static long millisinfuture = 20000;
    public static long countDownInterval = 1000;
    public static final String KEY = "reset";
    public static final String KEY2 = "left_p1";
    public static final String KEY3 = "right_p1";
    public static final String KEY4 = "left_p2";
    public static final String KEY5 = "right_p2";
    public AudioPlayer mPlayer = new AudioPlayer();

    public static final String SCORE1_KEY = "score1_key";
    public static final String SCORE2_KEY = "score2_key";
    private Integer currentPos;

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
    public void setTimer (){

    }
    public void again(){
        mPlayer.selectStart(getActivity());
        mPlayer.play();
        ArenaFragment.this.pgBar.setProgress(200);
        ArenaFragment.this.pgBar2.setProgress(200);
        ArenaFragment.this.left_p1 = 0;
        ArenaFragment.this.right_p1 = 0;
        ArenaFragment.this.left_p2 = 0;
        ArenaFragment.this.right_p2 = 0;
        ArenaFragment.this.click_left = 0;
        ArenaFragment.this.click_right = 0;
        ArenaFragment.this.hp_player1 = 200;
        ArenaFragment.this.hp_player2 = 200;
        timer.cancel();
        millisinfuture = 20000;
        countDownInterval = 1000;
        isCanceled = false;

        timer = new CountDownTimer(millisinfuture,countDownInterval) { // adjust the milli seconds here

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
                mPlayer.selectEnd(getActivity());
                mPlayer.play();

                text.setText("0");
                if(pgBar.getProgress() > pgBar2.getProgress()){
                    score_p1+=1;
                    ((TextView) getView().findViewById(R.id.txtScoreP1)).setText(String.valueOf(score_p1));
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 1 Win");
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                }
                else if(pgBar.getProgress() < pgBar2.getProgress()){
                    score_p2+=1;
                    ((TextView) getView().findViewById(R.id.txtScoreP2)).setText(String.valueOf(score_p2));
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 2 Win");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                } else if(pgBar.getProgress() == pgBar2.getProgress()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("DRAW");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                }
            }
        }.start();
        btn_left_player1.setEnabled(true);
        btn_left_player2.setEnabled(true);
        btn_right_player1.setEnabled(true);
        btn_right_player2.setEnabled(true);
        btn_attack_player1.setEnabled(true);
        btn_attack_player2.setEnabled(true);
        TranslateAnimation animation1 = new TranslateAnimation(left_p1, right_p1, 0, 0);
        animation1.setDuration(1000);
        animation1.setFillAfter(true);
        btn_player1.startAnimation(animation1);
        //imgview.startAnimation(animation1);
        TranslateAnimation animation2 = new TranslateAnimation(left_p2, right_p2, 0, 0);
        animation2.setDuration(1000);
        animation2.setFillAfter(true);
        btn_player2.startAnimation(animation2);
        //imgview2.startAnimation(animation2);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arena, container, false);


        if (ArenaSettingsLab.get(getActivity()).getCurrentArena() != null) {
            currentPos = ArenaSettingsLab.get(getActivity()).getCurrentArena();
            if (currentPos.equals(0)){
                view.setBackgroundResource(R.drawable.beach_arena);
            }
            else if (currentPos.equals(1)){
                view.setBackgroundResource(R.drawable.lampminion_arena);
            }
            else if (currentPos.equals(2)){
                view.setBackgroundResource(R.drawable.winter_arena);
            }
            else if (currentPos.equals(3)){
                view.setBackgroundResource(R.drawable.bathroom_arena);
            }
            else{
                view.setBackgroundResource(R.drawable.banana_arena);
            }
        }

        mPlayer.selectStart(getActivity());
        mPlayer.play();

        left_p1 = 0;
        right_p1 = 0;
        left_p2 = 0;
        right_p2 = 0;
        final RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_left_player1 = (Button)view.findViewById(R.id.btn_left_player1);
        btn_left_player2 = (Button)view.findViewById(R.id.btn_left_player2);
        btn_right_player1 = (Button)view.findViewById(R.id.btn_right_player1);
        btn_right_player2 = (Button)view.findViewById(R.id.btn_right_player2);
        text = (TextView)view.findViewById(R.id.timer);
        pgBar2 = (ProgressBar)view.findViewById(R.id.progressBar2);
        pgBar = (ProgressBar)view.findViewById(R.id.progressBar);
        btn_player1 = (Button)view.findViewById(R.id.btn_player1);
        btn_player2 = (Button)view.findViewById(R.id.btn_player2);
        hp_player1 = 200;
        hp_player2 = 200;
        click_left = 0;
        click_right = 0;
        score_p1 = 0;
        score_p2 = 0;
        pgBar.setMax(200);
        pgBar.setProgress(200);
        pgBar2.setRotation(180);
        pgBar2.setMax(200);
        pgBar2.setProgress(200);
        millisinfuture = 20000;
        countDownInterval = 1000;

        timer = new CountDownTimer(millisinfuture, countDownInterval) { // adjust the milli seconds here

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
                mPlayer.selectEnd(getActivity());
                mPlayer.play();

                text.setText("0");
                if(pgBar.getProgress() > pgBar2.getProgress()){
                    score_p1+=1;
                    ((TextView) getView().findViewById(R.id.txtScoreP1)).setText(String.valueOf(score_p1));
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 1 Win");
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                }
                else if(pgBar.getProgress() < pgBar2.getProgress()){
                    score_p2+=1;
                    ((TextView) getView().findViewById(R.id.txtScoreP2)).setText(String.valueOf(score_p2));
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Player 2 Win");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                } else if(pgBar.getProgress() == pgBar2.getProgress()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("DRAW");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "AGAIN",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    again();
                                    dialog.cancel();
                                }
                            }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
                }
            }
        }.start();
        btn_attack_player1 = (Button)view.findViewById(R.id.btn_attack_player1);
        btn_attack_player2 = (Button)view.findViewById(R.id.btn_attack_player2);
        btn_attack_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=9)
                {
                    hp_player2-=50;
                    mPlayer.selectPunch(getActivity());
                    mPlayer.play();

                    if(hp_player2<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 1 Win");
                        builder1.setCancelable(false);
                        //Increment score and set text
                        score_p1 += 1;
                        ((TextView) getView().findViewById(R.id.txtScoreP1)).setText(String.valueOf(score_p1));
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
                                "AGAIN",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        again();
                                        dialog.cancel();
                                        //Intent i = new Intent(getActivity(),HomeActivity.class);
                                        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        //startActivity(i);
                                    }
                                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
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
                if(click_left+click_right>=9)
                {
                    hp_player1-=50;
                    mPlayer.selectPunch(getActivity());
                    mPlayer.play();

                    if(hp_player1<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage("Player 2 Win");
                        builder1.setCancelable(false);
                        //Increment score and set text
                        score_p2 += 1;
                        ((TextView) getView().findViewById(R.id.txtScoreP2)).setText(String.valueOf(score_p2));
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
                                "AGAIN",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        again();
                                        dialog.cancel();
                                        //Intent i = new Intent(getActivity(),HomeActivity.class);
                                        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        //startActivity(i);
                                    }
                                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
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
                //imgview.startAnimation(animation);
            }
        });
        btn_right_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click_left+click_right>=9){
                } else {
                    right_p1 += 50;
                    left_p1 += 50;
                    click_left+=1;
                }
                TranslateAnimation animation = new TranslateAnimation(left_p1,right_p1,0,0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player1.startAnimation(animation);
                //imgview.startAnimation(animation);
            }
        });
        btn_left_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=9){
                } else {
                    right_p2 -= 50;
                    left_p2 -= 50;
                    click_right+=1;
                }
                TranslateAnimation animation = new TranslateAnimation(left_p2, right_p2,0,0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                btn_player2.startAnimation(animation);
                //imgview2.startAnimation(animation);

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
                //imgview2.startAnimation(animation);
            }
        });

        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        timer.cancel();
        mPlayer.stop();
    }

    @Override
    public void onPause(){
        super.onPause();
        timer.cancel();
        mPlayer.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
        timer.cancel();
        mPlayer.stop();
    }
}
