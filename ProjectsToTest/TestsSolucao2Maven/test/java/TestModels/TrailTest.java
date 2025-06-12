package TestModels;

import MakeItFit.activities.implementation.Trail;
import MakeItFit.utils.MakeItFitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;
import java.util.UUID;
//import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;


public class TrailTest {

    private Trail trail;
    private UUID userCode;
    private MakeItFitDate date;

    @BeforeEach
    public void setUp() {
        userCode = UUID.randomUUID();
        date = MakeItFitDate.of(2024, 6, 1);
        trail = new Trail(userCode, date, 120, "Trail Run", "Morning Trail", 10.0, 200.0, 100.0, Trail.TRAIL_TYPE_MEDIUM);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(userCode, trail.getUserCode());
        assertEquals(date, trail.getRealizationDate());
        assertEquals(120, trail.getExpectedDuration());
        assertEquals("Trail Run", trail.getDesignation());
        assertEquals("Morning Trail", trail.getName());
        assertEquals(10.0, trail.getDistance(), 0.001);
        assertEquals(200.0, trail.getElevationGain(), 0.001);
        assertEquals(100.0, trail.getElevationLoss(), 0.001);
        assertEquals(Trail.TRAIL_TYPE_MEDIUM, trail.getTrailType());
    }

    @Test
    public void testDefaultConstructor() {
        Trail t = new Trail();
        assertEquals(Trail.TRAIL_TYPE_EASY, t.getTrailType());
    }

    @Test
    public void testCopyConstructor() {
        Trail copy = new Trail(trail);
        assertEquals(trail, copy);
        assertNotSame(trail, copy);
    }

    @Test
    public void testSetTrailTypeWithinBounds() {
        trail.setTrailType(Trail.TRAIL_TYPE_HARD);
        assertEquals(Trail.TRAIL_TYPE_HARD, trail.getTrailType());
        trail.setTrailType(Trail.TRAIL_TYPE_EASY);
        assertEquals(Trail.TRAIL_TYPE_EASY, trail.getTrailType());
        trail.setTrailType(Trail.TRAIL_TYPE_MEDIUM);
        assertEquals(Trail.TRAIL_TYPE_MEDIUM, trail.getTrailType());
    }

    @Test
    public void testSetTrailTypeOutOfBounds() {
        trail.setTrailType(-1);
        assertEquals(Trail.TRAIL_TYPE_EASY, trail.getTrailType());
        trail.setTrailType(99);
        assertEquals(Trail.TRAIL_TYPE_HARD, trail.getTrailType());
    }

    @Test
    public void testCaloricWasteCalculation() {
        float index = 100f;
        int expected = (int) ((10.0 * 0.5 + 200.0 * 0.1 - 100.0 * 0.1) * index * 0.01);
        assertEquals(expected, trail.caloricWaste(index));
    }

    @Test
    public void testCalculateCaloricWasteSetsValue() {
        float index = 100f;
        trail.calculateCaloricWaste(index);
        int expected = trail.caloricWaste(index);
        assertEquals(expected, trail.getCaloricWaste());
    }

    @Test
    public void testToStringContainsTrailType() {
        String str = trail.toString();
        assertTrue(str.contains("Trail Type: " + Trail.TRAIL_TYPE_MEDIUM));
    }

    @Test
    public void testEqualsAndHashCode() {
        Trail t2 = new Trail(trail);
        assertTrue(trail.equals(t2));
        assertTrue(trail.equals(trail));
        assertFalse(trail.equals(null));
        assertFalse(trail.equals("not a trail"));
        t2.setTrailType(Trail.TRAIL_TYPE_HARD);
        assertFalse(trail.equals(t2));
    }

    @Test
    public void testClone() {
        Trail clone = trail.clone();
        assertEquals(trail, clone);
        assertNotSame(trail, clone);
    }
}
