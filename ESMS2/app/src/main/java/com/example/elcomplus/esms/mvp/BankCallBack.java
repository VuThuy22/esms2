package com.example.elcomplus.esms.mvp;

import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public interface BankCallBack {
    void onAddSuccess(Bank bank);
    void onAddFailure(String message);
    void onDeleteSuccess(String title, String message);
    void onDeleteFailure(String title, String message);
    void showAll(List<Bank> list);
    void onSuccessRequest(String message);
    void onFaileRequest(String message);
}
