package TestModels;

import java.util.List;
import java.util.UUID;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNotSame;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.Test;

import MakeItFit.activities.Activity;
import MakeItFit.activities.implementation.PushUp;
import MakeItFit.activities.implementation.Running;
import MakeItFit.activities.implementation.Trail;
import MakeItFit.activities.implementation.WeightSquat;
import MakeItFit.trainingPlan.TrainingPlan;
import MakeItFit.utils.MakeItFitDate;
import MakeItFit.utils.MyTuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainingPlanTest {

    private TrainingPlan plan;
    private UUID userCode;
    private MakeItFitDate startDate;
    private PushUp pushUp;
    private Running running;
    private Trail trail;
    private WeightSquat squat;

    @BeforeEach
    public void setUp() {
        userCode = UUID.randomUUID();
        startDate = MakeItFitDate.of(2024, 6, 1);
        plan = new TrainingPlan(userCode, startDate);
        pushUp = new PushUp(userCode, MakeItFitDate.of(2024, 5, 1), 30, "Push", "PushUp", 10, 3);
        running = new Running(userCode, MakeItFitDate.of(2024, 5, 2), 60, "Run", "Running", 5000, 10.0);
        trail = new Trail(userCode, MakeItFitDate.of(2024, 5, 3), 90, "Trail", "Trail", 10000, 200, 100, Trail.TRAIL_TYPE_MEDIUM);
        squat = new WeightSquat(userCode, MakeItFitDate.of(2024, 5, 4), 40, "Squat", "WeightSquat", 8, 4, 60.0);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(userCode, plan.getUserCode());
        assertEquals(startDate, plan.getStartDate());
        assertNotNull(plan.getCode());
        assertNotNull(plan.getActivities());
        assertTrue(plan.getActivities().isEmpty());
    }

    @Test
    public void testDefaultConstructor() {
        TrainingPlan defaultPlan = new TrainingPlan();
        assertNotNull(defaultPlan.getUserCode());
        assertNotNull(defaultPlan.getCode());
        assertNotNull(defaultPlan.getStartDate());
        assertNotNull(defaultPlan.getActivities());
        assertTrue(defaultPlan.getActivities().isEmpty());
    }

    @Test
    public void testCopyConstructor() {
        plan.addActivity(5, pushUp);
        TrainingPlan copy = new TrainingPlan(plan);
        assertEquals(plan.getUserCode(), copy.getUserCode());
        assertEquals(plan.getCode(), copy.getCode());
        assertEquals(plan.getStartDate(), copy.getStartDate());
        assertEquals(plan.getActivities(), copy.getActivities());
    }

    @Test
    public void testSetStartDate() {
        MakeItFitDate newDate = MakeItFitDate.of(2025, 1, 1);
        plan.setStartDate(newDate);
        assertEquals(newDate, plan.getStartDate());
    }

    @Test
    public void testAddActivity() {
        plan.addActivity(10, running);
        List<MyTuple<Integer, Activity>> acts = plan.getActivities();
        assertEquals(1, acts.size());
        assertEquals(Integer.valueOf(10), acts.get(0).getItem1());
        assertEquals(running, acts.get(0).getItem2());
    }

    @Test
    public void testRemoveActivity() {
        plan.addActivity(10, pushUp);
        plan.addActivity(5, running);
        plan.removeActivity(pushUp.getCode());
        assertEquals(1, plan.getActivities().size());
        assertEquals(running, plan.getActivities().get(0).getItem2());
    }

    @Test
    public void testRemoveActivityNotFound() {
        plan.addActivity(10, pushUp);
        plan.removeActivity(UUID.randomUUID());
        assertEquals(1, plan.getActivities().size());
    }

    @Test
    public void testUpdateActivities() {
        plan.addActivity(10, pushUp); // realizationDate < currentDate
        plan.addActivity(5, running);  // realizationDate < currentDate
        plan.addActivity(2, trail);    // realizationDate < currentDate
        plan.addActivity(1, squat);    // realizationDate < currentDate
        plan.updateActivities(MakeItFitDate.of(2024, 6, 2), 1.0f);
        // Não há método para verificar se foi atualizado, mas não lança exceção
        assertEquals(4, plan.getActivities().size());
    }

    @Test
    public void testExtractActivities() {
        plan.addActivity(10, pushUp); // realizationDate < currentDate
        plan.addActivity(5, running);  // realizationDate < currentDate
        plan.addActivity(2, trail);    // realizationDate < currentDate
        plan.addActivity(1, squat);    // realizationDate < currentDate
        List<Activity> extracted = plan.extractActivities(MakeItFitDate.of(2024, 6, 2));
        assertEquals(4, extracted.size());
        for (int i = 0; i < extracted.size(); i++) {
            assertNotSame(plan.getActivities().get(i).getItem2(), extracted.get(i));
        }
    }

    @Test
    public void testToString() {
        plan.addActivity(10, pushUp);
        String str = plan.toString();
        assertTrue(str.contains("Training plan details"));
        assertTrue(str.contains(plan.getCode().toString()));
        assertTrue(str.contains(plan.getUserCode().toString()));
        assertTrue(str.contains(plan.getStartDate().toString()));
        assertTrue(str.contains("Activities(Iterations / Activity)"));
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(plan.equals(plan));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(plan.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(plan.equals("not a plan"));
    }

    @Test
    public void testEqualsDifferentObject() {
        TrainingPlan other = new TrainingPlan(UUID.randomUUID(), startDate);
        assertFalse(plan.equals(other));
    }

    @Test
    public void testEqualsSameValues() {
        plan.addActivity(10, pushUp);
        TrainingPlan other = new TrainingPlan(plan.getUserCode(), plan.getStartDate());
        other.addActivity(10, pushUp);
        assertTrue(plan.equals(other));
    }

    @Test
    public void testCompareToDifferentDates() {
        TrainingPlan later = new TrainingPlan(userCode, MakeItFitDate.of(2025, 1, 1));
        assertTrue(plan.compareTo(later) < 0);
        assertTrue(later.compareTo(plan) > 0);
    }

    @Test
    public void testCompareToSameDateDifferentActivities() {
        TrainingPlan other = new TrainingPlan(userCode, startDate);
        plan.addActivity(10, pushUp);
        other.addActivity(10, pushUp);
        other.addActivity(5, running);
        assertTrue(plan.compareTo(other) < 0);
        assertTrue(other.compareTo(plan) > 0);
    }

    @Test
    public void testCompareToSameDateSameActivities() {
        TrainingPlan other = new TrainingPlan(userCode, startDate);
        plan.addActivity(10, pushUp);
        other.addActivity(10, pushUp);
        assertEquals(0, plan.compareTo(other));
    }

    @Test
    public void testClone() {
        plan.addActivity(10, pushUp);
        TrainingPlan clone = plan.clone();
        assertNotSame(plan, clone);
        assertEquals(plan.getUserCode(), clone.getUserCode());
        assertEquals(plan.getCode(), clone.getCode());
        assertEquals(plan.getStartDate(), clone.getStartDate());
        assertEquals(plan.getActivities(), clone.getActivities());
    }
}
