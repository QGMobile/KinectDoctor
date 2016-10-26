package com.qg.kinectdoctor.logic;

import android.os.AsyncTask;

import com.qg.kinectdoctor.http.HttpProcess;
import com.qg.kinectdoctor.param.GetDUserByPhoneParam;
import com.qg.kinectdoctor.param.GetPUserByPhoneParam;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.param.Param;
import com.qg.kinectdoctor.result.GetDUserByPhoneResult;
import com.qg.kinectdoctor.result.GetPUserByPhoneResult;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.result.Result;
import com.qg.kinectdoctor.util.CommandUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class LogicImpl implements Logic{

    private static final String TAG = LogicImpl.class.getSimpleName();
    private Executor exec = Executors.newFixedThreadPool(CommandUtil.getCoreNum()*2);

    private static LogicImpl instance;

    private LogicImpl(){};

    public static LogicImpl getInstance(){
        if(instance == null){
            synchronized (LogicImpl.class){
                if(instance == null){
                    instance = new LogicImpl();
                }
            }
        }
        return instance;
    }

    private <P extends Param, R extends Result>void getResult(final P param,final LogicHandler<R> handler,final Class<R> clazz){
        GetResultTask<R> task = new GetResultTask<R>() {
            @Override
            public R onBackground() {
                R result = HttpProcess.sendHttp(param, clazz);
                handler.onResult(result, false);
                return result;
            }

            @Override
            public void onUI(R result) {
                handler.onResult(result, true);
            }
        };
        task.executeOnExecutor(exec);
    }



    private abstract class GetResultTask<R extends Result> extends AsyncTask<Void,Void,R>{

        @Override
        protected R doInBackground(Void... ps) {
            return onBackground();
        }

        @Override
        protected void onPostExecute(R r) {
            onUI(r);
        }

        public abstract R onBackground();

        public abstract void onUI(R result);
    }

    @Override
    public void login(LoginParam param, LogicHandler<LoginResult> handler) {
        getResult(param, handler, LoginResult.class);
    }

    @Override
    public void getDUserByPhoneParam(GetDUserByPhoneParam param, LogicHandler<GetDUserByPhoneResult> handler) {
        getResult(param, handler, GetDUserByPhoneResult.class);
    }

    @Override
    public void getPUserByPhoneParam(GetPUserByPhoneParam param, LogicHandler<GetPUserByPhoneResult> handler) {
        getResult(param, handler, GetPUserByPhoneResult.class);
    }
}
