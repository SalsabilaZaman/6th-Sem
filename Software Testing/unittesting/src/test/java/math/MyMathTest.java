package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void factorial() {
        int expected = 6;
        int actual=(new MyMath()).factorial(3);
        assertEquals(expected,actual);
    }
    @Test
    public void factorial_of_zero() {
        int expected = 1;
        int actual=(new MyMath()).factorial(0);
        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorial_test_less_than_zero() {
        (new MyMath()).factorial(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void factorial_test_getter_than_twelve() {
        (new MyMath()).factorial(15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void prime_test_less_than_two() {
        (new MyMath()).isPrime(1);
    }

    @Test
    public void test_isPrime() {
        boolean expected=true;
        boolean actual=(new MyMath()).isPrime(7);
        assertEquals(expected,actual);
    }
    @Test
    public void test_is_notPrime() {
        boolean expected=false;
        boolean actual=(new MyMath()).isPrime(8);
        assertEquals(expected,actual);
    }
}