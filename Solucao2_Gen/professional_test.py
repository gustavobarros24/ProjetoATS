from user_generator import gen, formatted_users 

def professional_test(user_list): return f"""
package TestModels;


import static org.junit.Assert.*;

import MakeItFit.users.types.Professional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import MakeItFit.users.Gender;

@RunWith(Parameterized.class)
public class ProfessionalTest {{

    private Professional professional;
    private final String name;
    private final int age;
    private final Gender gender;
    private final float weight;
    private final int height;
    private final int bpm;
    private final int level;
    private final String address;
    private final String phone;
    private final String email;
    private final int frequency;


    @Parameters
    public static Collection<Object[]> data() {{
        return Arrays.asList(new Object[][] {{
            {formatted_users(user_list, True)}        
        }});
    }}

    public ProfessionalTest(
            String name, int age, Gender gender, float weight, int height, int bpm, int level,
            String address, String phone, String email, int frequency
    ) {{
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.bpm = bpm;
        this.level = level;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.frequency = frequency;
    }}

    @Before
    public void setUp() {{
        professional = new Professional(
                name, age, gender, weight, height, bpm, level,
                address, phone, email, frequency
        );
    }}

    @Test
    public void testConstructorAndGetters() {{
        assertEquals(name, professional.getName());
        assertEquals(age, professional.getAge());
        assertEquals(gender, professional.getGender());
        assertEquals(weight, professional.getWeight(), 0.001);
        assertEquals(height, professional.getHeight());
        assertEquals(bpm, professional.getBpm());
        assertEquals(level, professional.getLevel());
        assertEquals(address, professional.getAddress());
        assertEquals(phone, professional.getPhone());
        assertEquals(email, professional.getEmail());
        assertEquals("No specialization", professional.getSpecialization());
        assertEquals(frequency, professional.getFrequency());
    }}

    @Test
    public void testSetAndGetSpecialization() {{
        professional.setSpecialization("Runner");
        assertEquals("Runner", professional.getSpecialization());
    }}

    @Test
    public void testSetFrequency() {{
        professional.setFrequency(10);
        assertEquals(10, professional.getFrequency());
    }}

    @Test
    public void testCopyConstructor() {{
        Professional copy = new Professional(professional);
        assertEquals(professional.getName(), copy.getName());
        assertEquals(professional.getSpecialization(), copy.getSpecialization());
        assertEquals(professional.getFrequency(), copy.getFrequency());
    }}

    @Test
    public void testClone() {{
        Professional clone = professional.clone();
        assertNotSame(professional, clone);
        assertEquals(professional.getName(), clone.getName());
        assertEquals(professional.getSpecialization(), clone.getSpecialization());
        assertEquals(professional.getFrequency(), clone.getFrequency());
    }}

    @Test
    public void testToString() {{
        String str = professional.toString();
        assertTrue(str.contains("Specialization: No specialization"));
        assertTrue(str.contains("Frequency: " + frequency));
    }}

    @Test
    public void testUpdateSpecializationWithNoActivities() {{
        professional.setSpecialization("OldSpecialization");
        professional.updateSpecialization();
        // Should remain unchanged because no activities
        assertEquals("OldSpecialization", professional.getSpecialization());
    }}
    @Test
    public void testUpdateSpecializationWithActivities() {{
        // Adiciona atividades reais usando o método público
        professional.addActivity(new MakeItFit.activities.implementation.PushUp());
        professional.addActivity(new MakeItFit.activities.implementation.PushUp());
        professional.addActivity(new MakeItFit.activities.implementation.Running());

        professional.setSpecialization("OldSpecialization");
        professional.updateSpecialization();

        // Espera-se que a especialização seja "PushUp" pois aparece mais vezes
        assertEquals("PushUp", professional.getSpecialization());
    }}
}}

"""

if __name__ == "__main__":

    print(professional_test(gen(20)))