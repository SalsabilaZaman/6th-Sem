import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void div() {
        float expected=3.0F;
        float actual=(new MyClass()).div(10,5);
        assertEquals(expected,actual,1e-5);
    }

    @Test
    public void test_div_by_zero_exception(){
        assertThrows(ArithmeticException.class,()->{(new MyClass()).div(10,0);});
    }

    @Test(expected = ArithmeticException.class)
    public void test_div_divided_by_zero(){
        (new MyClass()).div(10,1);
    }

}