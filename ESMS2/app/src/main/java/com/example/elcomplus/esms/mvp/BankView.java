package com.example.elcomplus.esms.mvp;

import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public interface BankView {
    void display(List<Bank> list);
    void showDialog(String title, String message);

}
