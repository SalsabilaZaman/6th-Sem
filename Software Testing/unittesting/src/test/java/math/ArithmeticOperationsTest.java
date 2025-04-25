package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divide() {
        double expected=5.0;
        double actual=(new ArithmeticOperations()).divide(10,2);

        assertEquals(expected,actual,1e-3);
    }
    @Test
    public void divide_with_numerator_zero() {
        double expected=0;
        double actual=(new ArithmeticOperations()).divide(0,-2);

        assertEquals(expected,actual,1e-3);
    }
    @Test(expected = ArithmeticException.class)
    public void divide_with_denominator_zero(){
        (new ArithmeticOperations()).divide(10,0);
    }
    @Test
    public void multiply() {
        int expected=5;
        int actual=(new ArithmeticOperations()).multiply(10,2);

        assertEquals(expected,actual,1e-3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_multiplied_with_second_negative_numbers(){

        (new ArithmeticOperations()).multiply(10,-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_multiplied_with_first_negative_number(){

        (new ArithmeticOperations()).multiply(-10,2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_multiplied_with_both_negative_number(){

        (new ArithmeticOperations()).multiply(-10,-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_out_of_bound(){
        (new ArithmeticOperations()).multiply(Integer.MAX_VALUE,10);
    }
}