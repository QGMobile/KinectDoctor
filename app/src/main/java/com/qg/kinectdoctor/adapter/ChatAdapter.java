package com.qg.kinectdoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.chat.EMVoiceMessageBody;
import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.emsdk.EMConstants;
import com.qg.kinectdoctor.model.VoiceBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ZH_L on 2016/10/25.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.VoiceHolder>{
    private static final String TAG = ChatAdapter.class.getSimpleName();

    private Context context;
    private List<VoiceBean> list;

    public ChatAdapter(Context context, List<VoiceBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ChatAdapter.VoiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        if(layoutId == 0) return null;
        final View v = LayoutInflater.from(context).inflate(layoutId, null);
        return new VoiceHolder(v);
    }

    private int getLayoutId(int viewType){
        int layoutId = 0;
        switch(viewType){
            case EMConstants.VIEWTYPE_TIME:
                layoutId = R.layout.item_chat_time;
                break;
            case EMConstants.VIEWTYPE_SOMEONE:
                layoutId = R.layout.item_chat_patient;
                break;
            case EMConstants.VIEWTYPE_ME:
                layoutId = R.layout.item_chat_doctor;
                break;
        }
        return layoutId;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.VoiceHolder holder, int position) {
        VoiceBean bean = list.get(position);
        holder.bindElement(bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }


    public class VoiceHolder extends ItemViewHolder<VoiceBean>{

        private TextView timeTv;    //type time

        //type patient
        private Button patientBtn;
        private TextView pLengthTv;

        //type doctor
        private Button doctorBtn;
        private TextView dLengthTv;

        public VoiceHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindElement(VoiceBean bean) {
            switch(bean.getType()){
                case EMConstants.VIEWTYPE_TIME:
                    Log.e(TAG, "timeTv==null->"+(timeTv==null));
                    timeTv.setText(bean.getTime());
                    break;
                case EMConstants.VIEWTYPE_SOMEONE:
                    EMVoiceMessageBody pBody = bean.getVoice();
                    pLengthTv.setText(pBody.getLength());
                    patientBtn.setTag(pBody);
                    patientBtn.setOnClickListener(this);
                    break;
                case EMConstants.VIEWTYPE_ME:
                    EMVoiceMessageBody dBody = bean.getVoice();
                    dLengthTv.setText(dBody.getLength());
                    doctorBtn.setTag(dBody);
                    doctorBtn.setOnClickListener(this);
                    break;
            }
        }


        @Override
        public void initChilds(View itemView) {
            switch(ChatAdapter.this.getItemViewType(getPosition())){
                case EMConstants.VIEWTYPE_TIME:
                    timeTv = (TextView)itemView.findViewById(R.id.time_tv);
                    break;
                case EMConstants.VIEWTYPE_SOMEONE:
                    patientBtn = (Button)itemView.findViewById(R.id.patient_voice_btn);
                    pLengthTv = (TextView)itemView.findViewById(R.id.time_length_tv);
                    break;
                case EMConstants.VIEWTYPE_ME:
                    doctorBtn = (Button)itemView.findViewById(R.id.doctor_voice_btn);
                    dLengthTv = (TextView)itemView.findViewById(R.id.time_length_tv);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            EMVoiceMessageBody body = null;
            switch(view.getId()){
                case R.id.patient_voice_btn:
                    patientBtn.setClickable(false);
                    patientBtn.setBackgroundResource(R.drawable.patient_voice_click);
                    body  = (EMVoiceMessageBody) patientBtn.getTag();
                    break;
                case R.id.doctor_voice_btn:
                    doctorBtn.setClickable(false);
                    doctorBtn.setBackgroundResource(R.drawable.doctor_voice_click);
                    body = (EMVoiceMessageBody) doctorBtn.getTag();
                    break;
            }
            if(body != null && mListener != null){
                mListener.onVoiceClick(body, getPosition());
            }
        }
    }

    private OnItemVoiceClickListener mListener;
    public void setOnItemVoiceClickListener(OnItemVoiceClickListener listener){
        mListener = listener;
    }

    public interface OnItemVoiceClickListener{
        void onVoiceClick(EMVoiceMessageBody body, int position);
    }
}
