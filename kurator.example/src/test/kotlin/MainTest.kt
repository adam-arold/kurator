import org.junit.jupiter.api.Test
import java.io.File

class MainTest {

    @Test
    fun test() {
        println("================= ${File("src/main/resources").absolutePath}")
    }
}
