package com.qg.kinectdoctor.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.listener.AppBarStateChangeListener;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.model.DUser;
import com.qg.kinectdoctor.model.MedicalRecord;
import com.qg.kinectdoctor.model.PUser;
import com.qg.kinectdoctor.param.GetMRParam;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.GetMRResult;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.view.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by 攀登者 on 2016/10/25.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ImageButton pacient, message, me;
    private TextView department;
    private ArrayList<MedicalRecord> medicalRecordList;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        //
        test();
//        initData();
    }

    DUser dUser ;
    private void test() {
        LoginParam param = new LoginParam("13549991585", "qgmobile");
        LogicImpl.getInstance().login(param, new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if(result.isOk() && onUIThread && result.status == 1) {
                    dUser = new DUser();
                    dUser = result.getdUser();
                    initData();
                    Log.e(TAG, dUser.toString());
                } else if(!result.isOk() && onUIThread) {
                    Toast.makeText(HomeActivity.this, "denglushibai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        medicalRecordList = new ArrayList<>();
                // 获取联系人列表
                GetMRParam param = new GetMRParam();
                param.dUserId = dUser.getId();
                LogicImpl.getInstance().GetMR(param, new LogicHandler< GetMRResult>() {

                    @Override
                    public void onResult(GetMRResult result, boolean onUIThread) {
                        if(result.isOk() && onUIThread) {
                            medicalRecordList.addAll(result.medicalRecords);
                            recyclerView.setAdapter(new HomeRecyclerAdapter());
                        } else if(onUIThread && !result.isOk()) {
                            Toast.makeText(HomeActivity.this, R.string.getMRfail, Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }

    private void initUI() {
        department = (TextView) findViewById(R.id.department);
        // 設置年齡科室
//        department.setText();
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    department.setVisibility(View.VISIBLE);
                    //展开状态
                } else if (state == State.COLLAPSED) {
                    department.setVisibility(View.INVISIBLE);
                    //折叠状态
                } else {
                    department.setVisibility(View.INVISIBLE);
                    //中间状态
                }
            }
        });

        // 医生名字
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("name");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        pacient = (ImageButton) findViewById(R.id.pacient);
        message = (ImageButton) findViewById(R.id.message);
        me = (ImageButton) findViewById(R.id.me);
        pacient.setImageResource(R.drawable.pacient_click);
        message.setImageResource(R.drawable.me_normal);
        me.setImageResource(R.drawable.me_normal);
        pacient.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                break;
            case R.id.pacient:
                pacient.setImageResource(R.drawable.pacient_click);
                message.setImageResource(R.drawable.me_normal);
                me.setImageResource(R.drawable.me_normal);
                break;
            case R.id.message:
                pacient.setImageResource(R.drawable.pacient_normal);
                message.setImageResource(R.drawable.me_click);
                me.setImageResource(R.drawable.me_normal);
                break;
            case R.id.me:
                pacient.setImageResource(R.drawable.pacient_normal);
                message.setImageResource(R.drawable.me_normal);
                me.setImageResource(R.drawable.me_click);
                break;
        }
    }

    class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(HomeActivity.this).inflate(R.layout.home_item, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.name.setText(medicalRecordList.get(position).getPname());
            holder.ageAndsex.setText(medicalRecordList.get(position).getAge() + "，" + (medicalRecordList.get(position).getSex() == 0 ? "男" : "女"));
        }

        @Override
        public int getItemCount() {
            return medicalRecordList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView name;
            private TextView ageAndsex;

            public MyViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
                ageAndsex = (TextView) itemView.findViewById(R.id.age_sex);
            }
        }
    }
}
