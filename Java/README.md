## Java Autumn Example

### Setup
Your main class must be annotated with `@AutumnApplication` and implement `CommandLineRunner`.
```java
import com.glaze.autumn.annotations.AutumnApplication;
import com.glaze.autumn.application.CommandLineRunner;
import com.autumn.example.service.UserService;

@AutumnApplication
class Application implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(){
        // your application code must go here
    }

    public static void main(String[] args){
        // your code logic goes on run method
        Autumn.run(Application.class)
    }

}
```

What did i just see?
- Autumn expects the main class to implement `CommandLineRunner` so it can execute it's `run method` once all checks and instantiation processes are done
- Your main class is also eligible for dependency injection
- Autumn.run takes your main class as parameter aswell as being the entry point of your application

## Components
Annotating any class with one of the following annotations turns it into a component of your app, therefore they will be instantiated and injected into other components if needed, all of them do the same, but their usage it's expected to differ depending on which part of your application they're.

- `@Service` must be used to annotate classes holding bussines logic
- `@Repository` must be used to annotate classes holding persistence access
- `@Configuration` must be used to annotae classes holding any kind of configuration for example the configuration needed to access a database
- `@Component` it's used to annotate classes you want to inject but do not fall into any of the previous categories

All of these annotations have an optional `id` parameter that can be used to identify them, if an `id` is passed that will be the id for that given class instance, if no `id` is passed the id will be the class type along with a random regenerated alphanumeric string for example `com.glaze.UserService-GFFH43GH`

#### How to inject components
Autumn supports both field and constructor based dependency injection along with qualifying instances.

##### Constructor Injection
Only one of the constructors of your class will be eligible for annotations, if one or no constructors are present the default one will be used, but if there are more than one constructors declared you must especify which one you want to make eligible for dependency injection by annotation it with `@Autowired`, if more than one constrcutors are declared and none of them is annotated with `@Autowired` the first declared constructor will be one used for dependency injection
```java
@Service
class ServiceOne(){

    // only declared constructor used
    public ServiceOne(UserRepository userRepository){
        declarations...
    }
}

class ServiceTwo {
    fields...

    public ServiceTwo(UserRepository userRepository){
        declarations...
    }

    @Autowired // constrictor eligible for dependency injection
    public ServiceTwo(UserRepository userRepository, PasswordEncoder passwordEncoder){
        declarions...
    }
}
```

##### Field injection
Annotating your fields with `@Autowired` will turn them into a required component for the class holding the autowired field
```java
class RandomService{
    other fields...

    @Autowired
    private UserRepository userRepository;
}
```

##### Qualifying aka getting the instance
The oods are you'll create a interface and a couple of implementations for it in your projects, the problem comes when you require a especfic instance but you still want to use the interface type, you can achieve this by using `@Qualifer` which holds and `id` parameter used to identify the instance you require, this `id` parameter must match with the one given by any of the [component annotations](#Components), if no matching instance is found an expcetion will be thrown.
```kotlin
@Service
class RandomService{

    @Qualifer(id = "email")
    @Autowired
    private NotificationService emailNotificationService;

    private final NotificationService pushNotificationService;
    public RandomService(@Qualifer(id = "push") NotificationService pushNotificationService){
        this.pushNotificationService = pushNotificationService;
    }

}

interface NotificationService {
    void sendNotification()
}

@Service(id = "push")
class PushNotificationService implements NotificationService

@Service(id = "email")
class EmailNotificationService implements NotificationService
```

### Beans
Beans are just like components except they are instantiated as a method, this method must meet some requirements
- Return type must be diffenret from `void` or `Void`
- Must take no parameters
- Must be inside a [Component](#Components)
- Must be annotated with `@Bean`

```kotlin
@Component
class DummyComponent {
    fields...
    constructors...

    @Bean(id = "pass")
    public PaswordEncoder passwordEncoder(){
        return new PaswordEncoder()
    }
}

class PaswordEncoder{}
```

Beans are not required to be a [component](#Components), but they can only be instantiated after the class holding the method has been, just like components Beans can take an optional `id` parameter is used for qualification.