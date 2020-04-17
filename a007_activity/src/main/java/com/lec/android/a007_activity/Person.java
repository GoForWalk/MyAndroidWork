package com.lec.android.a007_activity;

// Intent에 담아 보내는 객체는 반. 드 .시 Serializable

import java.io.Serializable;

public class Person implements Serializable {

    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
