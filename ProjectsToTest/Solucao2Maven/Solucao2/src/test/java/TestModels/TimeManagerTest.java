package TestModels;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNotSame;
//import org.junit.Before;
//import org.junit.Test;

import MakeItFit.time.TimeManager;
import MakeItFit.utils.MakeItFitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeManagerTest {

    private MakeItFitDate initialDate;
    private TimeManager timeManager;

    @BeforeEach
    public void setUp() {
        initialDate = MakeItFitDate.of(2024, 6, 1); // exemplo de data
        timeManager = new TimeManager(initialDate);
    }

    @Test
    public void testConstructorWithCurrentDate() {
        TimeManager tm = new TimeManager();
        assertNotNull(tm.getCurrentDate());
    }

    @Test
    public void testConstructorWithSpecifiedDate() {
        assertEquals(initialDate, timeManager.getCurrentDate());
    }

    @Test
    public void testGetCurrentDate() {
        assertEquals(initialDate, timeManager.getCurrentDate());
    }

    @Test
    public void testAdvanceTimePositiveDays() {
        MakeItFitDate expectedDate = initialDate.plusDays(5);
        MakeItFitDate newDate = timeManager.advanceTime(5);
        assertEquals(expectedDate, newDate);
        assertEquals(expectedDate, timeManager.getCurrentDate());
    }

    @Test
    public void testAdvanceTimeZeroDays() {
        MakeItFitDate expectedDate = initialDate.plusDays(0);
        MakeItFitDate newDate = timeManager.advanceTime(0);
        assertEquals(expectedDate, newDate);
        assertEquals(initialDate, timeManager.getCurrentDate());
    }

    @Test
    public void testAdvanceTimeNegativeDaysThrowsException() {
//        timeManager.advanceTime(-1);
        assertThrows(IllegalArgumentException.class, () -> timeManager.advanceTime(-1));
    }

    @Test
    public void testAdvanceTimeMultipleTimes() {
        MakeItFitDate expectedDate = initialDate.plusDays(2).plusDays(3);
        timeManager.advanceTime(2);
        MakeItFitDate newDate = timeManager.advanceTime(3);
        assertEquals(expectedDate, newDate);
        assertEquals(expectedDate, timeManager.getCurrentDate());
    }

    @Test
    public void testAdvanceTimeDoesNotChangeOriginalReference() {
        MakeItFitDate beforeAdvance = timeManager.getCurrentDate();
        timeManager.advanceTime(1);
        assertNotSame(beforeAdvance, timeManager.getCurrentDate());
    }
}
