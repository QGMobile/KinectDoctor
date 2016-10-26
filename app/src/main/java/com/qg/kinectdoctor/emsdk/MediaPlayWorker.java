package com.qg.kinectdoctor.emsdk;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hyphenate.chat.EMVoiceMessageBody;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class MediaPlayWorker extends BaseWorker<PlayTask> implements SoundPool.OnLoadCompleteListener{
    private static final String TAG = MediaPlayWorker.class.getSimpleName();
//    private Handler handler;
//    private BlockingQueue<PlayTask> mpQueue;
//    private SoundListener soundListener;
//    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    public MediaPlayWorker(){
        super(Looper.getMainLooper());
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);
    }

    @Override
    public void run() {
        while(true) {
            try {
                PlayTask task = mQueue.take();
                EMVoiceMessageBody body = task.getEmVoiceMessageBody();
                String localUrl = body.getLocalUrl();
                String remoteUrl = body.getRemoteUrl();
                Log.e(TAG, "localUrl->"+localUrl+",remoteUrl->" + remoteUrl);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void setMediaPlayerListener(SoundListener listener){
//        soundListener = listener;
//    }
//
//    @Override
//    public void onPrepared(MediaPlayer mediaPlayer) {
//
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mediaPlayer) {
//
//    }
//
//    @Override
//    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
//        return false;
//    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        if(status == 0 && sampleId != 0){
            soundPool.play(sampleId, 0.5f, 0.5f ,1 ,0 , 1.0f);
        }
    }

//    public interface SoundListener{
//        void onStartPlay();
//        void onEndPlay();
//        void onErrorPlay();
//    }

    @Override
    public void onDestroy() {
        if(soundPool != null){
            soundPool.release();
            soundPool = null;
        }
//        soundListener = null;
    }
}
