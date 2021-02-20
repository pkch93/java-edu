package edu.pkch.extend;

public interface Greeting {
    default String hello(String name) {
        return "";
//        return defaultGreeting(name);
    }

//    private String defaultGreeting(String name) {
//        return String.format("hello %s", name);
//    }
}
