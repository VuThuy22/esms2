package com.example.elcomplus.esms.models;

public class Sms {
    private int smsId;
    private String BankName;
    private String Phone;
    private String content;
    private int status;
    private String time;

    public Sms(int smdId, String bankName, String phone, String content, int status, String time) {
        this.smsId = smdId;
        this.BankName = bankName;
        this.Phone = phone;
        this.content = content;
        this.status = status;
        this.time=time;
    }

    public Sms() {

    }

    public Sms(String bankName, String phone_accout, String content, int status, String time) {

        this.BankName = bankName;
        this.Phone = phone_accout;
        this.content = content;
        this.status = status;
        this.time=time;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "smsId=" + smsId +
                ", BankName='" + BankName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                '}';
    }
}
