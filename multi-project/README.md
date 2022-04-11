## MultiProject build

### @ComponentScan annotation
This annotation allows you to specify which packages will serve as a source of beans,
but this one is not limited to your own packages only, you can specify any
package that can be found in your classpath, yeah that includes dependencies!

### This Project structure (build tool agnostic)
```
multi-project
    |_ app (depends on clients and notification subprojects)
    |    |_ com.example.app
    |    
    |_ clients (sub project)
    |   |_ com.example.clients
    |    
    |_ notification (sub project)
       |_ com.example.notification
```

```
// app's gradle build.file
dependencies {
    implementation project(':clients')
    implementation project(':notification')
    implementation 'com.glaze.autumn:glaze-autumn:1.4'
}
```

### Requirements
- All of your projects must have `com.glaze.autumn:autumn:1.4` dependency
- Annotate your components in the respective projects
- Add your subprojects with annotated components as dependencies of the root
project
- In your root project use the `@ComponentScan` annotation as follows


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
`@ComponentScan` annotation, Keep in mind by using this annotation the package
where your source code classes live must be present too.

With this simple setup now you can scan classes from all the packages you need.