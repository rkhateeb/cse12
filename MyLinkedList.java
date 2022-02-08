/**
 * MyLinkedList.java
 * Name: Rizq Khateeb
 * Email: rkhateeb@ucsd.edu
 * Sources used: None
 * 
 * File consisting of a LinkedList, Node, and ListIterator class that creates and sets 
 * the pointers of nodes, and contains functions to add, remove, or get
 * nodes within LinkedLists, and iterate through a LinkedList
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;


/** 
 * Class that can create and set the pointers of nodes, and then 
 * create MyLinkedLists and add or set nodes within them, get those 
 * nodes, or remove them from the MyLinkedList
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		//create dummy node
		Node dummyNode = new Node(null);
		head = dummyNode;
		tail = dummyNode;
		head.setPrev(tail);
		head.setNext(tail);
		tail.setPrev(head);
		tail.setNext(head);
		size = 0;
	}

	@Override
	/** 
	 * Returns size of MyLinkedList
	 *   
	 * @return size of MyLinkedList
	 */
	public int size() {
		return this.size; 
	}

	@Override
	/** 
	 * Returns elements within a node
	 * @param index - index of the node whose element you want to extract
	 *   
	 * @return data within node as type E
	 */
	public E get(int index) {
		//use getNth to get the node
		Node nodeToGet = getNth(index);
		return (E) nodeToGet.data;
	}

	@Override
	/** 
	 * Adds a node to a specified index
	 * @param index - index of where you want to add node
	 * @param data - data within the node
	 *   
	 * @return void
	 */
	public void add(int index, E data) {
		//throw exceptions if entries invalid
		if (data==null){
			throw new NullPointerException("data is null.");
		}
		if (index<0 || index>size){
			throw new IndexOutOfBoundsException("index is out of bounds.");
		}
		//initialize the node you are going to add
		Node addedNode = new Node(data);
		//if empty, set head and tail pointers to addedNode
		if (size==0){
			head.setNext(addedNode);
			tail.setPrev(addedNode);

			addedNode.setPrev(tail);
			addedNode.setNext(head);
		}
		//if adding at the beginning, set head and next node 
		//pointers to addedNode
		else if (index==0){
			Node ogNode = getNth(index);

			head.setNext(addedNode);
			ogNode.setPrev(addedNode);

			addedNode.setPrev(head);
			addedNode.setNext(ogNode);
		}
		//if adding at the end, set previous node and tail 
		//pointers to addedNode
		else if (index == size){
			Node prevNode = getNth(index-1);
		
			prevNode.setNext(addedNode);
			tail.setPrev(addedNode);

			addedNode.setPrev(prevNode);
			addedNode.setNext(tail);
		}
		// set previous and next node pointers to addedNode
		else{
			Node ogNode = getNth(index);
			Node prevNode = getNth(index-1);

			prevNode.setNext(addedNode);
			ogNode.setPrev(addedNode);

			addedNode.setPrev(prevNode);
			addedNode.setNext(ogNode);
		}
		//increment size
		size++;
	}

	/** 
	 * Adds a node to the end of MyLinkedList
	 * @param data - data within the node
	 *   
	 * @return true
	 */
	public boolean add(E data) {
		add(size,data);
		return true;
	}

	/** 
	 * Sets a node to have certain data at a specified index
	 * @param index - index of where you want to add node
	 * @param data - data within the node
	 *   
	 * @return previous element at that index
	 */
	public E set(int index, E data) {
		//throw exceptions if entries invalid
		if (data==null){
			throw new NullPointerException("data is null.");
		}
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException("index is out of bounds.");
		}
		Node nodeToSet = getNth(index);
		E prevElement = nodeToSet.getElement();
		nodeToSet.setElement(data);
		return prevElement;
	}

	/** 
	 * Removes a node from a index
	 * @param index - index of where you want to remove node
	 *   
	 * @return data within the removed node
	 */
	public E remove(int index) {
		//throw exceptions if entries invalid
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException("index is out of bounds.");
		}
		Node nodeToRemove = getNth(index);
		//if only one element, set head and tail pointers to each other
		if (size==1){
			head.setNext(tail);
			tail.setPrev(head);
		}
		else {
			//if removing from the beginning, set head and next node 
			//pointers to each other
			if (index==0){
				Node nextNode = getNth(index+1);

				head.setNext(nextNode);
				nextNode.setPrev(head);
			}
			//if removing from the end, set prevNode and tail 
			//pointers to each other
			else if (index == size-1){
				Node prevNode = getNth(index-1);
		
				prevNode.setNext(tail);
				tail.setPrev(prevNode);
			}
			//set prevNode and nextNode pointers to each other
			else{
				Node nextNode = getNth(index+1);
				Node prevNode = getNth(index-1);

				prevNode.setNext(nextNode);
				nextNode.setPrev(prevNode);
			}
		}
		//decrement size
		size--;
		return (E) nodeToRemove.data; 
	}

	/** 
	 * Removes all nodes from MyLinkedList
	 *   
	 * @return void
	 */
	public void clear() {
		int permSize = size;
		//use remove to remove each node
		for(int i=0;i<permSize;i++){
			remove(0);
		}
	}

	/** 
	 * Checks if MyLinkedList is empty
	 *   
	 * @return true if empty
	 */
	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		else{
			return false;
		}
	}

	/** 
	 * Gets node at a particular index
	 * @param index - index of the node you want to retrieve
	 *   
	 * @return node you want to get
	 */
	protected Node getNth(int index) {
		//throw exceptions if entries invalid
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException("index is out of bounds.");
		}
		//set the node you want to get as head initially
		Node nodeToGet = head;
		//initialize counter
		int counter = 0;
		while(nodeToGet.getNext() != null){
			//if index matches counter, return that node
			if(index==counter){
				nodeToGet = nodeToGet.getNext();
				return nodeToGet;
			}
			//move to next node, and increase counter
			nodeToGet = nodeToGet.getNext();
			counter++;
		}
		return nodeToGet;
	}

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class MyListIterator implements ListIterator<E> {
        // class variables here
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

		/** 
		 * Constructor for MyListIterator class
		 */
		public MyListIterator(){
			//initialize all instance variables
			left = head;
			right = head.next;
			idx = 0;
			forward = true;
			canRemoveOrSet = false;
		}

		/** 
	 	* Checks if there is a right node in the iterator
	 	*   
	 	* @return true if there is a right node in the iterator
	 	*/
        public boolean hasNext() {
			//assume node will never have null data
			//if moving in forward direction and next node is not dummy node
			//return true
			if(right.getElement() != null){
				return true;
			}
			return false;
        }

		/** 
	 	* Returns next element in iterator
	 	*   
	 	* @return next element in the iterator
	 	*/
		public E next() {
            if (hasNext() == false){
				throw new NoSuchElementException("No element after this");
			}
			//set instance variables
			idx++;
			forward = true;
			canRemoveOrSet = true;
			//set new left and right nodes
			Node curRight = right;
			left = curRight;
			right = curRight.getNext();
			
			return curRight.getElement();
        }

		/** 
	 	* Checks if there is a left node in the iterator
	 	*   
	 	* @return true if there is a left node in the iterator
	 	*/
		public boolean hasPrevious() {
            //assume node will never have null data
			//if moving in backward direction and prev node is not dummy node
			//return true
			if(left.getElement() != null){
				return true;
			}
			return false;
        }

		/** 
	 	* Returns previous element in iterator
	 	*   
	 	* @return previous element in iterator
	 	*/
		public E previous() {
            if (hasPrevious() == false){
				throw new NoSuchElementException("No element before this");
			}
			//set instance variables
			idx--;
			forward = false;
			canRemoveOrSet = true;
			//set new left and right nodes
			Node curLeft = left;
			left = curLeft.getPrev();
			right = curLeft;
			
			return curLeft.getElement();
        }

		/** 
	 	* Returns next index
	 	*   
	 	* @return int for next index
	 	*/
		public int nextIndex(){
			if (hasNext() == false){
				return size;
			}
			return idx;
		}

		/** 
	 	* Returns previous index
		*   
	 	* @return int for previous index
	 	*/
		public int previousIndex(){
			return idx-1;
		}

		/** 
	 	* Add element to the list to replace left node
	 	* @param element - element to add
	 	*   
	 	* @return void
	 	*/
		public void add(E element){
			if (element == null){
				throw new NullPointerException("Input is null");
			}
			//set new left and right nodes
			Node addedNode = new Node(element);
			addedNode.setPrev(left);
			addedNode.setNext(right);

			left.setNext(addedNode);
			right.setPrev(addedNode);

			left = addedNode; 
			
			//set instance variables
			canRemoveOrSet = false;
			idx++;
		}

		/** 
		* Sets element to replace a node returned by next/previouc call
	 	* @param element - element to set
	 	*   
		* @return void
	 	*/
		public void set(E element){
			if (element == null){
				throw new NullPointerException("Input is null");
			}
			if (canRemoveOrSet == false){
				throw new IllegalStateException("Illegal State");
			}
			//set new left and right nodes for forward direction
			if(forward == true){
				Node nextNode = left;
				nextNode.setElement(element);
			}
			//set new left and right nodes for backward direction
			else if(forward == false){
				Node prevNode = right;
				prevNode.setElement(element);
			}
		}

		/** 
	 	* Removes an node from the linkedlist
	 	*
	 	* @return void
	 	*/
		public void remove(){
			if (canRemoveOrSet == false){
				throw new IllegalStateException("Illegal State");
			}
			//for forward direction
			if(forward == true){
				Node nodeToRemove = left;
				Node newLeft = nodeToRemove.getPrev();
				Node newRight = nodeToRemove.getNext();

				newLeft.setNext(newRight);
				newRight.setPrev(newLeft);

				//set new left and right nodes, and decrement index
				left = newLeft;
				right = newRight;
				idx--;
			}
			//for backward direction
			else if(forward == false){
				Node nodeToRemove = right;
				Node newLeft = nodeToRemove.getPrev();
				Node newRight = nodeToRemove.getNext();

				newLeft.setNext(newRight);
				newRight.setPrev(newLeft);

				//set new left and right nodes
				left = newLeft;
				right = newRight;
			}
			//set canRemoveOrSet to false now
			canRemoveOrSet = false;
		}
	}

	/** 
	* Creates and returns a new MyListIterator
	*
	* @return new MyListIterator
	*/
	public ListIterator<E> listIterator(){
		MyListIterator listIterator = new MyListIterator();
		return listIterator;
	}

	/** 
	* Creates and returns a new MyListIterator
	*
	* @return new MyListIterator
	*/
	public Iterator<E> iterator(){
		MyListIterator iterator = new MyListIterator();
		return iterator;
	}
}