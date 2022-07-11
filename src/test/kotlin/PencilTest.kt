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

    @Test
    fun `sharpening a pencil resets its length`() {
        val initialLength = 10
        val pencil = Pencil(10, initialLength)
        pencil.sharpen()
        assertEquals(initialLength - 1, pencil.length)
    }

    @Test
    fun `sharpening a pencil of length 0 does not restore point durability`() {
        val initialPointDurability = 4
        val initialLength = 0
        val pencil = Pencil(initialPointDurability, initialLength)
        val paper = Paper()
        pencil.write(paper, "Woot")
        assertEquals(0, pencil.pointDurability)
        pencil.sharpen()
        assertEquals(0, pencil.pointDurability)
        assertEquals(0, pencil.length)
    }

    @Test
    fun `text can be erased`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "Erase Me Please")
        pencil.erase(paper, "Erase Me")
        assertEquals("         Please", paper.read())
    }

    @Test
    fun `more text can be erased`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "ABCDEFG")
        pencil.erase(paper, "B")
            .erase(paper, "D")
            .erase(paper, "F")
        assertEquals("A C E G", paper.read())
    }

    @Test
    fun `it erases the last occurrence of a string`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "badger badger badger mushroom mushroom")
        pencil.erase(paper, "mushroom")
            .erase(paper, "badger")
        assertEquals("badger badger        mushroom         ", paper.read())
    }

    @Test
    fun `it continues to search earlier for an occurrence if erasing the same string repeatedly`() {
        val pencil = Pencil()
        val paper = Paper()
        pencil.write(paper, "How much wood would a woodchuck chuck if a woodchuck could chuck wood?")
        pencil.erase(paper, "wood")
            .erase(paper, "wood")
        assertEquals("How much wood would a woodchuck chuck if a     chuck could chuck     ?", paper.read())
    }

    @Test
    fun `erasing degrades eraser durability`() {
        val initialEraserDurability = 5
        val pencil = Pencil(initialEraserDurability = initialEraserDurability)
        val paper = Paper()
        pencil.write(paper, "Fish Sticks")
        pencil.erase(paper, "Fish")
        assertEquals(1, pencil.eraserDurability)
    }
}
