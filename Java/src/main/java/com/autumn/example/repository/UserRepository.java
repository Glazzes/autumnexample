package com.autumn.example.repository;

import com.glaze.autumn.annotations.Repository;

@Repository
public class UserRepository {

    public void save(String username){
        System.out.println("insert into users(name) values('" + username + "');");
    }

}
