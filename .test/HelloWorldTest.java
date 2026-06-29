import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    void testHelloWorldOutput() throws Exception {

        // Check that the class exists
        Class<?> helloClass;
        try {
            helloClass = Class.forName("HelloWorld");
        } catch (ClassNotFoundException e) {
            fail("Could not find a class named 'HelloWorld'.");
            return; // unreachable, but keeps compiler happy
        }

        // Find the main method
        Method mainMethod;
        try {
            mainMethod = helloClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            fail("The HelloWorld class must contain a main method.");
            return;
        }

        // Capture console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            mainMethod.invoke(null, (Object) new String[]{});

        } finally {
            System.setOut(originalOut);
        }

        String expected = "Hello Math 130!" + System.lineSeparator();

        assertEquals(
            expected,
            output.toString(),
            "The program must print exactly: Hello Math 130!"
        );
    }
}