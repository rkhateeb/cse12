
/**
 * MyLinkedListCustomTester.java
 * Name: Rizq Khateeb
 * ID: A15848068
 * Email: rkhateeb@ucsd.edu
 * Sources used: MyLinkedListPublicTester.java for ideas on how to format
 * my tests
 * 
 * Custom testers for MyLinkedList.java that test the various methods
 * and assert that everything is running smoothly.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * Tester that tests the hasNext, hasPrevious, next, previous, nextIndex,
 * previousIndex, set, remove, and add methods of MyListIterator and ensures
 * that everything runs smoothly.
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {

    private MyLinkedList<Integer> emptyIntegerList;
	private MyLinkedList<Integer> threeIntegerList;
	private Integer[] intDataThree = {4,10,13};

    private MyLinkedList.MyListIterator emptyIntegerListIterator, 
        threeIntegerListIterator;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        emptyIntegerList = new MyLinkedList<Integer>();
        emptyIntegerListIterator = emptyIntegerList.new MyListIterator();

        threeIntegerList = new MyLinkedList<Integer>();
		
    }

    /**
	 * method that creates an integer MyLinkedList of size 3
	 */
	private void createLinkedListThree() {
		//assign the values within the nodes
		MyLinkedList<Integer>.Node node0 =  
			this.threeIntegerList.new Node(this.intDataThree[0]);
		MyLinkedList<Integer>.Node node1 =  
			this.threeIntegerList.new Node(this.intDataThree[1]);
		MyLinkedList<Integer>.Node node2 =  
			this.threeIntegerList.new Node(this.intDataThree[2]);

		//set up all the pointers for each node
		this.threeIntegerList.head.next = node0;
		node0.prev = this.threeIntegerList.head;
		node0.next = node1;
		node1.prev = node0;
		node1.next = node2;
		node2.prev = node1;
		node2.next = this.threeIntegerList.tail;
		this.threeIntegerList.tail.prev = node2;
		this.threeIntegerList.size = 3;

        threeIntegerListIterator = threeIntegerList.new MyListIterator();
	}

    /**
     * test the hasNext method when MyLinkedList is empty
     */
    @Test
    public void testHasNext() {
        assertFalse("hasNext should be false because MyLinkedList is empty",
            emptyIntegerListIterator.hasNext());
    }

    /**
     * test the next method when MyLinkedList is empty
     */
    @Test
    public void testNext() {
        try {
			emptyIntegerListIterator.next();
		} catch (NoSuchElementException e) {
			// Exception caught, test passed
		}
    }

    /**
     * test the hasPrevious method when MyLinkedList is empty
     */
    @Test
    public void testHasPrevious() {
        assertFalse("hasPrevious should be false because MyLinkedList is empty",
            emptyIntegerListIterator.hasPrevious());
    }

    /**
     * test the previous method when MyLinkedList is empty
     */
    @Test
    public void testPrevious() {
        try {
			emptyIntegerListIterator.previous();
		} catch (NoSuchElementException e) {
			// Exception caught, test passed
		}
    }

    /**
     * test the nextIndex method when MyLinkedList is empty
     */
    @Test
    public void testNextIndex() {
        assertEquals("nextIndex should return size 0, since hasNext is false",
        0, emptyIntegerListIterator.nextIndex());
    }

    /**
     * test the previousIndex method when MyLinkedList is empty
     */
    @Test
    public void testPreviousIndex() {
        assertEquals("previousIndex should return -1, since hasPrevious is false",
        -1, emptyIntegerListIterator.previousIndex());
    }

    /**
     * test the set method without calling next or previous
     */
    @Test
    public void testSet() {
        this.createLinkedListThree();
        
        try {
			threeIntegerListIterator.set(15);
		} catch (IllegalStateException e) {
			// Exception caught, test passed
		}
    }

    /**
     * test the remove method without calling next or previous
     */
    @Test
    public void testRemoveTestOne() {
        this.createLinkedListThree();
        
        try {
			threeIntegerListIterator.remove();
		} catch (IllegalStateException e) {
			// Exception caught, test passed
		}
    }

    /**
     * test the remove method on a MyLinkedList with 3 nodes
     */
    @Test
    public void testRemoveTestTwo() {
        this.createLinkedListThree();
        
        threeIntegerListIterator.next();
        
        assertTrue("canRemoveOrSet should be true after next is called",
            threeIntegerListIterator.canRemoveOrSet);
        
        threeIntegerListIterator.remove();
        
        assertEquals("Valid remove when forward", null,
            threeIntegerListIterator.left.getElement());
        assertEquals("Valid remove when forward, shouldn't change", 10,
            threeIntegerListIterator.right.getElement());
        assertEquals("Index should decrement after removal", 0,
            threeIntegerListIterator.idx);
        assertFalse("Prevent another remove or set",
            threeIntegerListIterator.canRemoveOrSet);
    }

    /**
     * test the add method on an empty list
     */
    @Test
    public void testAdd() {
        emptyIntegerListIterator.add(15);
        assertEquals("Item was added to left of iterator", 15,
            emptyIntegerListIterator.left.getElement());
        assertEquals("Item on right of iterator remains same (null)", null,
            emptyIntegerListIterator.right.getElement());
        assertEquals("Index changed after add to 1", 1, emptyIntegerListIterator.idx);
        assertFalse("canRemoveOrSet should be false after add is called",
            emptyIntegerListIterator.canRemoveOrSet);

    }

}