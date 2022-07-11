import com.cardinalhealth.katas.Paper
import com.cardinalhealth.katas.Pencil
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PencilTest {

    @Test
    fun `we can read a blank piece of paper`() {
        val paper = Paper()
        assertEquals("", paper.read())
    }

    @Test
    fun `we can read a piece of paper that has text on it`() {
        val paper = Paper("Hello World")
        assertEquals("Hello World", paper.read())
    }

    @Test
    fun `we can write on the paper with a pencil`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "Hello!")
        assertEquals("Hello!", paper.read())
    }

    @Test
    fun `writing multiple times appends text to the paper`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "She sells sea shells")
        pencil.write(paper, " down by the sea shore")
        assertEquals("She sells sea shells down by the sea shore", paper.read())
    }

    @Test
    fun `writing with a pencil decreases its durability`() {
        val initialPointDurability = 10
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "a")
        assertEquals(initialPointDurability - 1, pencil.pointDurability)
    }

    @Test
    fun `writing spaces does not reduce durability`() {
        val initialPointDurability = 10
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "     a         ")
        assertEquals(initialPointDurability - 1, pencil.pointDurability)
    }

    @Test
    fun `writing newlines does not reduce durability`() {
        val initialPointDurability = 10
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "\na\n")
        assertEquals(initialPointDurability - 1, pencil.pointDurability)
    }

    @Test
    fun `writing with a dull pencil produces spaces`() {
        val initialPointDurability = 0
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "is this thing on?")
        assertEquals("                 ", paper.read())
    }

    @Test
    fun `spaces appear immediately after a pencil becomes dull`() {
        val initialPointDurability = 4
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "Howdy!")
        assertEquals("How   ", paper.read())
    }

    @Test
    fun `uppercase letters reduce visibility by 2`() {
        val initialPointDurability = 10
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "\nA\n")
        assertEquals(initialPointDurability - 2, pencil.pointDurability)
    }

    @Test
    fun `sharpening a pencil resets its durability`() {
        val initialPointDurability = 4
        val pencil = Pencil(initialPointDurability)
        val paper = Paper()
        pencil.write(paper, "Woot")
        assertEquals(0, pencil.pointDurability)
        pencil.sharpen()
        assertEquals(initialPointDurability, pencil.pointDurability)
    }
}
