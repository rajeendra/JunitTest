package com.rk.junittest;

/**
 * Created by rajeendra on 10/19/16.
 */
public class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public String toString() {
        return "Person of age: " + age;
    }
}
