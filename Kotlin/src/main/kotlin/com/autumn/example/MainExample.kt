import com.autumn.example.service.PrintService
import com.autumn.example.service.UserService
import com.glaze.autumn.Autumn
import com.glaze.autumn.annotations.Autowired
import com.glaze.autumn.annotations.AutumnApplication
import com.glaze.autumn.annotations.MainMethod

@AutumnApplication
class MainExample(private val userService: UserService) {

    @Autowired
    private lateinit var printService: PrintService

    @MainMethod
    fun run() {
        userService.saveUser("Glaze", "EpicPassword")
        printService.print()
    }
}

fun main() {
    Autumn.run(MainExample::class.java)
}
