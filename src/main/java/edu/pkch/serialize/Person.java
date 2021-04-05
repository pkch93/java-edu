package edu.pkch.serialize;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private long age;

    public Person() {
    }

    public Person(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    private void readObjectNoData() {
        this.name = "pkch";
        this.age = 28;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
