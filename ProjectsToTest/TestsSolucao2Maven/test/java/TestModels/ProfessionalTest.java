package TestModels;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotSame;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.Test;

import MakeItFit.users.Gender;
import MakeItFit.users.types.Professional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessionalTest {

    private Professional professional;

    @BeforeEach
    public void setUp() {
        professional = new Professional(
                "John Doe",
                30,
                Gender.Male,
                80.0f,
                180,
                60,
                5,
                "123 Main St",
                "123456789",
                "john@example.com",
                4
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("John Doe", professional.getName());
        assertEquals(30, professional.getAge());
        assertEquals(Gender.Male, professional.getGender());
        assertEquals(80.0f, professional.getWeight(), 0.001);
        assertEquals(180, professional.getHeight());
        assertEquals(60, professional.getBpm());
        assertEquals(5, professional.getLevel());
        assertEquals("123 Main St", professional.getAddress());
        assertEquals("123456789", professional.getPhone());
        assertEquals("john@example.com", professional.getEmail());
        assertEquals("No specialization", professional.getSpecialization());
        assertEquals(4, professional.getFrequency());
    }

    @Test
    public void testSetAndGetSpecialization() {
        professional.setSpecialization("Runner");
        assertEquals("Runner", professional.getSpecialization());
    }

    @Test
    public void testSetFrequency() {
        professional.setFrequency(10);
        assertEquals(10, professional.getFrequency());
    }

    @Test
    public void testCopyConstructor() {
        Professional copy = new Professional(professional);
        assertEquals(professional.getName(), copy.getName());
        assertEquals(professional.getSpecialization(), copy.getSpecialization());
        assertEquals(professional.getFrequency(), copy.getFrequency());
    }

    @Test
    public void testClone() {
        Professional clone = professional.clone();
        assertNotSame(professional, clone);
        assertEquals(professional.getName(), clone.getName());
        assertEquals(professional.getSpecialization(), clone.getSpecialization());
        assertEquals(professional.getFrequency(), clone.getFrequency());
    }

    @Test
    public void testToString() {
        String str = professional.toString();
        assertTrue(str.contains("Specialization: No specialization"));
        assertTrue(str.contains("Frequency: 4"));
    }

    @Test
    public void testUpdateSpecializationWithNoActivities() {
        professional.setSpecialization("OldSpecialization");
        professional.updateSpecialization();
        // Should remain unchanged because no activities
        assertEquals("OldSpecialization", professional.getSpecialization());
    }
    @Test
    public void testUpdateSpecializationWithActivities() {
        // Adiciona atividades reais usando o método público
        professional.addActivity(new MakeItFit.activities.implementation.PushUp());
        professional.addActivity(new MakeItFit.activities.implementation.PushUp());
        professional.addActivity(new MakeItFit.activities.implementation.Running());

        professional.setSpecialization("OldSpecialization");
        professional.updateSpecialization();

        // Espera-se que a especialização seja "PushUp" pois aparece mais vezes
        assertEquals("PushUp", professional.getSpecialization());
    }
}