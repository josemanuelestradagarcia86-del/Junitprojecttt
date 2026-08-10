import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void fizzTest() {
        assertEquals("Fizz", FizzBuzz.get(3));
    }

    @Test
    public void buzzTest() {
        assertEquals("Buzz", FizzBuzz.get(5));
    }

    @Test
    public void fizzBuzzTest() {
        assertEquals("FizzBuzz", FizzBuzz.get(15));
    }

    @Test
    public void numberTest() {
        assertEquals("7", FizzBuzz.get(7));
    }
}
