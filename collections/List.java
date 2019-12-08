package edu.olemiss.csci211.collections;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/** A very simple List interface that makes few assumptions about implementation. 
 * Implementing classes should allow duplicates, and maintain some order, which could
 * be data dependent (e.g. a sorted list), or merely dependent on insertion order.
 * 
 * @author rhodes
 *
 * @param <E> the type of element contained in the list.
 */
public interface List<E> extends Iterable<E> {
	
	/** Add (insert) an element to the list.
	 * 
	 * @param element the element to be added
	 */
	public void add(E element);
	
	/** Remove an element from the list and return a reference to
	 * the removed element. The element to be removed is chosen by
	 * the implementation.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 * @return the removed element
	 */
	public E remove();
	
	
	/** Provide the number of elements in the list.
	 * 
	 * @return the size of the list.
	 */
	public int size();
	
	
	
	
	/** Make this list empty.
	 * 
	 */
	public void clear();
	
	
	/** Indicate whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise.
	 */
	default boolean isEmpty() {
		
		return size() == 0;
	}
	
	
	/** Copy this List to a new List. The new List is a shallow copy, so it contains
	 *  references to the elements from this List. However, the new list should be
	 *  independent of the old one. For example, adding a new element to one list shouldn't
	 *  cause the element to be added to both.
	 * 
	 * @return a new List
	 */
	 public List<E> shallowCopy();

	
	
	/** Find an element in the list that "matches" the argument. We'll consider two elements A 
	 *  and B to match if A.equals(B) returns true. 
	 * 
	 * @param e the element to match
	 * @return a reference to an element from the list that matches the argument, or null if no match is found.
	 */
	default public E find(E e) {
		
		for(E element: this) {
			
			if(e.equals(element)) {

				return element;
			}
		}

		return null;
	}

	
	
	/** This default implementation returns an {@link ArrayList} from this List. This is
	 * a shallow copy, so the ArrayList contains references to the elements from this List.
	 * 
	 * @return an ArrayList
	 */
	default public ArrayList<E> toArrayList() {
		
		ArrayList<E> arraylist = new ArrayList<E>(this.size());
		
		for(E element: this) {
			
			arraylist.add(element);
		}

		return arraylist;
	}
	
	

	
}
