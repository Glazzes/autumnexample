package com.autumn.example.repository

import com.glaze.autumn.annotations.Repository

@Repository
class UserRepository {
    fun save(username: String){
        println("insert into users(name) values('$username')")
    }
}