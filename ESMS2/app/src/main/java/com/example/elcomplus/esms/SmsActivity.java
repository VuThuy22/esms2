package com.example.elcomplus.esms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.elcomplus.esms.adapter.SmsAdapter;
import com.example.elcomplus.esms.databases.SmsDataHelper;
import com.example.elcomplus.esms.models.Sms;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmsActivity extends AppCompatActivity {

    LinearLayoutManager manager;
    SmsDataHelper smsDataHelper;
    SmsAdapter adapter;
    @BindView(R.id.rc_sms)
    RecyclerView rcSms;
    List<Sms> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
        smsDataHelper=new SmsDataHelper(this);
        smsList=new ArrayList<>();
        manager=new LinearLayoutManager(this);
        smsList.addAll(smsDataHelper.getAll());
        adapter=new SmsAdapter(smsList);
        rcSms.setLayoutManager(manager);
        rcSms.setAdapter(adapter);
    }
}
