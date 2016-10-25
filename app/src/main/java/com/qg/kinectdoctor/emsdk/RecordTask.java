package com.qg.kinectdoctor.emsdk;

import android.media.MediaRecorder;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class RecordTask {
    private String outputFile;
    private int audioSource;    //MediaRecorder.AudioSource.MIC
    private int outputFormat;   //MediaRecorder.OutputFormat.AMR_NB
    private int outputEncode;   //MediaRecorder.AudioEncoder.AMR_NB

    public RecordTask(String outputFile, int audioSource, int outputFormat, int outputEncode){
        this.outputFile = outputFile;
        this.audioSource = audioSource;
        this.outputFormat = outputFormat;
        this.outputEncode = outputEncode;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public int getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(int audioSource) {
        this.audioSource = audioSource;
    }

    public int getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(int outputFormat) {
        this.outputFormat = outputFormat;
    }

    public int getOutputEncode() {
        return outputEncode;
    }

    public void setOutputEncode(int outputEncode) {
        this.outputEncode = outputEncode;
    }
}
