package com.qg.kinectdoctor.logic;

import com.qg.kinectdoctor.param.DelMRParam;
import com.qg.kinectdoctor.param.GetDUserByPhoneParam;
import com.qg.kinectdoctor.param.GetPUserByPhoneParam;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.DelMRResult;
import com.qg.kinectdoctor.result.GetDUserByPhoneResult;
import com.qg.kinectdoctor.result.GetPUserByPhoneResult;
import com.qg.kinectdoctor.result.LoginResult;

/**
 * Created by ZH_L on 2016/10/21.
 */
public interface Logic {

    public void login(LoginParam param, LogicHandler<LoginResult> handler);

    public void getDUserByPhoneParam(GetDUserByPhoneParam param, LogicHandler<GetDUserByPhoneResult> handler);

    public void getPUserByPhoneParam(GetPUserByPhoneParam param, LogicHandler<GetPUserByPhoneResult> handler);
}
