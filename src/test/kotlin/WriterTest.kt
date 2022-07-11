import com.cardinalhealth.katas.Writer
import org.junit.Assert.assertEquals
import org.junit.Test

class WriterTest {

    @Test
    fun `it can Erase Text`() {
        val result = Writer("This is the full sentence").erase("This")
        assertEquals("     is the full sentence", result)
    }

    @Test
    fun `it can write text`() {
        val result = Writer().write("Stuff")
        assertEquals("Stuff", result)
    }

    @Test
    fun `it can write additional text`() {
        val result = Writer("Stuff").write(" and things")
        assertEquals("Stuff and things", result)
    }
}
