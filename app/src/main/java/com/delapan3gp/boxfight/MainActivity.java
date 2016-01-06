package com.delapan3gp.boxfight;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final Button btn_left_player1 = (Button)findViewById(R.id.btn_left_player1);
        final Button btn_left_player2 = (Button)findViewById(R.id.btn_left_player2);
        final Button btn_right_player1 = (Button)findViewById(R.id.btn_right_player1);
        final Button btn_right_player2 = (Button)findViewById(R.id.btn_right_player2);
        imgview = (ImageView)findViewById(R.id.imageView);
        imgview.setImageResource(R.drawable.boxing);
        imgview2 = (ImageView)findViewById(R.id.imageView2);
        imgview2.setImageResource(R.drawable.boxing2);
        final Button btn_player1 = (Button)findViewById(R.id.btn_player1);
        final Button btn_player2 = (Button)findViewById(R.id.btn_player2);
        btn_attack_player1 = (Button)findViewById(R.id.btn_attack_player1);
        btn_attack_player2 = (Button)findViewById(R.id.btn_attack_player2);
        btn_attack_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=16)
                {
                    hp_player2-=50;
                    if(hp_player2<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setMessage("Player 1 Win");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "CLOSE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder1.show();
                        btn_left_player1.setEnabled(false);
                        btn_left_player2.setEnabled(false);
                        btn_right_player1.setEnabled(false);
                        btn_right_player2.setEnabled(false);
                        btn_attack_player1.setEnabled(false);
                        btn_attack_player2.setEnabled(false);
                        ProgressBar pgBar = (ProgressBar)findViewById(R.id.progressBar2);
                        pgBar.setProgress(0);
                    } else {
                        ProgressBar pgBar = (ProgressBar) findViewById(R.id.progressBar2);
                        pgBar.setProgress(hp_player2);
                    }
                }
            }
        });
        btn_attack_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_left+click_right>=16)
                {
                    hp_player1-=50;
                    if(hp_player1<=0){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setMessage("Player 2 Win");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "CLOSE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder1.show();
                        btn_left_player1.setEnabled(false);
                        btn_left_player2.setEnabled(false);
                        btn_right_player1.setEnabled(false);
                        btn_right_player2.setEnabled(false);
                        btn_attack_player1.setEnabled(false);
                        btn_attack_player2.setEnabled(false);
                        ProgressBar pgBar = (ProgressBar)findViewById(R.id.progressBar);
                        pgBar.setProgress(0);
                    } else {
                        ProgressBar pgBar = (ProgressBar) findViewById(R.id.progressBar);
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
                if(click_left<=-1)
                    click_left = -1;
                else
                    click_left -= 1;
                TranslateAnimation animation = new TranslateAnimation(left_p1, right_p1,0,0);
                animation.setDuration(1000);
                //animation.setRepeatCount(5);
                //animation.setRepeatMode(2);

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
                //animation.setRepeatCount(5);
                //animation.setRepeatMode(2);
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
                //animation.setRepeatCount(5);
                //animation.setRepeatMode(2);
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
                //animation.setRepeatCount(5);
                //animation.setRepeatMode(2);
                animation.setFillAfter(true);
                btn_player2.startAnimation(animation);
                imgview2.startAnimation(animation);
            }
        });
    }
}
