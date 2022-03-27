package com.example.clients;

import com.glaze.autumn.annotations.Component;

@Component(id = "graphql")
public class GraphQLClient implements Client{

    @Override
    public void sendRequest() {
        System.out.println("Sending a a request over graphql");
    }
}
