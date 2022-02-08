/**
 * TODO: MyArrayList.java
 * Name: Rizq Khateeb
 * ID: A15848068
 * Email: rkhateeb@ucsd.edu
 * Sources used: 
 * 
 * Class containing all the methods for MyArrayList. Includes methods to set, 
 * append, insert, prepend, and remove elements from MyArrayList
 */

 // Hint: the 'capacity' (length of data array) is not the same as the 'size'
 // Size is the number of elements in the ArrayList, i.e., the number of valid elements in the array
 
 /**
 * This file contains the MyArrayList class, which can take in element of an Object data
 * type, and expand the capacity and insert or remove elements
 */
 public class MyArrayList<E>{
    Object[] data;
    int size;

    private static final int FIVE = 5;
    private static final String ILL_CAP = "Internal capacity should be greater than 0";
    private static final String REQ_CAP = "Required capacity should be greater than initial capacity";
    private static final String IND_BND = "This index is out of the bounds of the array";

    /** 
     * no-arg constructor for MyArrayList
     */    
    public MyArrayList(){
        data = new Object[FIVE];
    }

    /** 
     * capacity arg constructor for MyArrayList
     *   
     * @param initialCapacity: integer specifying initial capacity of MyArrayList
     */
    public MyArrayList(int initialCapacity){
        //throw IllegalArgumentException for invalid argument
        if (initialCapacity < 0){
            throw new IllegalArgumentException(ILL_CAP);
        }
        //set data to be new object with inputted length
        else {
            data = new Object[initialCapacity];
        }
    }

    /** 
     * array arg constructor for MyArrayList
     *   
     * @param arr: array of objects
     */
    public MyArrayList(E[] arr){
        //initialize with no-arg constructor
        this();
        //if input is not null
        if (arr != null){
            //set data length to be length of inputted array
            data = new Object[arr.length];
            //for each element in inputted array
            for(int i=0; i<arr.length; i++){
                //set element of data to include elements of array, and increase array size
                data[i] = arr[i];
                size++;
            }
        }
    }

    /** 
     * expand capacity of MyArrayList
     *   
     * @param requiredCapacity: minimum integer value for MyArrayList capacity
     * @return void
     */
    public void expandCapacity(int requiredCapacity){
        int capacity = data.length;
        //throw IllegalArgumentException for invalid argument
        if (requiredCapacity < capacity){
            throw new IllegalArgumentException(REQ_CAP);
        }
        //set capacity to 5 if input was 0
        if (capacity == 0){
            capacity = FIVE;
        }
        //set capacity to double input if greater than 0
        else if (capacity > 0){
            capacity = 2*capacity;
        }
        //if capacity is still less that required capacity, set to required capacity
        if (requiredCapacity > capacity){
            capacity = requiredCapacity;
        }
        //initialize new array of objects with new capacity, and copy items in
        Object[] newData = new Object[capacity];
        for(int i=0; i<data.length; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    /** 
     * Return capacity of MyArrayList
     *   
     * @return capacity of MyArrayList
     */
    public int getCapacity(){
        return data.length;
    }

    /** 
     * Inserts element at particular index of MyArrayList
     *   
     * @param index: integer index you want to insert item at
     * @param element: object you want to insert at specified index
     * @return void
     */
    public void insert(int index, E element){
        //throw IndexOutOfBoundsException for illegal arguments
        if (index>size || index<0){
            throw new IndexOutOfBoundsException(IND_BND);
        }
        //if array is already at max size, use expandCapacity
        if (data.length == size){
            expandCapacity(data.length+1);
        }
        Object[] newData = new Object[data.length];
        //for each element in array
        for(int i=0; i<data.length; i++){
            //if i less than index, set object of new array to object
            //of original at the same index
            if (i<index){
                newData[i] = data[i];
            }
            //if i equals index, set object of new array to inputted element
            else if (i==index){
                newData[i] = element;
            }
            //if i greater than index, set object of new array to object
            //of original at index-1
            else if (i>index){
                newData[i] = data[i-1];
            }
        }
        //increase size counter, and set data to new array
        size++;
        data = newData;
    }

    /** 
     * append item to end of MyArrayList
     *   
     * @param element: object to append
     * @return void
     */
    public void append (E element){
        insert(size, element);
    }

    /** 
     * prepend item to beginning of MyArrayList
     *   
     * @param element: object to prepend
     * @return void
     */
    public void prepend (E element){
        insert(0, element);
    }

    /** 
     * get object at certain index of MyArrayList
     *   
     * @param index: index you want to obtain object from
     * @return object in index of data type E
     */
    public E get (int index){
        //throw IndexOutOfBoundsException for illegal arguments
        if (index>=size || index<0){
            throw new IndexOutOfBoundsException(IND_BND);
        }
        return (E) data[index];
    }

    /** 
     * set object at certain index of MyArrayList
     *   
     * @param index: index you want to obtain object from
     * @param element: object of data type E you want to set to that index
     * @return overridenElement: original item in that index
     */
    public E set (int index, E element){
        //throw IndexOutOfBoundsException for illegal arguments
        if (index>=size || index<0){
            throw new IndexOutOfBoundsException(IND_BND);
        }
        E overridenElement = (E) data[index];
        data[index] = element;
        return overridenElement;
    }

    /** 
     * remove item at certain index of MyArrayList
     *   
     * @param index: index you want to remove object from
     * @return elementToRemove: object that was removed
     */
    public E remove (int index){
        //throw IndexOutOfBoundsException for illegal arguments
        if (index>=size || index<0){
            throw new IndexOutOfBoundsException(IND_BND);
        }
        Object[] newData = new Object[data.length];
        E elementToRemove = null;
        //for each element in array
        for(int i=0; i<data.length; i++){
            //if i less than index, set element of new array
            //to element of old array at specified index
            if (i<index){
                newData[i] = data[i];
            }
            //if i equals index, set elementToRemove as 
            //element of array at specified index
            else if (i==index){
                elementToRemove = (E) data[i];
            }
            //if i greater than index, set item of new array at previous
            //index to be item of original array at the next index
            else if (i>index){
                newData[i-1] = data[i];
            }
        }
        //set array to be equal to new array, decrement size
        data = newData;
        size--;
        //return object that was removed
        return elementToRemove;
    }

    /** 
     * Return size of MyArrayList
     *   
     * @return size: integer for size of MyArrayList
     */
    public int size(){
        return size;
    }
 }
