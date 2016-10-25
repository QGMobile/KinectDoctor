package com.qg.kinectdoctor.emsdk;

import android.content.Context;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;
import java.util.Map;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class IMManager {
    private static final String TAG = IMManager.class.getSimpleName();

    private IMManager(Context appContext){
        EMOptions options = new EMOptions();
        //默认添加好友时是不需要验证的，这里也不需要验证
        options.setAcceptInvitationAlways(true);

        EMClient.getInstance().init(appContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    };

    private static IMManager instance = null;

    public static IMManager getInstance(Context appContext){
        if(instance == null){
            synchronized (IMManager.class){
                if(instance == null){
                    instance = new IMManager(appContext);
                }
            }
        }
        return instance;
    }

    /**
     * 环信登录
     * @param phone
     * @param callback
     */
    public void login(final String phone, final LoginCallback callback){
        EMClient.getInstance().login(EMConstants.DOCTOR_USERNAME_PREFIX + phone, EMConstants.LOGIN_PASSWORD, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d(TAG,"emsdk login success-phone:" + phone);
                callback.onSuccess();
            }

            @Override
            public void onError(int code, String message) {
                Log.e(TAG,"emsdk login error-message:" + message);
                callback.onError(message);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    /**
     * 环信登录退出(异步)
     */
    public void logoutAsy(final LoginCallback callback){
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"emsdk logout success");
                callback.onSuccess();
            }

            @Override
            public void onError(int i, String message) {
                Log.e(TAG,"emsdk logout error-message:" + message);
                callback.onError(message);
            }

            @Override
            public void onProgress(int code, String status) {

            }
        });

    }

    /**
     * 环信登录退出
     */
    public void logout(){
        EMClient.getInstance().logout(false);
    }

    public void sendVoiceMessage(String filePath, int length, String toChatUsername, EMCallBack callBack){
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
        message.setChatType(EMMessage.ChatType.Chat);
        message.setMessageStatusCallback(callBack);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    public void addMessageListener(EMMessageListener listener){
        EMClient.getInstance().chatManager().addMessageListener(listener);
    }

    public void removeMessageListener(EMMessageListener listener){
        EMClient.getInstance().chatManager().removeMessageListener(listener);
    }

    /**
     * 获取未读消息数量
     * @param username
     * @return
     */
    public int getUnreadMsgCount(String username){
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        if(conversation == null)return 0;
        return conversation.getUnreadMsgCount();
    }

    /**
     * 未读消息清零
     * @param username
     */
    public void clearUnReadMsg(String username){
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        if(conversation == null){
            return;
        }
        conversation.markAllMessagesAsRead();
    }

    /**
     * 获取所有会话
     * @return
     */
    public Map<String, EMConversation> getAllConversations(){
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        return conversations;
    }

    public List<String> getFriendsList() throws HyphenateException {
        List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
        return usernames;
    }

    public void setContactListener(EMContactListener listener){
        EMClient.getInstance().contactManager().setContactListener(listener);
    }
}
