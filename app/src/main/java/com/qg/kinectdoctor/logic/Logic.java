package com.qg.kinectdoctor.logic;

import com.qg.kinectdoctor.param.DelMRParam;
import com.qg.kinectdoctor.param.DelRcStageParam;
import com.qg.kinectdoctor.param.GetDUserByPhoneParam;
import com.qg.kinectdoctor.param.GetMRParam;
import com.qg.kinectdoctor.param.GetPUserByPhoneParam;
import com.qg.kinectdoctor.param.GetRcStageParam;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.param.SetMRParam;
import com.qg.kinectdoctor.param.SetRcStageParam;
import com.qg.kinectdoctor.result.DelMRResult;
import com.qg.kinectdoctor.result.DelRcStageResult;
import com.qg.kinectdoctor.result.GetDUserByPhoneResult;
import com.qg.kinectdoctor.result.GetMRResult;
import com.qg.kinectdoctor.result.GetPUserByPhoneResult;
import com.qg.kinectdoctor.result.GetRcStageResult;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.result.SetMRResult;
import com.qg.kinectdoctor.result.SetRcStageResult;

/**
 * Created by ZH_L on 2016/10/21.
 */
public interface Logic {

    public void login(LoginParam param, LogicHandler<LoginResult> handler);

    public void getDUserByPhoneParam(GetDUserByPhoneParam param, LogicHandler<GetDUserByPhoneResult> handler);

    public void getPUserByPhoneParam(GetPUserByPhoneParam param, LogicHandler<GetPUserByPhoneResult> handler);

    public void GetMR(GetMRParam param, LogicHandler<GetMRResult> handler) ;

    public void SetMR(SetMRParam param, LogicHandler<SetMRResult> handler);

    public void DelMR(DelMRParam param, LogicHandler<DelMRResult> handler);

    public void SetRcStage(SetRcStageParam param, LogicHandler<SetRcStageResult> handler);

    public void DelRcStage(DelRcStageParam param, LogicHandler<DelRcStageResult> handler);

    public void getRcStage(GetRcStageParam param, LogicHandler<GetRcStageResult> handler);
}
