package com.qg.kinectdoctor.emsdk;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class MediaPlayWorker extends BaseWorker<PlayTask> implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener{
    private static final String TAG = MediaPlayWorker.class.getSimpleName();
//    private Handler handler;
//    private BlockingQueue<PlayTask> mpQueue;
    private MediaPlayListener mpListener;
    private MediaPlayer mediaPlayer;

    public MediaPlayWorker(){
        super(Looper.getMainLooper());
    }

    @Override
    public void run() {
        while(true) {
            try {
                PlayTask task = mQueue.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMediaPlayerListener(MediaPlayListener listener){
        mpListener = listener;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    public interface MediaPlayListener{
        void onStartPlay();
        void onEndPlay();
        void onErrorPlay();
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
        mpListener = null;
    }
}
