package TestModels;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotSame;
//import static org.junit.Assert.assertTrue;
//import org.junit.Test;

import MakeItFit.users.Gender;
import MakeItFit.users.types.Occasional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OccasionalTest {

    private Occasional createSampleOccasional(int frequency) {
        return new Occasional(
                "Alice", 30, Gender.Female, 60.5f, 165, 70, 2,
                "Rua A", "912345678", "alice@email.com", frequency
        );
    }

    @Test
    public void testConstructorAndGetFrequency() {
        Occasional o = createSampleOccasional(5);
        assertEquals(5, o.getFrequency());
    }

    @Test
    public void testSetFrequencyValid() {
        Occasional o = createSampleOccasional(2);
        o.setFrequency(10);
        assertEquals(10, o.getFrequency());
        o.setFrequency(0);
        assertEquals(0, o.getFrequency());
    }

    @Test
    public void testSetFrequencyNegativeThrowsException() {
        Occasional o = createSampleOccasional(1);
//        o.setFrequency(-1);
        assertThrows(IllegalArgumentException.class, () -> o.setFrequency(-1));
    }

    @Test
    public void testCopyConstructor() {
        Occasional o1 = createSampleOccasional(7);
        Occasional o2 = new Occasional(o1);
        assertNotSame(o1, o2);
        assertEquals(o1.getFrequency(), o2.getFrequency());
        assertEquals(o1.toString(), o2.toString());
    }

    @Test
    public void testClone() {
        Occasional o1 = createSampleOccasional(3);
        Occasional o2 = o1.clone();
        assertNotSame(o1, o2);
        assertEquals(o1.getFrequency(), o2.getFrequency());
        assertEquals(o1.toString(), o2.toString());
    }

    @Test
    public void testToStringContainsFrequency() {
        Occasional o = createSampleOccasional(8);
        String str = o.toString();
        assertTrue(str.contains("Frequency: 8"));
        assertTrue(str.contains("Alice"));
    }

    @Test
    public void testSetFrequencyDoesNotAffectOtherInstances() {
        Occasional o1 = createSampleOccasional(2);
        Occasional o2 = createSampleOccasional(2);
        o1.setFrequency(15);
        assertEquals(15, o1.getFrequency());
        assertEquals(2, o2.getFrequency());
    }

    @Test
    public void testSetAndGetFrequencyEdgeCase() {
        Occasional o = createSampleOccasional(0);
        o.setFrequency(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, o.getFrequency());
    }
}
