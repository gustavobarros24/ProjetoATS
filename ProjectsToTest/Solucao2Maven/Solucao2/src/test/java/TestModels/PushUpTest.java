package TestModels;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import MakeItFit.activities.implementation.PushUp;
import MakeItFit.utils.MakeItFitDate;





public class PushUpTest {

    private UUID userCode;
    private MakeItFitDate realizationDate;
    private int expectedDuration;
    private String designation;
    private String name;
    private int repetitions;
    private int series;
    private PushUp pushUp;

    @Before
    public void setUp() {
        userCode = UUID.randomUUID();
        realizationDate = MakeItFitDate.of(2024, 6, 1);
        expectedDuration = 30;
        designation = "Push Up";
        name = "Standard Push Up";
        repetitions = 15;
        series = 4;
        pushUp = new PushUp(userCode, realizationDate, expectedDuration, designation, name, repetitions, series);
    }

    @Test
    public void testConstructorWithParams() {
        assertEquals(userCode, pushUp.getUserCode());
        assertEquals(realizationDate, pushUp.getRealizationDate());
        assertEquals(expectedDuration, pushUp.getExpectedDuration());
        assertEquals(designation, pushUp.getDesignation());
        assertEquals(name, pushUp.getName());
        assertEquals(repetitions, pushUp.getRepetitions());
        assertEquals(series, pushUp.getSeries());
    }

    @Test
    public void testDefaultConstructor() {
        PushUp defaultPushUp = new PushUp();
        assertNotNull(defaultPushUp);
    }

    @Test
    public void testCopyConstructor() {
        PushUp copy = new PushUp(pushUp);
        assertEquals(pushUp, copy);
        assertNotSame(pushUp, copy);
    }

    @Test
    public void testCaloricWasteCalculation() {
        float index = 2.0f;
        int expectedWaste = (int) (repetitions * series * index * 0.1);
        assertEquals(expectedWaste, pushUp.caloricWaste(index));
    }

    @Test
    public void testCalculateCaloricWasteSetsValue() {
        float index = 2.0f;
        int expectedWaste = (int) (repetitions * series * index * 0.1);
        pushUp.calculateCaloricWaste(index);
        assertEquals(expectedWaste, pushUp.getCaloricWaste());
    }

    @Test
    public void testToStringDelegatesToSuper() {
        String expected = pushUp.getClass().getSuperclass().cast(pushUp).toString();
        assertEquals(expected, pushUp.toString());
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(pushUp.equals(pushUp));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(pushUp.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(pushUp.equals("not a PushUp"));
    }

    @Test
    public void testEqualsSameValues() {
        PushUp other = new PushUp(pushUp);
        assertTrue(pushUp.equals(other));
    }

    @Test
    public void testCloneCreatesEqualButDistinctObject() {
        PushUp clone = pushUp.clone();
        assertEquals(pushUp, clone);
        assertNotSame(pushUp, clone);
    }
}
