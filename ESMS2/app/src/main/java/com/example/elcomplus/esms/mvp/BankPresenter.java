package com.example.elcomplus.esms.mvp;

import android.content.Context;

import com.example.elcomplus.esms.MainActivity;
import com.example.elcomplus.esms.models.Bank;
import com.example.elcomplus.esms.models.Sms;

import java.util.List;

public class BankPresenter implements BankCallBack {
    private BankView bankView;
    private BankModel bankModel;


    public BankPresenter(Context context,BankView bankView) {
        this.bankView = bankView;
        this.bankModel=new BankModel(context, this);
    }



    public void sendRequets(int Id_requets,String url,Sms sms){
        bankModel.postRequest(Id_requets,url,sms);
    }

    public void delete(int id){
        bankModel.deleteBank(id);
    }
    public void insert(Bank bank){
        bankModel.insertBank(bank);
    }
    public void getList(){
        bankModel.getListBank();
    }
    @Override
    public void onAddSuccess(Bank bank) {
        getList();
    }

    @Override
    public void onAddFailure(String message) {
        bankView.showDialog("Error!",message);
    }

    @Override
    public void onDeleteSuccess(String title, String message) {
        getList();
    }

    @Override
    public void onDeleteFailure(String title, String message) {

    }

    @Override
    public void showAll(List<Bank> list) {
        bankView.display(list);
    }

    @Override
    public void onSuccessRequest(String message) {
        bankView.showDialog("Succes",message);
    }

    @Override
    public void onFaileRequest(String message) {
        bankView.showDialog("Faile",message);
    }
}
