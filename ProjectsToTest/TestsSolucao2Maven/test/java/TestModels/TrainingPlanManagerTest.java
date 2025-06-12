package TestModels;

import MakeItFit.activities.Activity;
import MakeItFit.activities.ActivityManager;
import MakeItFit.activities.implementation.*;
import MakeItFit.exceptions.EntityDoesNotExistException;
import MakeItFit.trainingPlan.TrainingPlan;
import MakeItFit.trainingPlan.TrainingPlanManager;
import MakeItFit.utils.MakeItFitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;



class TrainingPlanManagerTest {

    private TrainingPlanManager trainingPlanManager;
    private UUID userCode;
    private MakeItFitDate startDate;

    @BeforeEach
    void setUp() {
        trainingPlanManager = new TrainingPlanManager();
        userCode = UUID.randomUUID();
        startDate = MakeItFitDate.of(2024, 5, 15);
    }

    @Test
    @DisplayName("Test constructor creates empty training plans map")
    void testConstructor() {
        TrainingPlanManager manager = new TrainingPlanManager();
        assertEquals(0, manager.getAllTrainingPlans().size());
    }

    @Test
    @DisplayName("Test createTrainingPlan with valid parameters")
    void testCreateTrainingPlanValid() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        assertNotNull(trainingPlan);
        assertEquals(userCode, trainingPlan.getUserCode());
        assertEquals(startDate, trainingPlan.getStartDate());
    }

    @Test
    @DisplayName("Test createTrainingPlan with null userCode throws exception")
    void testCreateTrainingPlanNullUserCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.createTrainingPlan(null, startDate));
        assertEquals("Invalid input: userCode, startDate.", exception.getMessage());
    }

    @Test
    @DisplayName("Test createTrainingPlan with null startDate throws exception")
    void testCreateTrainingPlanNullStartDate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.createTrainingPlan(userCode, null));
        assertEquals("Invalid input: userCode, startDate.", exception.getMessage());
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with valid parameters")
    void testConstructTrainingPlanByObjectivesValid() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        TrainingPlan result = trainingPlanManager.constructTrainingPlanByObjectives(
            trainingPlan, 1.5f, false, 2, 3, 5, 1000);
        
        assertNotNull(result);
        assertEquals(trainingPlan, result);
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with hard activities")
    void testConstructTrainingPlanByObjectivesWithHardActivities() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        TrainingPlan result = trainingPlanManager.constructTrainingPlanByObjectives(
            trainingPlan, 1.5f, true, 2, 3, 5, 500);
        
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with invalid maxActivitiesPerDay")
    void testConstructTrainingPlanByObjectivesInvalidMaxActivitiesPerDay() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, -1, 3, 5, 1000));
        assertEquals("Invalid input.", exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, 4, 3, 5, 1000));
        assertEquals("Invalid input.", exception.getMessage());
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with invalid maxDifferentActivities")
    void testConstructTrainingPlanByObjectivesInvalidMaxDifferentActivities() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, 2, -1, 5, 1000));
        assertEquals("Invalid input.", exception.getMessage());
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with invalid weeklyRecurrence")
    void testConstructTrainingPlanByObjectivesInvalidWeeklyRecurrence() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, 2, 3, -1, 1000));
        assertEquals("Invalid input.", exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, 2, 3, 8, 1000));
        assertEquals("Invalid input.", exception.getMessage());
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with invalid minimumCaloricWaste")
    void testConstructTrainingPlanByObjectivesInvalidMinimumCaloricWaste() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.constructTrainingPlanByObjectives(
                trainingPlan, 1.5f, false, 2, 3, 5, -1));
        assertEquals("Invalid input.", exception.getMessage());
    }

    @Test
    @DisplayName("Test insertTrainingPlan with valid training plan")
    void testInsertTrainingPlanValid() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        assertDoesNotThrow(() -> trainingPlanManager.insertTrainingPlan(trainingPlan));
        assertEquals(1, trainingPlanManager.getAllTrainingPlans().size());
    }

    @Test
    @DisplayName("Test insertTrainingPlan with null training plan throws exception")
    void testInsertTrainingPlanNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.insertTrainingPlan(null));
        assertEquals("Invalid input: trainingPlan cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Test insertTrainingPlan with duplicate code throws exception")
    void testInsertTrainingPlanDuplicate() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.insertTrainingPlan(trainingPlan));
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    @DisplayName("Test removeTrainingPlan")
    void testRemoveTrainingPlan() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        trainingPlanManager.removeTrainingPlan(trainingPlan.getCode());
        assertEquals(0, trainingPlanManager.getAllTrainingPlans().size());
    }

    @Test
    @DisplayName("Test getTrainingPlan with existing code")
    void testGetTrainingPlanExists() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        TrainingPlan result = trainingPlanManager.getTrainingPlan(trainingPlan.getCode());
        assertEquals(trainingPlan, result);
    }

    @Test
    @DisplayName("Test getTrainingPlan with non-existing code throws exception")
    void testGetTrainingPlanNotExists() {
        UUID nonExistingCode = UUID.randomUUID();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> trainingPlanManager.getTrainingPlan(nonExistingCode));
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    @DisplayName("Test updateTrainingPlan with existing training plan")
    void testUpdateTrainingPlanExists() throws EntityDoesNotExistException {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        assertDoesNotThrow(() -> trainingPlanManager.updateTrainingPlan(trainingPlan));
    }

    @Test
    @DisplayName("Test updateTrainingPlan with non-existing training plan throws exception")
    void testUpdateTrainingPlanNotExists() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        EntityDoesNotExistException exception = assertThrows(EntityDoesNotExistException.class, 
            () -> trainingPlanManager.updateTrainingPlan(trainingPlan));
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    @DisplayName("Test getAllTrainingPlans")
    void testGetAllTrainingPlans() {
        TrainingPlan trainingPlan1 = trainingPlanManager.createTrainingPlan(userCode, startDate);
        TrainingPlan trainingPlan2 = trainingPlanManager.createTrainingPlan(UUID.randomUUID(), startDate);
        
        trainingPlanManager.insertTrainingPlan(trainingPlan1);
        trainingPlanManager.insertTrainingPlan(trainingPlan2);
        
        List<TrainingPlan> allPlans = trainingPlanManager.getAllTrainingPlans();
        assertEquals(2, allPlans.size());
    }

    @Test
    @DisplayName("Test addActivity")
    void testAddActivity() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        Activity activity = new PushUp(userCode, startDate, 30, "Test", "PushUp", 10, 3);
        trainingPlanManager.addActivity(trainingPlan.getCode(), 5, activity);
        
        // Verify activity was added (implementation depends on TrainingPlan's addActivity method)
    }

    @Test
    @DisplayName("Test removeActivity")
    void testRemoveActivity() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        Activity activity = new PushUp(userCode, startDate, 30, "Test", "PushUp", 10, 3);
        trainingPlanManager.addActivity(trainingPlan.getCode(), 5, activity);
        trainingPlanManager.removeActivity(trainingPlan.getCode(), activity.getCode());
        
        // Verify activity was removed (implementation depends on TrainingPlan's removeActivity method)
    }

    @Test
    @DisplayName("Test getTrainingPlansFromUser")
    void testGetTrainingPlansFromUser() {
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        
        TrainingPlan plan1 = trainingPlanManager.createTrainingPlan(user1, startDate);
        TrainingPlan plan2 = trainingPlanManager.createTrainingPlan(user1, startDate);
        TrainingPlan plan3 = trainingPlanManager.createTrainingPlan(user2, startDate);
        
        trainingPlanManager.insertTrainingPlan(plan1);
        trainingPlanManager.insertTrainingPlan(plan2);
        trainingPlanManager.insertTrainingPlan(plan3);
        
        List<TrainingPlan> user1Plans = trainingPlanManager.getTrainingPlansFromUser(user1);
        assertEquals(2, user1Plans.size());
        
        List<TrainingPlan> user2Plans = trainingPlanManager.getTrainingPlansFromUser(user2);
        assertEquals(1, user2Plans.size());
    }

    @Test
    @DisplayName("Test updateActivities")
    void testUpdateActivities() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        MakeItFitDate currentDate = MakeItFitDate.of(2024, 5, 20);
        assertDoesNotThrow(() -> trainingPlanManager.updateActivities(currentDate, 1.5f));
    }

    @Test
    @DisplayName("Test extractActivities")
    void testExtractActivities() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        trainingPlanManager.insertTrainingPlan(trainingPlan);
        
        MakeItFitDate currentDate = MakeItFitDate.of(2024, 5, 20);
        List<Activity> activities = trainingPlanManager.extractActivities(currentDate, userCode);
        
        assertNotNull(activities);
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives covers all activity types")
    void testConstructTrainingPlanByObjectivesAllActivityTypes() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        // Test with parameters that allow multiple different activities
        TrainingPlan result = trainingPlanManager.constructTrainingPlanByObjectives(
            trainingPlan, 1.5f, false, 3, 4, 7, 2000);
        
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test constructTrainingPlanByObjectives with zero minimum caloric waste")
    void testConstructTrainingPlanByObjectivesZeroMinimum() {
        TrainingPlan trainingPlan = trainingPlanManager.createTrainingPlan(userCode, startDate);
        
        TrainingPlan result = trainingPlanManager.constructTrainingPlanByObjectives(
            trainingPlan, 1.5f, false, 1, 1, 1, 0);
        
        assertNotNull(result);
    }
}