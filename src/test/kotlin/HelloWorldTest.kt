import com.cardinalhealth.katas.Eraser
import org.junit.Assert.assertEquals
import org.junit.Test

class HelloWorldTest {

    @Test
    fun `it can Erase Text`() {
        val result = Eraser().erase("This is the full sentence", "This")
        assertEquals("     is the full sentence", result)
    }

}
