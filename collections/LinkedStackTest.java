package edu.olemiss.csci211.collections;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedStackTest {

	@Test
	public void constructorDoesntThrow() {
		
		new LinkedStack<Integer>();
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void emptyPop() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		assertEquals(null, s.pop());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void emptyPeek() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		assertEquals(null, s.peek());
	}

	
	@Test
	public void pushpop() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		s.push(12);
		
		assertEquals(new Integer(12), s.pop());
	}

	@Test
	public void pushpeek() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		s.push(12);
		
		assertEquals(new Integer(12), s.peek());
	}

	
	@Test(expected = java.util.NoSuchElementException.class)
	public void pushpoppop() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		s.push(12);
		s.pop();
		
		assertEquals(null, s.pop());
	}

	
	@Test
	public void pushpushpoppop() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		s.push(12);
		s.push(9);
		
		s.pop();
		
		assertEquals(new Integer(12), s.pop());
	}

	@Test
	public void pushpoppushpop() {
		
		LinkedStack<Integer> s=new LinkedStack<Integer>();
		
		s.push(12);
		s.pop();
		s.push(9);
		
		assertEquals(new Integer(9), s.pop());
	}
	
	@Test
	@SuppressWarnings("deprecation") // for Date.setHours(), Date.getHours()
	public void shallowCopy1() {
		
		// let's use Date, since it's mutable.
		LinkedStack<Date> s = new LinkedStack<Date>();
		
		Date d = new Date();
		d.setHours(17);
		
		s.push(d);
		
		LinkedStack<Date> s2 =(LinkedStack<Date>) s.shallowCopy();
					
		Date d2=s2.pop();

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
		
		
		LinkedStack<Integer> s1 = new LinkedStack<Integer>();
		
		for(int i=0; i<10; i++) {
			
			s1.push(i);
		}		
		
		LinkedStack<Integer> s2 =(LinkedStack<Integer>) s1.shallowCopy();
					
		s2.push(10); // supposed to only affect s2
		s2.push(11); // supposed to only affect s2
		
		for(int i=0; i<10; i++) {
			
			s1.pop();
		}		
		
		int last = s1.pop(); // should throw
				
	}


	@Test
	public void shallowCopy4() {
			
		LinkedStack<Integer> q1 = new LinkedStack<Integer>();
		
		for(int i=0; i<10; i++) {
			
			q1.push(i);
		}		
		
		LinkedStack<Integer> q2 =(LinkedStack<Integer>) q1.shallowCopy();
			
		//Let's check that the order of elements is correct in the copy.
		for(int i=0; i<10; i++) {
			
			int v1=q1.pop();
			int v2=q2.pop();
			
			assertEquals(v1, v2);
		}				
	}

	
	
	@Test
	public void hasNextFalseOnEmptyList() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		Iterator<Integer> iter = s.iterator();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNextTrueforSingle() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		
		Iterator<Integer> iter = s.iterator();
		
		assertTrue(iter.hasNext());
	}
	

	@Test
	public void hasNext2() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		
		Iterator<Integer> iter = s.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());
	}
	
	
	@Test
	public void hasNext3() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		s.push(11);
		
		Iterator<Integer> iter = s.iterator();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertTrue(iter.hasNext());
		
		iter.next();
		
		assertFalse(iter.hasNext());

	}
	

	@Test
	public void next1() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		s.push(11);
		
		Iterator<Integer> iter = s.iterator();
		
		assertEquals(11, (int) iter.next());
	}
	
	@Test
	public void next2() {
	
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		s.push(11);
		
		Iterator<Integer> iter = s.iterator();
		
		iter.next();
		
		assertEquals(10, (int) iter.next());
	}

	@Test(expected = java.util.NoSuchElementException.class) 
	public void next3() {
		
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		s.push(10);
		s.push(11);
		
		Iterator<Integer> iter = s.iterator();
		
		iter.next();
		iter.next();
		
		iter.next(); // should throw
	}


	@Test
	public void find1() {
		
		List<Integer> l = new LinkedStack<Integer>();
		
		l.add(1);
		l.add(2);
		l.add(3);

		assertTrue(l.find(2)!=null);
	}

	@Test
	public void find2() {
		
		List<Integer> l = new LinkedStack<Integer>();
		
		l.add(1);
		l.add(2);
		l.add(3);

		assertTrue(l.find(4)==null);
	}

	@Test
	public void find3() {
		
		List<Integer> l = new LinkedStack<Integer>();
		
		l.add(1);
		l.add(2);
		l.add(3);

		assertTrue(l.find(3)!=null);
	}

	@Test
	public void find4() {
		
		List<Integer> l = new LinkedStack<Integer>();
		
		l.add(1);
		l.add(2);
		l.add(3);

		assertTrue(l.find(1)!=null);
	}

	@Test
	public void find5() {
		
		List<Integer> l = new LinkedStack<Integer>();
		
		Integer one = new Integer(1);
		
		l.add(one);
		l.add(2);
		l.add(3);

		assertTrue(l.find(1) == one);
	}

	
	
	
}