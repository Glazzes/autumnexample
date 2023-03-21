## Circular dependencies example

This project is no different from its java counterpart, the main difference here is that this project will fail when executed because of the presence of the following circular dependency:

```Java
class One {
    @Autowired private Two two;
}

class Two {
    @Autowired private Three three;
}

class Three {
    @Autowired private One one;
}
```

This circular dependency can be expanded to even more classes if you want to experiment with it.