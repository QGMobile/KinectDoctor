package com.qg.kinectdoctor.logic;

import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;

/**
 * Created by ZH_L on 2016/10/21.
 */
public interface Logic {

    public void login(LoginParam param, LogicHandler<LoginResult> handler);
}
