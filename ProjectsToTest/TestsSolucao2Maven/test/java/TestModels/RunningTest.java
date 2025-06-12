package TestModels;

import MakeItFit.activities.implementation.Running;
import MakeItFit.utils.MakeItFitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;
import java.util.UUID;
//import static org.junit.Assert.*;


import static org.junit.jupiter.api.Assertions.*;

public class RunningTest {

    private Running running1;
    private Running running2;
    private UUID userCode;
    private MakeItFitDate date;

    @BeforeEach
    public void setUp() {
        userCode = UUID.randomUUID();
        date = MakeItFitDate.of(2024, 6, 1);

        running1 = new Running(userCode, date, 60, "Morning Run", "Run1", 10000, 10.5);
        running2 = new Running(running1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(userCode, running1.getUserCode());
        assertEquals(date, running1.getRealizationDate());
        assertEquals(60, running1.getExpectedDuration());
        assertEquals("Morning Run", running1.getDesignation());
        assertEquals("Run1", running1.getName());
        assertEquals(10000, running1.getDistance(), 0.001);
        assertEquals(10.5, running1.getSpeed(), 0.001);
    }

    @Test
    public void testDefaultConstructor() {
        Running r = new Running();
        assertEquals(0, r.getSpeed(), 0.001);
    }

    @Test
    public void testCopyConstructor() {
        assertEquals(running1, running2);
        assertNotSame(running1, running2);
    }

    @Test
    public void testSetAndGetSpeed() {
        running1.setSpeed(12.3);
        assertEquals(12.3, running1.getSpeed(), 0.001);
    }

    @Test
    public void testCaloricWaste() {
        float index = 1.2f;
        int expected = (int) (10.5 * 10000 * index * 0.005);
        assertEquals(expected, running1.caloricWaste(index));
    }

    @Test
    public void testCalculateCaloricWaste() {
        float index = 1.5f;
        running1.calculateCaloricWaste(index);
        int expected = (int) (10.5 * 10000 * index * 0.005);
        assertEquals(expected, running1.getCaloricWaste());
    }

    @Test
    public void testToString() {
        String str = running1.toString();
        assertTrue(str.contains("Speed: 10.5 Km/h"));
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(running1.equals(running1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(running1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(running1.equals("not a Running"));
    }

    @Test
    public void testEqualsDifferentSpeed() {
        Running r = new Running(userCode, date, 60, "Morning Run", "Run1", 10000, 9.0);
        assertFalse(running1.equals(r));
    }

    @Test
    public void testEqualsDifferentDistance() {
        Running r = new Running(userCode, date, 60, "Morning Run", "Run1", 9000, 10.5);
        assertFalse(running1.equals(r));
    }

    @Test
    public void testClone() {
        Running clone = running1.clone();
        assertEquals(running1, clone);
        assertNotSame(running1, clone);
    }
}
