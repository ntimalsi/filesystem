package edu.olemiss.csci211.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A linked implementation of the Queue interface. 
 * 
 * @author rhodes
 *
 * @param <E> the type of elements stored in the queue.
 */
public class LinkedQueue<E> implements Queue<E>{

	protected class Node{
		
		E element;
		Node next;
	}
	
	private Node front, rear;
	private int size = 0;
	
	
	/**
	 *  Construct an empty queue.
	 */ 
	public LinkedQueue(){
		
		front=rear=null;
		size = 0;
	}

	/**
	 * @see #enqueue
	 */
	@Override
	public void add(E element) {
		
		enqueue(element);
	}


	
	
	@Override // see interface for comments.
	public void enqueue(E element) {

		Node n = new Node();
		n.element = element;
		n.next = null;
		
		if(rear == null){
			front = rear = n;
			
		} else {
			
			rear.next = n;
			rear=n;
		}
		size++;
	}
	
	@Override // see interface for comments.
	public void clear() {
		
		this.front = this.rear = null;
		this.size = 0;
	}
	
	
	/**
	 *  @return the removed element
	 *  @throws NoSuchElementException if the queue is empty.
	 */	
	@Override // see interface for comments.
	public E dequeue() {
		
		if( front == null){
			
			throw new NoSuchElementException("dequeue: Queue is empty.");
		}
		
		E r = front.element;
		
		if( rear == front) {
			
			rear = null;
		}
		
		front = front.next;
		
		size--;
		return r;
	}

	@Override // see interface for comments.
	public int size() {
		
		return size;
	}

	/** An Iterator for our LinkedQueue.
	 * 
	 * @author rhodes
	 *
	 */
	 class LinkedQueueIterator implements Iterator<E>{
		
		LinkedQueue<E>.Node current;
		
		public LinkedQueueIterator(LinkedQueue<E> q){
			
			current = q.front;
		}
		
		@Override // see interface for comments.
		public boolean hasNext() {
			
			return (current != null);
		}

		@Override
		public E next() {

			E r = current.element;
			
			current = current.next;
			
			return r;
		}


		
	}


	@Override
	public List<E> shallowCopy() {
		 
		LinkedQueue<E> q = new LinkedQueue<E>();
		
		for(E e:this) {
			
			q.enqueue(e);
		}
			
		return q;
	}

	@Override
	public Iterator<E> iterator() {
		
		return new LinkedQueueIterator(this);
	}
	
}
