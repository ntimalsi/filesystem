package edu.olemiss.csci211.collections;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedQueueTest {
	
	

	@Test
	public void constructorDoesntThrow() {
		
		new LinkedQueue<Integer>();
	}
	
	@Test
	public void enqueueDoesntThrow() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		q.enqueue(5);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void dequeueThrows() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		q.dequeue();
	}

	
	@Test
	public void enqueuedequeue1() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		q.enqueue(5);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueuedequeue2() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(5);
		q.enqueue(7);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueuedequeue3() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(5);
		q.enqueue(7);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueuedequeue4() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(5);
		q.enqueue(7);
		q.dequeue();

		int v = q.dequeue();
		
		assertEquals(7,v);
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void enqueuedequeueShouldThrow() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(5);
		q.enqueue(7);
		q.dequeue();
		q.dequeue();

		q.dequeue(); // should throw
	}

	@Test
	public void enqueueAfterdequeue() {
		
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(5);
		q.dequeue();
		q.enqueue(12);

		int v = q.dequeue(); 
		assertEquals(12, v);
	}
	
	@Test
	@SuppressWarnings("deprecation") // for Date.setHours(), Date.getHours()
	public void shallowCopy1() {
		
		// let's use Date, since it's mutable.
		LinkedQueue<Date> q = new LinkedQueue<Date>();
		
		Date d = new Date();
		d.setHours(17);
		
		q.enqueue(d);
		
		LinkedQueue<Date> q2 =(LinkedQueue<Date>) q.shallowCopy();
		
			
		Date d2=q2.dequeue();

		
		// The shallow copy should contain references to the same objects
		// as the original.
		assertTrue(d == d2);
		
		// So, we can change the Date in the original list using the Date that
		// came from the shallow copy.
		d2.setHours(14);
		assertTrue(d.getHours() == 14);
		
		// I don't usually put two asserts in one test, but this seems like 
		// an instructive example.
	}


	@Test(expected=NoSuchElementException.class)
	public void shallowCopy2() {
		
		
		LinkedQueue<Integer> q1 = new LinkedQueue<Integer>();
		
		for(int i=0; i<10; i++) {
			
			q1.enqueue(i);
		}		
		
		LinkedQueue<Integer> q2 =(LinkedQueue<Integer>) q1.shallowCopy();
					
		q2.enqueue(10); // supposed to only affect q2
		q2.enqueue(11); // supposed to only affect q2
		
		for(int i=0; i<10; i++) {
			
			q1.dequeue();
		}		
		
		int last = q1.dequeue(); // should throw
				
	}

	@Test
	public void shallowCopy4() {
			
		LinkedQueue<Integer> q1 = new LinkedQueue<Integer>();
		
		for(int i=0; i<10; i++) {
			
			q1.enqueue(i);
		}		
		
		LinkedQueue<Integer> q2 =(LinkedQueue<Integer>) q1.shallowCopy();
			
		//Let's check that the order of elements is correct in the copy.
		for(int i=0; i<10; i++) {
			
			int v1=q1.dequeue();
			int v2=q2.dequeue();
			
			assertEquals(v1, v2);
		}				
	}

	
	@Test
	public void hasNextFalseOnEmptyList() {
	
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		Iterator<Integer> iter = q.iterator();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNextTrueforSingle() {
	
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(10);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
	}
	

	@Test
	public void hasNext2() {
	
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(10);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNext3() {
	
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		
		q.enqueue(10);
		q.enqueue(11);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());

	}
	

	


}
