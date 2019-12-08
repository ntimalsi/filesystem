package edu.olemiss.csci211.collections;

import java.util.NoSuchElementException;

/** An interface for classes implementing the queue data structure.
 * Queues add elements at the end of the list, and remove elements 
 * from the beginning of the list. This makes them a First In First Out
 * data structure.
 * @author rhodes
 *
 * @param <E> the type of element in the queue
 */
public interface Queue<E> extends List<E> {

	/**
	 *  Add the given element to the rear of the queue.
	 *  @param element the element to be added.
	 */
	public void enqueue(E element);

	/**
	 *  Remove the element at the front of the queue, and return it.
	 *  @return the removed element
	 *  @throws NoSuchElementException if the queue is empty.
	 */
	public E dequeue();
	
	
	@Override
	default public E remove() {
		
		return dequeue();
	}

	/** Return the number of elements in this queue.
	 * 
	 * @return the size of this queue.
	 */
	public int size();
	
	/** 
	 * Make the queue empty.
	 */
	public void clear();
	
	
}
