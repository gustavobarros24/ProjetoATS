from user_generator import gen, formatted_users 

def occasional_test(user_list): return f"""

package TestModels;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import MakeItFit.users.Gender;
import MakeItFit.users.types.Occasional;

@RunWith(Parameterized.class)
public class OccasionalTest {{

    private Occasional occasional;
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

    public OccasionalTest(
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
        occasional = new Occasional(
                name, age, gender, weight, height, bpm, level,
                address, phone, email, frequency
        );
    }}

    @Test
    public void testConstructorAndGetFrequency() {{
        assertEquals(frequency, occasional.getFrequency());
    }}

    @Test
    public void testSetFrequencyValid() {{
        occasional.setFrequency(10);
        assertEquals(10, occasional.getFrequency());
        occasional.setFrequency(0);
        assertEquals(0, occasional.getFrequency());
    }}

    @Test(expected = IllegalArgumentException.class)
    public void testSetFrequencyNegativeThrowsException() {{
        occasional.setFrequency(-1);
    }}

    @Test
    public void testCopyConstructor() {{
        Occasional copy = new Occasional(occasional);
        assertNotSame(occasional, copy);
        assertEquals(occasional.getFrequency(), copy.getFrequency());
        assertEquals(occasional.toString(), copy.toString());
    }}

    @Test
    public void testClone() {{
        Occasional cloned = occasional.clone();
        assertNotSame(occasional, cloned);
        assertEquals(occasional.getFrequency(), cloned.getFrequency());
        assertEquals(occasional.toString(), cloned.toString());
    }}

    @Test
    public void testToStringContainsFrequency() {{
        String str = occasional.toString();
        assertTrue(str.contains("Frequency: " + frequency));
        assertTrue(str.contains(name));
    }}

    @Test
    public void testSetFrequencyDoesNotAffectOtherInstances() {{
        Occasional occasional2 = new Occasional(
                "Bob", 25, Gender.Male, 75.0f, 180, 72, 3,
                "Rua B", "987654321", "bob@email.com", 3
        );
        occasional.setFrequency(15);
        assertEquals(15, occasional.getFrequency());
        assertEquals(3, occasional2.getFrequency());
    }}

    @Test
    public void testSetAndGetFrequencyEdgeCase() {{
        occasional.setFrequency(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, occasional.getFrequency());
    }}
}}

"""

if __name__ == "__main__":

    print(occasional_test(gen(20)))