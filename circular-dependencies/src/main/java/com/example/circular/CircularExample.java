package com.example.circular;

import com.example.circular.dependencies.One;
import com.glaze.autumn.Autumn;
import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.AutumnApplication;

@AutumnApplication
public class CircularExample {

    @Autowired
    private One one;

    public static void main(String[] args) {
        Autumn.run(CircularExample.class);
        System.out.println("You will see this message if no circular are here!!!");
    }

}
