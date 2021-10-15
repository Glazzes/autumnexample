package com.autumn.example;

import com.autumn.example.service.PrintService;
import com.autumn.example.service.UserService;
import com.glaze.autumn.Autumn;
import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.AutumnApplication;
import com.glaze.autumn.application.CommandLineRunner;

@AutumnApplication
public class MainExample implements CommandLineRunner {
    @Autowired
    private PrintService printService;

    private final UserService userService;
    public MainExample(UserService userService){
        this.userService = userService;
    }

    public static void main(String[] args) {
        Autumn.run(MainExample.class);
    }

    @Override
    public void run() {
        userService.save("Glaze", "GlazePassword");
        printService.print();
    }
}
