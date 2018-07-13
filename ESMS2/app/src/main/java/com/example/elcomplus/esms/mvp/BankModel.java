package com.example.elcomplus.esms.mvp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elcomplus.esms.databases.BankDataHelper;
import com.example.elcomplus.esms.databases.SmsDataHelper;
import com.example.elcomplus.esms.models.Bank;
import com.example.elcomplus.esms.models.Sms;

import org.json.JSONException;
import org.json.JSONObject;

public class BankModel {
    private BankCallBack bankCallBack;
    private BankPresenter bankPresenter;
    BankDataHelper bankDataHelper;
    SmsDataHelper smsDataHelper;
    Context context;
    Bank bank;
    public BankModel(Context context, BankCallBack bankCallBack){
        this.bankCallBack=bankCallBack;
        this.context=context;
        bankDataHelper=new BankDataHelper(context);
        smsDataHelper=new SmsDataHelper(context);

    }
    public void insertBank(Bank bank){
        long id = bankDataHelper.addBank(bank);
        if(id<0){
            bankCallBack.onAddFailure("Add Failure");
        }
        else {
            bank.setId(((int) id));
            bankCallBack.onAddSuccess(bank);
        }
    }
    public void deleteBank(int id){
        bankDataHelper.deletebank(id);
        bankDataHelper.getAll();
        bankCallBack.onDeleteSuccess("Notify","Success delete");
    }

    public void getListBank(){
        bankCallBack.showAll(bankDataHelper.getAll());
    }
    public void editSms(Sms sms){
       smsDataHelper.editBank(sms);
    }

    public void postRequest(int id_request, String url, final Sms sms){
        JSONObject request=new JSONObject();
        try{
            request.put("requestId",id_request);
            request.put("bankId",-2);
            request.put("bankName",sms.getBankName());
            request.put("requestType",-1);
            request.put("money",0);
            request.put("content","");
            request.put("account","");
            request.put("fullContent",sms.getContent());
            request.put("action",0);

        }catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest postJson=new JsonObjectRequest(Request.Method.POST, url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int result = response.getInt("result");
                    String resultDesc = response.getString("resultDesc");
                    String requestId = response.getString("requestId");
                    bankCallBack.onSuccessRequest(requestId+resultDesc);
                    sms.setStatus(1);
                    editSms(sms);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ;
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                bankCallBack.onFaileRequest("Error!");
            }
        });
        Volley.newRequestQueue(context.getApplicationContext()).add(postJson);
    }


}
