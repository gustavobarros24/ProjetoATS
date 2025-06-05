from user_generator import gen, formatted_users 

def amateur_test(user_list): return f"""

package TestModels;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import MakeItFit.users.Gender;
import MakeItFit.users.types.Amateur;

@RunWith(Parameterized.class)
public class AmateurTest {{

    private Amateur amateur;
    private final String name;
    private final int age;
    private final Gender gender;
    private final float weight;
    private final int height;
    private final int goalWeight;
    private final int activityLevel;
    private final String address;
    private final String phone;
    private final String email;

    @Parameters
    public static Collection<Object[]> data() {{
        return Arrays.asList(new Object[][] {{
            {formatted_users(user_list)}
        }});
    }}

    public AmateurTest(String name, int age, Gender gender, float weight,
                       int height, int goalWeight, int activityLevel,
                       String address, String phone, String email) {{
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goalWeight = goalWeight;
        this.activityLevel = activityLevel;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }}

    @Before
    public void setUp() {{
        amateur = new Amateur(
                name, age, gender, weight, height, goalWeight,
                activityLevel, address, phone, email
        );
    }}

    @Test
    public void testConstructorAndToString() {{
        String str = amateur.toString();
        assertNotNull(str);
        assertTrue("Should contain name: " + name, str.contains(name));
        assertTrue("Should contain separator", str.contains("===================="));
    }}

    @Test
    public void testCopyConstructor() {{
        Amateur copy = new Amateur(amateur);
        assertNotSame(amateur, copy);
        assertEquals(amateur.toString(), copy.toString());
    }}

    @Test
    public void testClone() {{
        Amateur cloned = amateur.clone();
        assertNotSame(amateur, cloned);
        assertEquals(amateur.toString(), cloned.toString());
        assertTrue(cloned instanceof Amateur);
    }}

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(amateur);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Amateur deserialized = (Amateur) ois.readObject();
        ois.close();

        assertNotNull(deserialized);
        assertEquals(amateur.toString(), deserialized.toString());
    }}
}}

"""

if __name__ == "__main__":

    print(amateur_test(gen(20)))