/**
 * An interface that specifies the functionality of an ArrayList ADT
 */
public interface MyList<E> {

    /**
     * Increase the capacity of the underlying array
     */
    void expandCapacity(int requiredCapacity);
    

    /**
     * Get the amount of elements arraylist can hold 
     * @return Number of elements an arraylist can hold - length of the array
     */
    int getCapacity();
    
    /**
     * Add an element at the specified index
     * @param index - position in the array to insert the element
     * @param element - the element to be inserted 
     */
    void insert(int index, E element);
    
    /**
    * Add an element to the end of the list 
    * @param element - the element to be added    
    */
    void append(E element);
    
    /**
    * Add an element to the beginning of the list
    * @param element - the element to be added
    */
    void prepend(E element);
    
    /**
    * Get the element at the given index 
    * @param index - position in the arraylist
    * @return element present in the given index
    */
    E get(int index);
    
    /**
    * Replaces an element at the specified index with a new element and return the original elements
    * @param index - position of the element to be replaced
    * @param element - new element replacing the old element
    * @return original element present in the index before replacement
    */
    E set(int index, E element);
    
    /**
    * Remove the element at the specified index and return the removed element
    * @param index - position of the element to be removed
    * @return element in that index
    */
    E remove(int index);
    
    /**
    * Get the number of elements in the list
    * @return number of elements present in the list
    */
    int size();
    
}