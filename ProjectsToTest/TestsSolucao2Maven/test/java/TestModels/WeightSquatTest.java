package TestModels;

import MakeItFit.activities.implementation.WeightSquat;
import MakeItFit.utils.MakeItFitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;
import java.util.UUID;
//import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;


public class WeightSquatTest {

    private WeightSquat ws1;
    private WeightSquat ws2;
    private UUID userCode;
    private MakeItFitDate date;

    @BeforeEach
    public void setUp() {
        userCode = UUID.randomUUID();
        date = MakeItFitDate.of(2024, 6, 1);
        ws1 = new WeightSquat(userCode, date, 60, "Leg Day", "Weighted Squat", 10, 4, 50.0);
        ws2 = new WeightSquat(ws1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(userCode, ws1.getUserCode());
        assertEquals(date, ws1.getRealizationDate());
        assertEquals(60, ws1.getExpectedDuration());
        assertEquals("Leg Day", ws1.getDesignation());
        assertEquals("Weighted Squat", ws1.getName());
        assertEquals(10, ws1.getRepetitions());
        assertEquals(4, ws1.getSeries());
        assertEquals(50.0, ws1.getWeight(), 0.001);
    }

    @Test
    public void testCopyConstructor() {
        assertEquals(ws1, ws2);
        assertNotSame(ws1, ws2);
    }

    @Test
    public void testCaloricWasteCalculation() {
        float index = 1.5f;
        int expected = (int) (10 * 4 * 50.0 * 0.2 * index * 0.5);
        assertEquals(expected, ws1.caloricWaste(index));
    }

    @Test
    public void testCalculateCaloricWasteSetsValue() {
        float index = 2.0f;
        int expected = ws1.caloricWaste(index);
        ws1.calculateCaloricWaste(index);
        assertEquals(expected, ws1.getCaloricWaste());
    }

    @Test
    public void testToStringDelegatesToSuper() {
        String expected = ws1.toString();
        assertEquals(expected, ws1.toString());
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(ws1.equals(ws1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(ws1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(ws1.equals("not a WeightSquat"));
    }

    @Test
    public void testEqualsDifferentObject() {
        WeightSquat wsDiff = new WeightSquat(UUID.randomUUID(), date, 60, "Leg Day", "Weighted Squat", 10, 4, 80.0);
        assertFalse(ws1.equals(wsDiff));
    }

    @Test
    public void testCloneCreatesEqualButDistinctObject() {
        WeightSquat clone = ws1.clone();
        assertEquals(ws1, clone);
        assertNotSame(ws1, clone);
    }
}
