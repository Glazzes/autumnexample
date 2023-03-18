## Kotlin Autumn Example

### Setup
Your main class must be annotated with `@AutumnApplication`, as your main
method will serve as a way to run the `Autumn container`, in order to execute
your code logic, you must create a no args method with a return type of void, 
this method has to be annotated with `@MainMethod`
```kotlin
import com.glaze.autumn.annotations.AutumnApplication
import com.glaze.autumn.annotations.MainMethod
import com.autumn.example.service.UserService

@AutumnApplication
class Application : CommandLineRunner {
    @Autowired private lateinit var userService: UserService
    
    @MainMethod
    fun anyMethodNameYouWant() {
        // your application code logic goes here
    }
}

fun main() {
    Autumn.run(Application::class.java)
}

```

What did I just see?
- Autumn.run takes your main class as parameter as being the entry point of your application
- @MainMethod annotated method will be invoked after the container has 
instantiated all your classes correctly
- Your main class is also eligible for dependency injection  
  
## Components
Annotating any class with one of the following annotations turns it into a component of your app, therefore they will be instantiated and injected into other components if needed, all of them do the same, but their usage it's expected to differ depending on which part of your application they're.

- `@Service` must be used to annotate classes holding business logic
- `@Repository` must be used to annotate classes holding persistence access
- `@Configuration` must be used to annotate classes holding any kind of configuration for example the configuration needed to access a database
- `@Component` it's used to annotate classes you want to inject but do not fall into any of the previous categories

All of these annotations have an optional `id` parameter that can be used 
to identify them, if an `id` is passed that will be the id for that given 
class instance, if no `id` is passed the id will be the class name, for instance `com.glaze.http.HttpClient`

#### How to inject components
Autumn supports both field and constructor based dependency injection along with qualifying instances.

##### Constructor Injection
Only one of the constructors of your class will be eligible for annotations, if one or no constructors are present the default one will be used, but if there are more than one constructor declared you must specify which one you want to make eligible for dependency injection by annotation it with `@Autowired`, if more than one constructor has been declared and none of them is annotated with `@Autowired` the first declared constructor will be one used for dependency injection
```kotlin
@Service // default constructor used
class ServiceOne(private val userRepository: UserRepository)

class ServiceTwo {
    // fields...
    constructor(val userRepository: UserRepository){
        // declarations...
    }

    @Autowired // <= constrictor eligible for dependency injection
    constructor(val userRepository: UserRepository, val encoder: PasswordEncoder){
        // declarations...
    }
}
```

##### Field injection
Annotating your fields with `@Autowired` will turn them into a required component for the class holding the autowired field
```kotlin
@Component
class RandomService{
    // other fields...

    @Autowired
    private lateinit var userRepository: UserRepository
}
```

##### Qualifying aka getting the instance
The odds are you'll create an interface and a couple of implementations for it in your projects, the problem comes when you require a specific instance, but you still want to use its interface type, you can achieve this by using `@Qualifer` which holds and `id` parameter used to identify the instance you require, this `id` parameter must match with the one given by any of the [component annotations](#Components), if no matching instance could have been found an exception will be thrown.
```kotlin
@Service
class RandomService(@Qualifer(id = "push") val pushNotificationService: NotificationService){

    @Qualifer(id = "email")
    @Autowired
    private lateinit var emailNotificationService: NotificationService
}

interface NotificationService {
    fun sendNotification()
}

@Service(id = "push")
class PushNotificationService : NotificationService

@Service(id = "email")
class EmailNotificationService : NotificationService
```

### Beans
Beans are just like components except they are instantiated as a method, this method must meet some requirements
- Return type must be different from `Unit` or equivalents in Java
- Must take no parameters
- Must be inside a [Component](#Components)
- Must be annotated with `@Bean`

```kotlin
@Component
class DummyComponent {
    // fields...
    // constructors...

    @Bean(id = "pass")
    fun passwordEncoder(): PaswordEncoder{
        return PaswordEncoder()
    }
}

class PasswordEncoder
```

Beans can or can not be a [component](#Components), but they can only be instantiated after the class holding the method
has been, just like components, Beans can take an optional `id` parameter 
that is going to be used for qualification, if no `id` parameter is passed
the method name will serve as the `ìd`
