package com.test.stoppishing;

public class PhoneNumberItem {
    private String nameStr ;
    private String phoneNumberStr ;

    public void setNameStr(String name) {
        nameStr = name;
    }

    public void setPhoneNumberStr(String phoneNumber) {
        phoneNumberStr = phoneNumber ;
    }

    public String getNameStr() {
        return this.nameStr ;
    }
    public String getPhoneNumberStr() {
        return this.phoneNumberStr ;
    }
}
