package com.example.clients;

import com.glaze.autumn.annotations.Component;

@Component(id = "http")
public class HttpClient implements Client {

    @Override
    public void sendRequest() {
        System.out.println("Calling an external resource over http");
    }
}
