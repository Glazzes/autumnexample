package com.example.circular.dependencies;

import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.Component;

@Component
public class Three {

    @Autowired
    private One one;
}
