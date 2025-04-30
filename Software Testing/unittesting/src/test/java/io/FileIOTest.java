package io;

import math.ArrayOperations;
import math.MyMath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIOTest {
    private FileIO fileIO;

    @Before
    public void setUp() throws Exception {
        fileIO = new FileIO();
    }



    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void readFile() {
        String filePath = "D:\\Salsa\\6th-Sem\\Software Testing\\unittesting\\src\\test\\resources\\grades_valid.txt";
        int[] actual = fileIO.readFile(filePath);
        int[] expected = {3,9,0,2,10,9,3,8,0,3};
        
        assertArrayEquals(expected, actual);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_file() {
        String filePath = "D:\\Salsa\\6th-Sem\\Software Testing\\unittesting\\src\\test\\resources\\hello.txt";
        (new FileIO()).readFile(filePath);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_empty_file() {
        String filePath = "D:\\Salsa\\6th-Sem\\Software Testing\\unittesting\\src\\test\\resources\\empty_file.txt";
        (new FileIO()).readFile(filePath);
    }

    @Test(expected = NumberFormatException.class)
    public void test_invalid_entries() {
        String filePath = "D:\\Salsa\\6th-Sem\\Software Testing\\unittesting\\src\\test\\resources\\grades_invalid.txt";
        (new FileIO()).readFile(filePath);
    }
}