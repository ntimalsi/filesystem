/**
 * 
 */
package edu.olemiss.csci211.collections;

import java.util.NoSuchElementException;

/** An interface for data structures that support stack operations. Stacks
 * always add and remove elements from the "top" of the list. This makes
 * a stack a Last In First Out data structure.
 * 
 * @author rhodes
 *
 * @param <E> the element type.
 */
public interface Stack<E> extends List<E> {

	/** add the given element onto the top of the stack.
	 * 
	 * @param e The element to be pushed
	 */	
	public void push(E e);

	
	/** remove the element off the top of the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 * @return A reference to the removed element
	 */
	public E pop();

	@Override
	default public  E remove() {
		
		return pop();
	}
	
	
	/** Return a reference to the element on the top of the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 * @return A reference to the topmost element
	 */	
	public E peek();
	
	
	/** Retrieve the number of elements on this stack.
	 * 
	 * @return an int containing the number of elements
	 */
	
	public int size();
	
}
