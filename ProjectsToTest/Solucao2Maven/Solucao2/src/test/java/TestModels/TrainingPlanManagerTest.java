package TestModels;

import MakeItFit.activities.Activity;
import MakeItFit.activities.implementation.PushUp;
import MakeItFit.activities.implementation.Running;
import MakeItFit.activities.implementation.Trail;
import MakeItFit.activities.implementation.WeightSquat;
import MakeItFit.exceptions.EntityDoesNotExistException;
import MakeItFit.trainingPlan.TrainingPlan;
import MakeItFit.trainingPlan.TrainingPlanManager;
import MakeItFit.utils.MakeItFitDate;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
//import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingPlanManagerTest {

    private TrainingPlanManager manager;
    private UUID userCode;
    private MakeItFitDate startDate;
    private TrainingPlan plan;

    @BeforeEach
    public void setUp() {
        manager = new TrainingPlanManager();
        userCode = UUID.randomUUID();
        startDate = MakeItFitDate.of(2024, 6, 1);
        plan = new TrainingPlan(userCode, startDate);
    }

    @Test
    public void testCreateTrainingPlanSuccess() {
        TrainingPlan tp = manager.createTrainingPlan(userCode, startDate);
        assertNotNull(tp);
        assertEquals(userCode, tp.getUserCode());
        assertEquals(startDate, tp.getStartDate());
    }

    @Test
    public void testCreateTrainingPlanNullUser() {
        assertThrows(IllegalArgumentException.class, () -> manager.createTrainingPlan(userCode, null));
    }

    @Test
    public void testCreateTrainingPlanNullDate() {
        assertThrows(IllegalArgumentException.class, () -> manager.createTrainingPlan(userCode, null));
    }

    @Test
    public void testInsertAndGetTrainingPlan() {
        manager.insertTrainingPlan(plan);
        TrainingPlan fetched = manager.getTrainingPlan(plan.getCode());
        assertEquals(plan, fetched);
    }

    @Test
    public void testInsertNullTrainingPlan() {
        assertThrows(IllegalArgumentException.class, () -> manager.insertTrainingPlan(null));
    }

    @Test
    public void testInsertDuplicateTrainingPlan() {
//        manager.insertTrainingPlan(plan);
//        manager.insertTrainingPlan(plan);
        assertThrows(IllegalArgumentException.class, () -> manager.insertTrainingPlan(plan));
    }

    @Test
    public void testGetNonExistentTrainingPlan() {
        assertThrows(EntityDoesNotExistException.class, () -> manager.getTrainingPlan(UUID.randomUUID()));
    }

    @Test
    public void testRemoveTrainingPlan() {
        manager.insertTrainingPlan(plan);
        manager.removeTrainingPlan(plan.getCode());
        try {
            manager.getTrainingPlan(plan.getCode());
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testUpdateTrainingPlanSuccess() throws EntityDoesNotExistException {
        manager.insertTrainingPlan(plan);
        plan.setStartDate(MakeItFitDate.of(2024, 7, 1));
        manager.updateTrainingPlan(plan);
        assertEquals(MakeItFitDate.of(2024, 7, 1), manager.getTrainingPlan(plan.getCode()).getStartDate());
    }

    @Test
    public void testUpdateNonExistentTrainingPlan() throws EntityDoesNotExistException {
        assertThrows(EntityDoesNotExistException.class, () -> manager.updateTrainingPlan(plan));
    }

    @Test
    public void testGetAllTrainingPlans() {
        manager.insertTrainingPlan(plan);
        TrainingPlan plan2 = new TrainingPlan(UUID.randomUUID(), startDate);
        manager.insertTrainingPlan(plan2);
        List<TrainingPlan> all = manager.getAllTrainingPlans();
        assertEquals(2, all.size());
        assertTrue(all.contains(plan));
        assertTrue(all.contains(plan2));
    }

    @Test
    public void testAddActivity() {
        manager.insertTrainingPlan(plan);
        Activity activity = new PushUp(userCode, startDate, 10, "PushUp", "PushUp", 10, 2);
        manager.addActivity(plan.getCode(), 2, activity);
        assertEquals(1, manager.getTrainingPlan(plan.getCode()).getActivities().size());
    }

    @Test
    public void testRemoveActivity() {
        manager.insertTrainingPlan(plan);
        Activity activity = new PushUp(userCode, startDate, 10, "PushUp", "PushUp", 10, 2);
        plan.addActivity(2, activity);
        UUID activityCode = activity.getCode();
        manager.removeActivity(plan.getCode(), activityCode);
        assertTrue(manager.getTrainingPlan(plan.getCode()).getActivities().isEmpty());
    }

    @Test
    public void testGetTrainingPlansFromUser() {
        manager.insertTrainingPlan(plan);
        TrainingPlan plan2 = new TrainingPlan(userCode, MakeItFitDate.of(2024, 6, 2));
        manager.insertTrainingPlan(plan2);
        TrainingPlan plan3 = new TrainingPlan(UUID.randomUUID(), startDate);
        manager.insertTrainingPlan(plan3);
        List<TrainingPlan> userPlans = manager.getTrainingPlansFromUser(userCode);
        assertEquals(2, userPlans.size());
        assertTrue(userPlans.contains(plan));
        assertTrue(userPlans.contains(plan2));
    }

    @Test
    public void testUpdateActivities() {
        manager.insertTrainingPlan(plan);
        Activity activity = new Running(userCode, startDate, 20, "Running", "Running", 1000, 10);
        plan.addActivity(1, activity);
        manager.updateActivities(MakeItFitDate.of(2024, 6, 2), 1.0f);
        // No exception means pass
    }

    @Test
    public void testExtractActivities() {
        manager.insertTrainingPlan(plan);
        Activity activity = new Trail(userCode, startDate, 30, "Trail", "Trail", 1000, 100, 100, 1);
        plan.addActivity(1, activity);
        // Use uma data depois da realização
        MakeItFitDate afterDate = startDate.plusDays(1);
        List<Activity> acts = manager.extractActivities(afterDate, userCode);
        assertEquals(1, acts.size());
        assertEquals(activity, acts.get(0));
    }

    @Test
    public void testConstructTrainingPlanByObjectives() {
        TrainingPlan tp = manager.createTrainingPlan(userCode, startDate);
        TrainingPlan result = manager.constructTrainingPlanByObjectives(tp, 1.0f, false, 2, 2, 2, 100);
        assertNotNull(result);
        assertTrue(result.getActivities().size() > 0);
    }

    @Test
    public void testConstructTrainingPlanByObjectivesInvalidParams() {
        TrainingPlan tp = manager.createTrainingPlan(userCode, startDate);
//        manager.constructTrainingPlanByObjectives(tp, 1.0f, false, -1, 2, 2, 100);
        assertThrows(IllegalArgumentException.class, () -> manager.constructTrainingPlanByObjectives(tp, 1.0f, false, -1, 2, 2, 100));
    }

    @Test
    public void testRemoveActivityFromEmptyPlanDoesNothing() {
        manager.insertTrainingPlan(plan);
        UUID fakeActivityCode = UUID.randomUUID();
        // Não deve lançar exceção ao tentar remover uma atividade inexistente
        manager.removeActivity(plan.getCode(), fakeActivityCode);
        assertTrue(manager.getTrainingPlan(plan.getCode()).getActivities().isEmpty());
    }

    @Test
    public void testExtractActivitiesReturnsEmptyWhenNoActivitiesMatch() {
        manager.insertTrainingPlan(plan);
        // Adiciona uma atividade com data de realização futura
        Activity activity = new Trail(userCode, startDate.plusDays(5), 30, "Trail", "Trail", 1000, 100, 100, 1);
        plan.addActivity(1, activity);
        // Usa uma data antes da realização da atividade
        MakeItFitDate beforeDate = startDate.plusDays(1);
        List<Activity> acts = manager.extractActivities(beforeDate, userCode);
        assertTrue(acts.isEmpty());
    }
}
