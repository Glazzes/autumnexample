## Autumn Examples

This project contains four sub projects demonstrating the usage of [Autumn DI contaier project](https://github.com/Glazzes/autumn) in different situations, such as it's usage with Java, Kotlin, more complex scenarios such as mutli projects builds with Gradle as well as execeptional cases such as finding circular dependencies.

#### Obersevations
All projects make use of [gradle shadow plugin](https://imperceptiblethoughts.com/shadow/getting-started/) to be packaged into a uber jar file, still, there are some considerations to keep in mind:
- All members of `multi-project` build must apply the shadow plugin through a custom [precompiled plugin](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins)
- Kotlin projects are uncapable of loading classes through reflection as long as `Meta-INF/version/9/module-info.class` is present in your uber jar, therefore this file must be excluded from the resulting jar by configuring `ShadowJar task` under `build.gradle.kts` file as follows:
```Kotlin
tasks.withType<ShadowJar>().configureEach {
    exclude("Meta-INF/version/9/module-info.class")
}
```

#### How to build and run
You can run all projects as an application with `gradle run`command, but `multi-project`, this one expects to be ran by a sub project containing an entry point (psvm), in this case `app sub project` by using `gradle :app:run` command.

In order to convert each project into a uber file run `gradle shadowJar`, the resulting jar will be located at `current path/build/libs/projectname-version-all.jar`, as expected `multi-project` needs to packaged from `app sub project` with the following command `gradle :app:shadowJar`, the resulting jar will be found at `current path/app/build/libs/app-0.0.1-all.jar`