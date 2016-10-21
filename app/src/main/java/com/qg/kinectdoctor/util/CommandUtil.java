package com.qg.kinectdoctor.util;

import android.os.CpuUsageInfo;
import android.os.Environment;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class CommandUtil {

    public static int getCoreNum(){
        return Runtime.getRuntime().availableProcessors();
//        FileFilter filter = new FileFilter(){
//
//            @Override
//            public boolean accept(File file) {
//                String fileName = file.getName();
//                return fileName.matches("cpu[0-9]");
//            }
//        };
//
//        File dir = new File("/sys/devices/system");
//        File[] files = dir.listFiles(filter);
//        if(files == null) return 0;
//        return files.length;
    }
}
