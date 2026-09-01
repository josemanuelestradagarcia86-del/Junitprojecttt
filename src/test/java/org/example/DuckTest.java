package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DuckTest {

    private Duck duck;

    @Before
    public void setUp() {
        duck = new Duck() {
            @Override
            public void display() {
                System.out.println("Displaying Duck test implementation");
            }
        };
    }

    @Test
    public void testDuckNotNull() {
        assertNotNull("La instancia de Duck no debe ser nula", duck);
    }

    @Test
    public void testDuckInstance() {
        assertTrue("El objeto debe ser una instancia de Duck", duck instanceof Duck);
    }

    @Test
    public void testDisplayExecution() {
        try {
            duck.display();
        } catch (Exception e) {
            fail("El método display no debe lanzar excepciones: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        String result = duck.toString();
        assertNotNull("toString() no debe devolver null", result);
        assertEquals("Duck{}", result);
    }
}