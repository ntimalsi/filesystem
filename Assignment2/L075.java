package edu.olemiss.csci211.Assignment2;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.olemiss.csci211.collections.GeneralTree;
import edu.olemiss.csci211.collections.LinkedQueue;
import edu.olemiss.csci211.collections.LinkedStack;

public class L075 {
	
//	updateSizes
//	find
//	contains


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
	public void updateSizes1() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
		f.addChild("/", new Filesystem.File("bar", 21));
		
		f.updateSizes();
		System.out.println(f);
		
		assertEquals(31, f.getRootSize());
//		assertEquals("name:/  bytes:31 \n" + 
//				"	name:foo  bytes:10\n" + 
//				"	name:bar  bytes:21\n" + 
//				"\n",f.toString());	
	}

	@Test
	public void updateSizes2() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
		f.addChild("/", new Filesystem.File("bar", 21));
		
		f.addChild("bar", new Filesystem.File("baz", 47));
		f.addChild("bar", new Filesystem.File("boom", 44));
		
		f.updateSizes();
		System.out.println(f);
		assertEquals(101, f.getRootSize());
//		assertEquals("name:/  bytes:101{\n" + 
//				"	name:foo  bytes:10{}\n" + 
//				"	name:bar  bytes:91{\n" + 
//				"		name:baz  bytes:47{}\n" + 
//				"		name:boom  bytes:44{}\n" + 
//				"	}\n" + 
//				"}\n" ,f.toString());
	}


	@Test
	public void find1() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		GeneralTree<String>.Node result = t.find("root");
		
		GeneralTree<String>.Node correct = t.new Node("root");
		
		assertTrue(result.equals(correct));
	}
	
	

	@Test
	public void find2() {
		
		GeneralTree<String> t = new GeneralTree<String>();
		t.setRootData("root");
		
		t.addChild("root", "child1");
		t.addChild("root", "child2");
		t.addChild("root", "child3");
		
		t.addChild("child3", "grandchild1");
		
		GeneralTree<String>.Node result = t.find("child1");
		
		GeneralTree<String>.Node correct = t.new Node("child1");
		
		assertTrue(result.equals(correct));

	}

	


}
