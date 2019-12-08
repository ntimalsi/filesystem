/**
 * 
 */
package edu.olemiss.csci211.collections;


import java.util.Iterator;
import java.util.NoSuchElementException;

/** A simple stack class. Uses a linked implementation.
 * 
 * @author rhodes
 *
 * @param <E> the type of element contained in the stack.
 */
public class LinkedStack<E> implements Stack<E>{
	
	private int size = 0;

	// Unlike the book, we'll use an inner class for our Node.
	// Its two data members can be accessed directly by the Stack
	// code, so we don't need setters and getters.
	protected class Node{
		
		E data; 
		Node next;
	}
	
	protected Node top; // not public, but can still be seen by other classes in the
			  			// csci211 package.
	
	/** Create an empty stack.
	 * 
	 */
	public LinkedStack(){
		
		top = null;
	}
	
	@Override // see interface for comments.
	public void push(E e){
		
		Node n = new Node();
		n.data = e;
		n.next = top;
		top = n;
		size++;
	}
	
	@Override // see interface for comments.
	public E pop(){
		
		if (top == null)
			throw new NoSuchElementException("Pop on empty stack.");

		E r = top.data;
		top = top.next;
		size--;	
		
		return r;
	}
	
	@Override // see interface for comments.
	public E peek() {
		
		if (top == null)
			throw new NoSuchElementException("Peek on empty stack.");
		
		return top.data;
	}
	
	/** Retrieve the number of elements on this stack.
	 * 
	 * @return an int containing the number of elements
	 */
	public int size() {
		
		return this.size;
		
	}
	
	/** An Iterator for our LinkedStack.
	 * 
	 * @author rhodes
	 *
	 */
	 class LinkedStackIterator implements Iterator<E> {
		
		LinkedStack<E>.Node current;
		
		public LinkedStackIterator(LinkedStack<E> s){
			
			current = s.top;
		}
		
		@Override
		public boolean hasNext() {
			
			return (current != null);
		}

		@Override
		public E next() {

			if (hasNext()) {
			
				E r = current.data;
				
				current = current.next;
				return r;
			} else
				throw new NoSuchElementException("no more elements");
			

		}

		
	}


	@Override
	public void add(E element) {
		
		push(element);
		
	}

	@Override
	public void clear() {
	
		this.top = null;
		this.size = 0;
	}

	@Override
	public List<E> shallowCopy() {
		
		LinkedStack<E> s = new LinkedStack<E>();
		
		// Sharing the nodes actually works for LinkedStack, but only
		// in a garbage collected language like Java.
//		s.top = this.top;
//		s.size = this.size;
		
		//Let's behave ourselves, though, and write code that isn't "tricky".
		
		shallowCopyHelper(this.top, s);
		
		return s;
	}
	
	private void shallowCopyHelper(Node n, LinkedStack<E> s) {
		
		if(n == null)
			return;
		
		shallowCopyHelper(n.next, s);
		
		s.push(n.data);
	}

	@Override
	public Iterator<E> iterator() {
		
		return new LinkedStackIterator(this);
	}

}
	
