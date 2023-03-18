## MultiProject builds
Multi project builds are a bit more complicated than a regular build
as this projects rely on other projects of your own, in other words
source code out of your main project, because of this issue it's necessary
to scan all sub projects, @ComponentScan annotation solves this issue as follows;

### @ComponentScan annotation
This annotation allows you to specify which packages will serve as a source of beans,
but this one is not limited to your own packages only, you can specify any
package that can be found in your classpath, yeah that includes dependencies!

### This Project structure (build tool agnostic)
App is the main project and depends on the other two
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
    implementation 'com.glaze:autumn:0.1'
}
```

### Requirements
- All of your projects must have `com.glaze:autumn:0.1` dependency
- Annotate your components in the respective projects with `@Component` annotation
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
    
    @MainMethod
    public static void run() {
        // TODO
    }
}
```

In order to scan classes from another packages you must specify those via
`@ComponentScan` annotation, Keep in mind by using this annotation your main
project package must be included too.

With this simple setup now you can scan classes from all the packages you need.
