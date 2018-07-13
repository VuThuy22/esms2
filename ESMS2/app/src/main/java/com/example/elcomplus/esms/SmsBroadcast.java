package com.example.elcomplus.esms;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.elcomplus.esms.Utils.TimeUtils;
import com.example.elcomplus.esms.databases.BankDataHelper;
import com.example.elcomplus.esms.databases.SmsDataHelper;
import com.example.elcomplus.esms.models.Bank;
import com.example.elcomplus.esms.models.Sms;
import com.example.elcomplus.esms.mvp.BankPresenter;
import com.example.elcomplus.esms.mvp.BankView;

import java.util.ArrayList;
import java.util.List;

public class SmsBroadcast extends BroadcastReceiver implements BankView {
    String bankName, phone_accout, content,time;
    BankDataHelper bankDataHelper;
    SmsDataHelper smsDataHelper;
    List<String> list;
    Sms sms;
    Context context;
    private BankPresenter bankPresenter;
    String URL = "http://101.99.23.175:5566";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            Bundle bundle = intent.getExtras();
            bankDataHelper=new BankDataHelper(context);
            smsDataHelper=new SmsDataHelper(context);
            list=new ArrayList<>();
            bankPresenter=new BankPresenter(context,this);
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    phone_accout="";
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    content=smsMessage.getDisplayMessageBody();
                    bankName=smsMessage.getOriginatingAddress();
                    long Id_request=smsMessage.getTimestampMillis();
                    time=TimeUtils.convertLongToddMMyyyy(Id_request);
                    list.addAll(bankDataHelper.getBankPhone());
                    for(int j=0;j<list.size();j++){
                        if(bankName.equals(list.get(j))){
                            String url = URL + "/api/vietlott/admin/change_balance";
                            sms=new Sms(bankName,phone_accout,content,0,time);
                            smsDataHelper.addSms(sms);
                            bankPresenter.sendRequets((int)Id_request,url,sms);
                        }
                    }

                }
            }
        }
    }

    @Override
    public void display(List<Bank> list) {

    }

    @Override
    public void showDialog(String title, String message) {
        Intent intent1 = new Intent("receiverSms");
        context.sendBroadcast(intent1);
    }
}
