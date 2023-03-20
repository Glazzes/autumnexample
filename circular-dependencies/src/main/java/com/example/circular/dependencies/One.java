package com.example.circular.dependencies;

import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.Component;

@Component
public class One {

    @Autowired
    private Two two;
}
