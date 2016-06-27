package test.main.week1;

import main.week1.StringCalculator;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * StringCalculator Tester.
 *
 * @author <Authors name>
 * @since <pre>6�� 27, 2016</pre>
 * @version 1.0
 */
public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @Before
    public void before() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: add(String str)
     *
     */
    @Test
    public void testAdd() throws Exception {
        String str = "1,2:4";
        assertEquals(stringCalculator.add(str), 7);
    }

    @Test
    public void testAdd1() throws Exception {
        String str = null;
        assertEquals(stringCalculator.add(str), 0);
    }

    @Test
    public void testAdd2() throws Exception {
        String str = "";
        assertEquals(stringCalculator.add(str), 0);
    }

    @Test
    public void testAdd3() throws Exception {
        String str = "//;\n1;2;3";
        assertEquals(stringCalculator.add(str), 6);
    }

    @Test
    public void testAdd4() throws Exception {
        String str = "//;;\n1;;2;;3";
        assertEquals(stringCalculator.add(str), 6);
    }
}
