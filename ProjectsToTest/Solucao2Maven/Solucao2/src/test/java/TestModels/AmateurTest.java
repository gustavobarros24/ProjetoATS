package TestModels;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import MakeItFit.users.Gender;
import MakeItFit.users.types.Amateur;

public class AmateurTest {

    @Test
    public void testConstructorAndToString() {
        Amateur amateur = new Amateur(
                "John Doe", 25, Gender.Male, 70.5f, 180, 60, 1,
                "123 Main St", "123456789", "john@example.com"
        );
        String str = amateur.toString();
        assertNotNull(str);
        assertTrue(str.contains("John Doe"));
        assertTrue(str.contains("===================="));
    }

    @Test
    public void testCopyConstructor() {
        Amateur original = new Amateur(
                "Jane Doe", 30, Gender.Female, 60.0f, 165, 65, 2,
                "456 Elm St", "987654321", "jane@example.com"
        );
        Amateur copy = new Amateur(original);
        assertNotSame(original, copy);
        assertEquals(original.toString(), copy.toString());
    }

    @Test
    public void testClone() {
        Amateur amateur = new Amateur(
                "Alex Smith", 22, Gender.Other, 80.0f, 175, 70, 3,
                "789 Oak St", "555555555", "alex@example.com"
        );
        Amateur cloned = amateur.clone();
        assertNotSame(amateur, cloned);
        assertEquals(amateur.toString(), cloned.toString());
        assertTrue(cloned instanceof Amateur);
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        Amateur amateur = new Amateur(
                "Serial Test", 40, Gender.Male, 90.0f, 190, 75, 4,
                "Test St", "000000000", "serial@example.com"
        );
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
    }
}
