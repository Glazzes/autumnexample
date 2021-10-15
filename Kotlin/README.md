## Kotlin Autumn Example

### Setup
Your main class must be annotated with `@AutumnApplication` and implement `CommandLineRunner`.
```kotlin
import com.glaze.autumn.annotations.AutumnApplication
import com.glaze.autumn.application.CommandLineRunner
import com.autumn.example.service.UserService

@AutumnApplication
class Application : CommandLineRunner {
    @Autowired private lateinit var userService: UserService

    override fun run(){
        // your application code logic goes here
    }
}

fun main(){
    Autumn.run(Application::class.java)
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
```kotlin
@Service // deault constructor used
class ServiceOne(private val userRepository: UserRepository)

class ServiceTwo {
    fields...
    constructor(val userRepository: UserRepository){
        declarations...
    }

    @Autowired // constrictor eligible for dependency injection
    constructor(val userRepository: UserRepository, val enconder: PasswordEncoder){
        declarions...
    }
}
```

##### Field injection
Annotating your fields with `@Autowired` will turn them into a required component for the class holding the autowired field
```kotlin
class RandomService{
    other fields...

    @Autowired
    private lateinit var userRepository: UserRepository;
}
```

##### Qualifying aka getting the instance
The oods are you'll create a interface and a couple of implementations for it in your projects, the problem comes when you require a especfic instance but you still want to use the interface type, you can achieve this by using `@Qualifer` which holds and `id` parameter used to identify the instance you require, this `id` parameter must match with the one given by any of the [component annotations](#Components), if no matching instance is found an expcetion will be thrown.
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
- Return type must be diffenret from `Unit` or equivalents in Java
- Must take no parameters
- Must be inside a [Component](#Components)
- Must be annotated with `@Bean`

```kotlin
@Component
class DummyComponent {
    fields...
    constructors...

    @Bean(id = "pass")
    fun passwordEncoder(): PaswordEncoder{
        return PaswordEncoder()
    }
}

class PaswordEncoder
```

Beans are not required to be a [component](#Components), but they can only be instantiated after the class holding the method has been, just like components Beans can take an optional `id` parameter is used for qualification.