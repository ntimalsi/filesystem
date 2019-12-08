package edu.olemiss.csci211.collections;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class GeneralTreeTest {

	@Test
	public void constructorShouldntThrow() {
		
		
		GeneralTree<String> t = new GeneralTree<String>();
	}
	
	@Test
	public void setRootData1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		assertEquals(t.getRootData(),"root");
		
	}
	
	@Test
	public void setRootData2() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		t.setRootData("root2");
		
		assertEquals(t.getRootData(),"root2");
		
	}

	@Test
	public void rootContains1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		assertTrue(t.contains("root"));
	}

	@Test
	public void setRootContains2() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		t.setRootData("root2");
		
		assertTrue(t.contains("root2"));
		assertFalse(t.contains("root"));
	}

	
	@Test
	public void addContains1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		
		assertTrue(t.contains("child1"));
	}
	
	@Test
	public void addContains2() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		
		assertTrue(t.contains("child1"));
		assertTrue(t.contains("child2"));
	}

	@Test
	public void addContains3() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("child1", "grandchild1");
		
		assertTrue(t.contains("child1"));
		assertTrue(t.contains("grandchild1"));
	}

	@Test
	public void addContains4() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		t.addChild("root", "child3");
		
		t.addChild("child3", "grandchild1");
		
		assertTrue(t.contains("child3"));
		assertTrue(t.contains("grandchild1"));
	}

	@Test
	public void toString1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		t.addChild("root", "child3");
		
		t.addChild("child3", "grandchild1");
		
		System.out.println(t);
	}
	

	
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
