package com.lec.android.a008_practice;

import java.io.Serializable;
import java.util.Date;

public class Model implements Serializable {

    String name;
    String age;
    String address;
    Date birthday;
    String PhoneNum;

    public Model() {
    }

    public Model(String name, String age, String address, Date birthday, String phoneNum) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
        this.PhoneNum = phoneNum;
    }

    public Model(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
