package com.qg.kinectdoctor.emsdk;

import com.hyphenate.chat.EMVoiceMessageBody;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class PlayTask {

    private EMVoiceMessageBody emVoiceMessageBody;

    public PlayTask(EMVoiceMessageBody emVoiceMessageBody){
        this.emVoiceMessageBody = emVoiceMessageBody;
    }

    public EMVoiceMessageBody getEmVoiceMessageBody() {
        return emVoiceMessageBody;
    }

    public void setEmVoiceMessageBody(EMVoiceMessageBody emVoiceMessageBody) {
        this.emVoiceMessageBody = emVoiceMessageBody;
    }
}
