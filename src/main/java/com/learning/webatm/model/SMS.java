package com.learning.webatm.model;

public class SMS {

    Long id;
    String phoneNumber;
    String info;


    public SMS() {
    }

    public SMS(Long id, String phoneNumber, String info) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
