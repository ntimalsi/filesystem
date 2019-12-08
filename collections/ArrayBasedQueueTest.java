package edu.olemiss.csci211.collections;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;


public class ArrayBasedQueueTest {
	

	@Test
	public void constructorDoesntThrow() {
		
		new ArrayBasedQueue<Integer>(10);
	}
	
	@Test
	public void enqueueShouldntThrow() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		q.enqueue(5);
	}

	@Test(expected = IllegalStateException.class)
	public void enqueueShouldThrow() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(1);
		q.enqueue(5);
		q.enqueue(5);

	}

	
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void dequeueThrows() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		q.dequeue();
	}

	
	@Test
	public void enqueueDequeue1() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		q.enqueue(5);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueueDequeue2() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(5);
		q.enqueue(7);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueueDequeue3() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(5);
		q.enqueue(7);
		int v = q.dequeue();
		
		assertEquals(5,v);
	}
	
	@Test
	public void enqueueDequeue4() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(5);
		q.enqueue(7);
		q.dequeue();

		int v = q.dequeue();
		
		assertEquals(7,v);
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void enqueueDequeueShouldThrow() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(5);
		q.enqueue(7);
		q.dequeue();
		q.dequeue();

		q.dequeue(); // should throw
	}

	@Test
	public void enqueueAfterDequeue() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(5);
		q.dequeue();
		q.enqueue(12);

		int v = q.dequeue(); 
		assertEquals(12, v);
	}
	
	@Test
	public void add() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.add(7);
		int r=q.dequeue();
		
		assertEquals(7, r);
	}
	
	@Test 
	public void clear1() {
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		for(int i=0; i<10; i++) {
			
			q.enqueue(1914);
		}
				
		q.clear();
		
		assertEquals(0,q.size());
	}
	
	@Test
	@SuppressWarnings("deprecation") // for Date.setHours(), Date.getHours()
	public void shallowCopy1() {
		
		// let's use Date, since it's mutable.
		ArrayBasedQueue<Date> q = new ArrayBasedQueue<Date>(10);
		
		Date d = new Date();
		d.setHours(17);
		
		q.enqueue(d);
		
		
		
		ArrayBasedQueue<Date> q2 =(ArrayBasedQueue<Date>) q.shallowCopy();
		
			
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
	
	
	@Test
	public void shallowCopy2() {
		
		
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		for(int i=0; i<10; i++) {
			
			q.enqueue(i);
		}		
		
		
		ArrayBasedQueue<Integer> q2 =(ArrayBasedQueue<Integer>) q.shallowCopy();
		
		for(int i=0; i<10; i++) {
			
			q2.dequeue();
		}		
		
		// We emptied q2, but q1 should be unchanged.
		assertTrue(q2.size() == 0 && q.size() == 10);		
	}

	
	@Test(expected=NoSuchElementException.class)
	public void shallowCopy3() {
		
		
		ArrayBasedQueue<Integer> q1 = new ArrayBasedQueue<Integer>(15);
		
		for(int i=0; i<10; i++) {
			
			q1.enqueue(i);
		}		
		
		ArrayBasedQueue<Integer> q2 =(ArrayBasedQueue<Integer>) q1.shallowCopy();
					
		q2.enqueue(10); // supposed to only affect q2
		q2.enqueue(11); // supposed to only affect q2
		
		for(int i=0; i<10; i++) {
			
			q1.dequeue();
		}		
		
		int last = q1.dequeue(); // should throw
				
	}

	
	@Test
	public void shallowCopy4() {
			
		ArrayBasedQueue<Integer> q1 = new ArrayBasedQueue<Integer>(15);
		
		for(int i=0; i<10; i++) {
			
			q1.enqueue(i);
		}		
		
		ArrayBasedQueue<Integer> q2 =(ArrayBasedQueue<Integer>) q1.shallowCopy();
			
		//Let's check that the order of elements is correct in the copy.
		for(int i=0; i<10; i++) {
			
			int v1=q1.dequeue();
			int v2=q2.dequeue();
			
			assertEquals(v1, v2);
		}				
	}

	
	
	
	@Test
	public void hasNextFalseOnEmptyList() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		Iterator<Integer> iter = q.iterator();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNextTrueforSingle() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(10);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
	}
	

	@Test
	public void hasNext2() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(10);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNext3() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(10);
		
		q.enqueue(10);
		q.enqueue(11);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());

	}

	@Test
	public void hasNext4() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(2);
		
		q.enqueue(10);
		q.enqueue(11);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());
	}

	
	@Test
	public void hasNext5() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(2);
		
		q.enqueue(10);
		q.enqueue(11);
		q.dequeue();
		q.enqueue(12);
		
		Iterator<Integer> iter = q.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void nextShouldThrow() {
	
		ArrayBasedQueue<Integer> q = new ArrayBasedQueue<Integer>(2);
		
		q.enqueue(10);
		q.enqueue(11);
		q.dequeue();
		q.enqueue(12);
		
		Iterator<Integer> iter = q.iterator();
		

		
		iter.next();
		iter.next();
		iter.next();
	}


	


}
