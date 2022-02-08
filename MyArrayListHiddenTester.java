/**
 * TODO: MyArrayListHiddenTester
 * Name: Rizq Khateeb
 * ID: A15848068
 * Email: rkhateeb@ucsd.edu
 * Sources used: 
 * 
 * JUnit Test file containing all the hidden tests for MyArrayList.java
 * Tests all the methods for specific cases and asserts that conditions are
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;

import org.junit.*;

/**
 * This file contains all the hidden tests implemented by me to be graded on
 * Use this as a guide to write tests to verify your MyArrayList implementation 
 */
public class MyArrayListHiddenTester {

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3 };


    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test */
    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * Aims to test the capacity argument constructor when the input
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        boolean thrown = false;

        try {
            MyArrayList testMyArrayList = new MyArrayList(-1);
          } catch (IllegalArgumentException e) {
            thrown = true;
          }

        assertTrue(thrown);
    }

    /**
     * Aims to test the Array argument constructor when the input
     * is null
     */
    @Test
    public void testConstructorNullArg(){
        //initilaize MyArrayList with capacity null
        arr = null;
        MyArrayList testMyArrayList = new MyArrayList(arr);

        //check that capacity has been set to default no-arg of 5
        assertEquals("Check that no-arg constrcutor has been called", 5, testMyArrayList.data.length);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        
        for(int i=0;i<5;i++){
            testMyArrayList.insert(i, i+1);
        }

        testMyArrayList.append(10);
        
        assertEquals("Check that first entry in MyArrayList is 1", 1, testMyArrayList.data[0]);
        assertEquals("Check that 6th entry in MyArrayList is 10", 10, testMyArrayList.data[5]);  
        assertEquals("Check that size of MyArrayList is 6", 6, testMyArrayList.size);
        assertEquals("Check that capacity of MyArrayList is 10", 10, testMyArrayList.data.length);
    }

    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        String nullStr = null;
        //initilaize MyArrayList with capacity 2
        MyArrayList testMyArrayList = new MyArrayList(2);
        testMyArrayList.append("Hi");
        testMyArrayList.append("Hello");

        testMyArrayList.prepend(nullStr);

        assertEquals("Check that first entry in MyArrayList is null", null, testMyArrayList.data[0]);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBound(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        boolean thrown = false;

        try {
            testMyArrayList.insert(-1,arr);
          } catch (IndexOutOfBoundsException e) {
            thrown = true;
          }
        assertTrue(thrown);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        
        for(int i=0;i<1000;i++){
            testMyArrayList.insert(i, i+1);
        }
        
        assertEquals("Check that first entry in MyArrayList is 1", 1, testMyArrayList.data[0]);
        assertEquals("Check that 900th entry in MyArrayList is 901", 901, testMyArrayList.data[900]);        
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        boolean thrown = false;

        try {
            testMyArrayList.get(8);
          } catch (IndexOutOfBoundsException e) {
            thrown = true;
          }
        assertTrue(thrown);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        boolean thrown = false;

        try {
            testMyArrayList.set(8, 4);
          } catch (IndexOutOfBoundsException e) {
            thrown = true;
          }

        assertTrue(thrown);
    }


    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        boolean thrown = false;

        try {
            testMyArrayList.remove(8);
          } catch (IndexOutOfBoundsException e) {
            thrown = true;
          }

        assertTrue(thrown);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        //initilaize MyArrayList with capacity 5
        MyArrayList testMyArrayList = new MyArrayList(5);
        boolean thrown = false;

        try {
            testMyArrayList.expandCapacity(3);
          } catch (IllegalArgumentException e) {
            thrown = true;
          }

        assertTrue(thrown);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than double(2x) the current capacity
     */
    @Test
    public void testExpandCapacityExplode(){
        //initilaize MyArrayList with capacity 6
        MyArrayList testMyArrayList = new MyArrayList(6);

        //expand capacity to a minimum of 7
        testMyArrayList.expandCapacity(18);

        //check that capacity has been more than doubled from 6 to 18
        assertEquals("Check that capacity is doubled", 18, testMyArrayList.data.length);
    }

}
