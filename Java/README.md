## Java Autumn Example

### Setup
Your main class must be annotated with @AutumnApplication, as your main method will serve as a way to run the Autumn container, in order to execute your code logic, you must create a no args method with a return type of void, this method has to be annotated with @MainMethod
```java
import com.glaze.autumn.annotations.AutumnApplication;
import com.glaze.autumn.annotations.MainMethod;
import com.autumn.example.service.UserService;

@AutumnApplication
class Application implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @MainMethod
    public void run(){
        // your application code must go here
    }

    public static void main(String[] args){
        // your code logic goes on run method
        Autumn.run(Application.class);
    }

}
```

What did I just see?
- Autumn.run takes your main class as parameter as being the entry point of your application
- @MainMethod annotated method will be invoked after the container has instantiated all your classes correctly
- Your main class is also eligible for dependency injection

## Components
Annotating any class with one of the following annotations turns it into a component of your app, therefore they will be instantiated and injected into other components if needed, all of them do the same, but their usage it's expected to differ depending on which part of your application they're.

- `@Service` must be used to annotate classes holding business logic
- `@Repository` must be used to annotate classes holding persistence access
- `@Configuration` must be used to annotate classes holding any kind of configuration for example the configuration needed to access a database
- `@Component` it's used to annotate classes you want to inject but do not fall into any of the previous categories

All of these annotations have an optional id parameter that can be used to identify them, if an id is passed that will be the id for that given class instance, if no id is passed the id will be the class name, for instance `com.glaze.http.HttpClient`

#### How to inject components
Autumn supports both field and constructor based dependency injection along with qualifying instances.

##### Constructor Injection
Only one your class's constructors is eligible for dependency injection, 
if one or no constructors are present the default one will be used, 
in case there are more than one constructor declared you must specify 
which one you want to make eligible for dependency injection by using `@Autowired`, if more than one constructor are declared and none of them is annotated with `@Autowired` the first declared constructor will be one used for dependency injection
```java
@Service
class ServiceOne(){

    // only declared constructor used
    public ServiceOne(UserRepository userRepository){
        // declarations...
    }
}

class ServiceTwo {
    // fields...

    public ServiceTwo(UserRepository userRepository){
        // declarations...
    }

    @Autowired // constrictor eligible for dependency injection
    public ServiceTwo(UserRepository userRepository, PasswordEncoder passwordEncoder){
        // declarations...
    }
}
```

##### Field injection
Annotating your fields with `@Autowired` will turn them into a required component for the class holding the autowired field
```java
class RandomService{
    // other fields...

    @Autowired
    private UserRepository userRepository;
}
```

##### Qualifying aka getting the instance
The odds are you'll create an interface and a couple of implementations for it in your projects, the problem comes when you require a specific instance, but you still want to use the interface type, you can achieve this by using `@Qualifer` which holds and `id` parameter used to identify the instance you require, this `id` parameter must match with the one given by any of the [component annotations](#Components), if no matching instance is found an exception will be thrown.
```java
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
    void sendNotification();
}

@Service(id = "push")
class PushNotificationService implements NotificationService {}

@Service(id = "email")
class EmailNotificationService implements NotificationService {}
```

### Beans
Beans are just like components except they are instantiated as a method, this method must meet some requirements
- Return type must be different from `void` or `Void`
- Must take no parameters
- Must be inside a [Component](#Components)
- Must be annotated with `@Bean`

```java
@Component
class DummyComponent {
    // fields...
    // constructors...

    @Bean(id = "pass")
    public PaswordEncoder passwordEncoder() {
        return new PaswordEncoder();
    }
}

class PasswordEncoder{}
```

Beans can or can not be a [component](#Components), but they can only be instantiated after the class holding the method
has been, just like components, Beans can take an optional `id` parameter
that is going to be used for qualification, if no `id` parameter is passed
the method name will serve as the `ìd`
