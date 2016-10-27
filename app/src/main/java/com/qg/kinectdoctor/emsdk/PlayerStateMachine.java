package com.qg.kinectdoctor.emsdk;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.util.Log;

import com.qg.kinectdoctor.activity.App;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZH_L on 2016/10/27.
 */
public class PlayerStateMachine implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener{
    private static final String TAG = PlayerStateMachine.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    private boolean isPrepared;

    public PlayerStateMachine(){}

    private void initStatus(){
        isPrepared = false;
    }

    private void init(File file){
        initStatus();
        if(mediaPlayer == null){
            //prepare auto called
            Uri uri = Uri.fromFile(file);
            mediaPlayer = MediaPlayer.create(App.getInstance(), uri);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
        }else{
            try {
                //your should call prepare by hand
                mediaPlayer.setDataSource(file.getAbsolutePath());

                //prepare block until mediaplayer can play
                //mediaPlayer.prepare();

                //prepare asynchronizly
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void playMedia(File dataSource){
        if(isMediaPlayerPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        init(dataSource);

    }

    private boolean isMediaPlayerPlaying(){
        if(mediaPlayer == null)return false;
        return mediaPlayer.isPlaying();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.e(TAG, "onCompletion");
        if(mediaPlayer != null){
            mediaPlayer.reset();
        }
        if(mListener != null){
            mListener.onPlayComplete();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e(TAG, "onPrepared");
        isPrepared = true;
        mediaPlayer.start();
    }

    public boolean isPrepared(){
        return isPrepared;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        Log.e(TAG, "onError");
        if(mediaPlayer != null){
            mediaPlayer.reset();
        }
        if(mListener != null){
            mListener.onPlayError();
            return true;
        }
        return false;
    }

    private PlayerStateMachineListener mListener;
    public void setPlayerStateMachineListener(PlayerStateMachineListener listener){
        mListener = listener;
    }

    public interface PlayerStateMachineListener{
        void onPlayComplete();
        void onPlayError();
    }

    public void releaseRes(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
