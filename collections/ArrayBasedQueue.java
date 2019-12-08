package edu.olemiss.csci211.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** An implementation of the {@link Queue} interface that is backed
 * by an array.
 * 
 * @author rhodes
 *
 * @param <E> The element type
 */
public class ArrayBasedQueue<E> implements Queue<E>{

	E[] arr;
	
	int front, rear;
	int size;
	
	
	/** Construct a queue with the given capacity.
	 * 
	 * @param capacity the number of elements the queue can hold
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int capacity){
		
		// It's not possible to construct arrays of generic types directly, 
		// but we can construct an array of Object references and then cast
		// to E[] (an array of references to instances of E). Each element
		// of the array is initially a reference with value null, not an E,
		// but the enqueue() method will store references to actual instances of E.
		this.arr = (E []) new Object[capacity];
		this.front = 0;
		this.rear  = 0;
		
	}


	/** {@inheritDoc}
	 *  @param element {@inheritDoc}
	 *  @throws IllegalStateException if the array doesn't have space for the additional element.
	 */
	@Override
	public void enqueue(E element) {
		
		if(this.size == arr.length) {
			
			throw new IllegalStateException("Insufficient capacity.");
		}
		
		int rearIndex = rear % arr.length;
		arr[rearIndex] = element;
		
		rear++;
		size++;
		
	}

	@Override
	public E dequeue() {
		
		if (isEmpty())
            throw new NoSuchElementException("Queue is empty.");
    
		int frontIndex = front % arr.length;
        E result = arr[frontIndex];
        arr[frontIndex] = null;        
        front++;

    
        size--;
    
        return result;
	}

	@Override
	public int size() {
		
		return this.size;
	}

	@Override
	public void clear() {

		for(int i=0; i<arr.length; i++) {
			
			arr[i]=null;
		}
		
		this.front = 0;
		this.rear  = 0;
		this.size  = 0;
		
	}

	@Override
	public void add(E element) {
		
		this.enqueue(element);
	}

	@Override
	public List<E> shallowCopy() {
		
		ArrayBasedQueue<E> r = new ArrayBasedQueue<E>(this.arr.length);
		
		for(int i=0; i<this.arr.length; i++) {
			
			r.arr[i] = this.arr[i];
		}
		
		r.front = this.front;
		r.rear = this.rear;
		r.size = this.size;
		
		return r;
	}

	class ArrayQueueIterator implements Iterator<E>{

		int next;
		int rear;
		
		
		private ArrayQueueIterator(ArrayBasedQueue<E> q){
			
			this.next = q.front;
			this.rear = q.rear;
		}
		
		@Override
		public boolean hasNext() {
			
			return (next < rear);
		}

		/** {@inheritDoc}
		 * @throws NoSuchElementException if there are no more elements
		 */
		@Override
		public E next() {
			
			if (this.hasNext()) {
				
				int nextIndex = next % arr.length;
				E rval = arr[nextIndex];
				
				next++;
				
				return rval;
			} else 
				throw new NoSuchElementException();
		}
	}
	
	
	@Override
	public Iterator<E> iterator() {
		
		return new ArrayQueueIterator(this);
	}



}
