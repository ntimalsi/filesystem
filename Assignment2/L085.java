package edu.olemiss.csci211.Assignment2;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import edu.olemiss.csci211.collections.ArrayBasedQueue;
import edu.olemiss.csci211.collections.GeneralTree;
import edu.olemiss.csci211.collections.LinkedStack;

public class L085 {

	@Test 
	public void iterator1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
				
		Iterator<String> iter = t.iterator();
		
		assertTrue(iter.hasNext());
	}

	@Test 
	public void iterator2() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		
		Iterator<String> iter = t.iterator();
		
		assertEquals("root", iter.next());
	}
	
	@Test 
	public void iterator3() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		t.addChild("root", "child3");
		
		Iterator<String> iter = t.iterator();
		
		assertEquals("root", iter.next());
		assertEquals("child1", iter.next());
		assertEquals("child2", iter.next());
		assertEquals("child3", iter.next());
	}

	@Test 
	public void iterator4() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		t.addChild("root", "child3");
		
		t.addChild("child1", "grandchild1");
		
		Iterator<String> iter = t.iterator();
		
		assertEquals("root", iter.next());
		assertEquals("child1", iter.next());
		assertEquals("grandchild1", iter.next());
		assertEquals("child2", iter.next());
		assertEquals("child3", iter.next());
	}


}
