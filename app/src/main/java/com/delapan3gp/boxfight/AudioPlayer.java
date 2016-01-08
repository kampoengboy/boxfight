package com.delapan3gp.boxfight;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by fausta on 08/01/16.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void stopSoundWhenFinished(){
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });
    }

    public void selectBgSound(Context c){
        stop();
        mPlayer = MediaPlayer.create(c, R.raw.bgsound);
    }

    public void selectStart(Context c){
        stop();
        mPlayer = MediaPlayer.create(c, R.raw.startbattle);
        stopSoundWhenFinished();
    }

    public void selectEnd(Context c){
        stop();
        mPlayer = MediaPlayer.create(c, R.raw.endbattle);
        stopSoundWhenFinished();
    }

    public void play() {
        mPlayer.start();
    }

}
