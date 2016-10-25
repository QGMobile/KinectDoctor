package com.qg.kinectdoctor.emsdk;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class MediaRecordWorker extends BaseWorker<RecordTask> implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener, RecordControl{
    private static final String TAG  = MediaRecordWorker.class.getSimpleName();
//    private Handler handler;
//    private BlockingQueue<RecordTask> mrQueue;
    private MediaRecordListener mrListener;
    private MediaRecorder mediaRecorder;

    public MediaRecordWorker(){
        super(Looper.getMainLooper());
        mediaRecorder = new MediaRecorder();
    }


    private RecordTask curTask = null;
   private Object mLock = new Object();

    @Override
    public void run() {
        while(true){
            try {
                final RecordTask task = mQueue.take();
                synchronized (mLock) {
                    curTask = task;
                    int audioSource = task.getAudioSource();
                    String path = task.getOutputFile();
                    int outputFormat = task.getOutputFormat();
                    int outputEncode = task.getOutputEncode();

                    try {
                        initRecorder(audioSource, path, outputFormat, outputEncode);
                        startRecord();
                    } catch (IOException e) {
                        e.printStackTrace();
                        curTask = null;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRecorder(int audioSource, String path, int outputFormat, int outputEncode) throws IOException {
        //from Initial to Initialized
        mediaRecorder.setAudioSource(audioSource);

        //from Initialized to DataSourceConfigured
        mediaRecorder.setOutputFormat(outputFormat);

        //circle(DataSourceConfigured)
        mediaRecorder.setOutputFile(path);
        mediaRecorder.setAudioEncoder(outputEncode);

        //from DataSourceConfigured to Prepared
        mediaRecorder.prepare();
    }


    private void startRecord(){
        mediaRecorder.start();
        if(mrListener != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mrListener.onStartRecord(MediaRecordWorker.this);
                }
            });
        }
    }

    public void setMediaRecordListener(MediaRecordListener listener){
        mrListener = listener;
    }

    @Override
    public void onError(MediaRecorder mediaRecorder, int what, int extra) {
        if(mrListener != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mrListener.onErrorRecord();
                }
            });
        }
    }

    @Override
    public void onInfo(MediaRecorder mediaRecorder, int what, int extra) {
        if(what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED){
            mediaRecorder.stop();
            if(mrListener != null){

            }
            mediaRecorder.reset();
        }else if(what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED){

        }
    }

//    @Override
//    public void recordResume() {
//        mediaRecorder.resume();
//    }

//    @Override
//    public void recordPause() {
//        mediaRecorder.pause();
//    }

    @Override
    public void recordStop() {
        mediaRecorder.stop();
        synchronized (mLock) {
            if (mrListener != null && curTask != null) {
                String filePath = curTask.getOutputFile();
                File file = new File(filePath);
                mrListener.onEndRecord(file);
            }
            mediaRecorder.reset();
        }
    }


    public interface MediaRecordListener{
        void onStartRecord(RecordControl control);
        void onEndRecord(File outputFile);
        void onErrorRecord();
    }

    @Override
    public void onDestroy() {
        if(mediaRecorder != null){
            mediaRecorder.release();
            mediaRecorder = null;
        }
        mrListener = null;
    }
}
