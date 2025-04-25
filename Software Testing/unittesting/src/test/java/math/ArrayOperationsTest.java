package math;

import io.FileIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ArrayOperationsTest {
    private FileIO fileIO;
    private ArrayOperations arrayOperations;
    private MyMath math;

    @Before
    public void setUp() throws Exception {
        fileIO = new FileIO();
        arrayOperations = new ArrayOperations();
        math = new MyMath();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findPrimesInFile() {
        String filePath = "E:\\6th Sem\\unittesting\\src\\test\\resources\\grades_valid.txt";
        int[] expected = {3, 2, 3, 3};
        int[] actual = arrayOperations.findPrimesInFile(fileIO, filePath, math);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findPrimesInFile2() {
        String filePath = "E:\\6th Sem\\unittesting\\src\\test\\resources\\grades_valid.txt";
        int[] arrayOfNumbers = fileIO.readFile(filePath);
        int[] expected = {3,9,0,2,10,9,3,8,0,3};
        int[] actual = arrayOperations.findPrimesInFile(fileIO, filePath, math);

        assertArrayEquals(expected, actual);

    }

    @Test
    public void findPrimesInFile3() {
        String filePath = "E:\\6th Sem\\unittesting\\src\\test\\resources\\empty_file.txt";
        int[] expected = {3, 2, 3, 3};
        int[] actual = arrayOperations.findPrimesInFile(fileIO, filePath, math);

        assertArrayEquals(expected, actual);
    }
}