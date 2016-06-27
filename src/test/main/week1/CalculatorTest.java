package test.main.week1;

import main.week1.Calculator;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Calculator Tester.
 *
 * @author <Authors name>
 * @since <pre>6�� 27, 2016</pre>
 * @version 1.0
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void before() throws Exception {
        calculator = new Calculator();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(calculator.add(1,2),3);
    }

    @Test
    public void testMinus() throws Exception {
        assertEquals(calculator.minus(2,1),1);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(calculator.divide(2,1), 2);
    }


} 
