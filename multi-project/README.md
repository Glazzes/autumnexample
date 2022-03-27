## MultiProject build

### @ComponentScan annotation
This annotation allows you to specify which packages will serve as source of beans,
but this one is not limited to your own packages, you can specify any package of
contained inside a jar or your project classpath.

### This Project structure
```
multi-project
    |_ app (dependes on clients and notification)
    |    |_ com.example.app
    |    
    |_ clients (sub project)
    |   |_ com.example.clients
    |    
    |_ notification (sub project)
       |_ com.example.notification
```

```
// gradle build.file
dependencies {
    implementation project(':clients')
    implementation project(':notification')
    implementation 'com.glaze.autumn:glaze-autumn:1.3'
}
```

### Requirements
Project as a dependency on clients and notification projects, in order to
get beans from those packages you will need the autumn dependency in their
classpath.

### Set up
```java
// App main class
@AutumnApplication
@ComponentScan(basePackages = {
        "com.example.app", 
        "com.example.notification", 
        "com.example.clients", 
})
public class App {
    
}
```

In order to scan classes from another packages you must specify those via
`@AutumnApplication` annotation, Keep in mind by using this annotation the package
where your source code classes live must be present too.

With this simple setup now you can scan classes from all the packages you need.