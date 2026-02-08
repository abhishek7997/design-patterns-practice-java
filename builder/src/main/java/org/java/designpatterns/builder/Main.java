package org.java.designpatterns.builder;

public class Main {
    public static void main(String[] args) {
        SampleHttpRequest request = SampleHttpRequest.builder()
            .url("https://www.google.com")
            .contentType("application/json")
            .method("GET")
            .timeout(1000L)
            .build();
        System.out.println("REQUEST: " + request);
    }
}