/**
 * MyLinkedListCustomTester.java
 * Name: Rizq Khateeb
 * Email: rkhateeb@ucsd.edu
 * Sources used: MyLinkedListPublicTester for formatting
 * of tests
 * 
 * Custom testers for MyLinkedList.java that test the various methods
 * and assert that everything is running smoothly.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Testers that test the add, set, get, getNth, remove, clear
 * and size methods of MyLinkedList to ensure they run properly
 * 
 * IMPORTANT: Do not change the method headers and points are awarded 
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTesterOld {
	private MyLinkedList<String> emptyStringList;
	private MyLinkedList<Integer> threeIntegerList;
	private MyLinkedList<Integer> oneIntegerList;
	private Integer[] intDataThree = {4,10,13};
	private Integer[] intDataOne = {16};
	
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		emptyStringList = new MyLinkedList<String>();
		threeIntegerList = new MyLinkedList<>();
		oneIntegerList = new MyLinkedList<>();
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
	}

	/**
	 * method that creates an integer MyLinkedList of size 1
	 */
	private void createLinkedListOne() {
		//assign the value within the node
		MyLinkedList<Integer>.Node node0 =  
			this.oneIntegerList.new Node(this.intDataOne[0]);

		//set the pointers for the node
		this.oneIntegerList.head.next = node0;
		node0.prev = this.oneIntegerList.head;
		node0.next = this.oneIntegerList.tail;
		this.oneIntegerList.tail.prev = node0;
		this.oneIntegerList.size = 1;
	}

	/**
	 * tests addding a string to an empty MyLinkedList
	 */
	@Test
	public void testAdd() {
		this.emptyStringList.add("hello");
		
		assertEquals("Head should point to new node", 
			"hello", this.emptyStringList.head.next.data);
		assertEquals("New node should point to tail", 
			"hello", this.emptyStringList.tail.prev.data);
		assertEquals("Size of the LinkedList should be updated to 1", 
			1, this.emptyStringList.size);
		assertSame("Make sure head's next and tail's previous are the same", 
			this.emptyStringList.head.next, this.emptyStringList.tail.prev);
	}

	/**
	 * tests adding a integer to the beginning of MyLinkedList
	 */
	@Test
	public void testAddWithIndexTestOne() {
		this.createLinkedListOne();
		
		this.oneIntegerList.add(0,2);

		assertEquals("Head should point to new node", 
			Integer.valueOf(2), this.oneIntegerList.head.next.data);
		assertEquals("Size of the LinkedList should be updated to 2", 
			2, this.oneIntegerList.size);
		
	}

	/**
	 * tests adding null to a MyLinkedList
	 */	
	@Test
	public void testAddWithIndexTestTwo() {
		//create and fill oneIntegerList
		this.createLinkedListOne();
		
		try {
			this.oneIntegerList.add(1,null);
		} catch (NullPointerException e) {
			// Exception caught, test passed
		}
	}

	/**
	 * tests get on an empty MyLinkedList
	 */
	@Test
	public void testGet() {
		try {
			emptyStringList.get(1);
		} catch (IndexOutOfBoundsException e) {
			// Exception caught, test passed
		}
	}

	/**
	 * tests getNth on an empty MyLinkedList
	 */
	@Test
	public void testGetNth() {
		try {
			emptyStringList.getNth(1);
		} catch (IndexOutOfBoundsException e) {
			// Exception caught, test passed
		}
	}

	/**
	 * tests setting an integer to the end of a MyLinkedList
	 */
	@Test
	public void testSet() {
		//create and fill threeIntegerList
		this.createLinkedListThree();

		//initialize variable with size before set function
		int sizeMLL = threeIntegerList.size();
		threeIntegerList.set(sizeMLL-1,4);
		
		assertEquals("Value at last index should be reset to 4",
			Integer.valueOf(4), this.threeIntegerList.tail.prev.data);
		assertEquals("Size of the LinkedList should NOT be updated", 
			3, this.threeIntegerList.size);

	}

	/**
	 * tests remove from a MyLinkedList with one element
	 */
	@Test
	public void testRemoveTestOne() {
		//create and fill oneIntegerList
		this.createLinkedListOne();

		//remove from oneIntegerList
		oneIntegerList.remove(0);
		
		assertSame("Head pointer should now point to tail", 
		this.oneIntegerList.tail, this.oneIntegerList.head.next);
		assertSame("Tail pointer should now point to head", 
		this.oneIntegerList.head, this.oneIntegerList.tail.prev);
	}
	
	/**
	 * tests remove from an empty MyLinkedList
	 */
	@Test
	public void testRemoveTestTwo() {
		try {
			emptyStringList.remove(1);
		} catch (IndexOutOfBoundsException e) {
			// Exception caught, test passed
		}
	}

	/**
	 * tests clear on an empty list
	 */
	@Test
	public void testClear() {
		try {
			emptyStringList.clear();
		} catch (IndexOutOfBoundsException e) {
			// Exception caught, test passed
		}
	}

	/**
	 * tests size on a non-empty MyLinkedList
	 */
	@Test
	public void testSize() {
		this.createLinkedListThree();
		assertEquals("Size of the LinkedList should be correct (3)", 
			3, this.threeIntegerList.size);
	}
}