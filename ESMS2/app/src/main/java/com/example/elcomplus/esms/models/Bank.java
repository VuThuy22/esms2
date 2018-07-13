package com.example.elcomplus.esms.models;

public class Bank {
    private int id;
    private String BankName;
    private String BankPhone;

    public Bank(int id, String bankName, String bankPhone) {
        this.id = id;
        BankName = bankName;
        BankPhone = bankPhone;
    }

    public Bank(String bankName, String bankPhone) {
        BankName = bankName;
        BankPhone = bankPhone;
    }

    public Bank() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankPhone() {
        return BankPhone;
    }

    public void setBankPhone(String bankPhone) {
        BankPhone = bankPhone;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", BankName='" + BankName + '\'' +
                ", BankPhone='" + BankPhone + '\'' +
                '}';
    }
}
