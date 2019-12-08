package edu.olemiss.csci211.Assignment2;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import edu.olemiss.csci211.collections.ArrayBasedQueue;
import edu.olemiss.csci211.collections.GeneralTree;
import edu.olemiss.csci211.collections.LinkedQueue;
import edu.olemiss.csci211.collections.LinkedStack;

public class L100 {

	@Test 
	public void isReachable1() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "E50"});	
		
		assertTrue(n.isReachable("NEZ", "SEZ"));
	
	}
	
	@Test 
	public void isReachable2() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "E50"});	
		
		assertFalse(n.isReachable("SEZ", "NEZ"));
	
	}
	
	@Test 
	public void isReachable3() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "E50"});	
		n.addNeighbors("NEZ", new String[] {"SEZ", "E50"});
		
		assertTrue(n.isReachable("SEZ", "NEZ"));
	
	}

	@Test 
	public void isReachable4() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		n.addNeighbors("NEZ", new String[] {"W50"});
		n.addNeighbors("W50", new String[] {"E50"});
		
		assertTrue(n.isReachable("E50", "SEZ"));
	
	}
	
	@Test 
	public void isReachable5() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		n.addNeighbors("NEZ", new String[] {"W50"});
		n.addNeighbors("W50", new String[] {"E50"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		assertTrue(n.isReachable("E50", "SEZ"));
	
	}

	@Test 
	public void isReachable6() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		n.addNeighbors("NEZ", new String[] {"W50"});
		n.addNeighbors("W50", new String[] {"E50"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		assertTrue(n.isReachable("E50", "E50"));
	}
	
	@Test  
	public void isReachable7() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		n.addNeighbors("NEZ", new String[] {"W50"});
		n.addNeighbors("W50", new String[] {"E50", "W50"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		assertTrue(n.isReachable("SEZ", "SEZ"));
	
	}

	@Test  
	public void isReachable8() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		n.addNeighbors("W50", new String[] {"E50"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		assertTrue(n.isReachable("E50", "E50"));
	}
	
	
//	@Test  
//	public void spanningTree1() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ"});	
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		System.out.println(t);
//		
//		assertEquals( "SEZ{\n"
//					+ "\tNEZ{}\n"
//					+ "}\n",
//					t.toString());
//	}
//
//	@Test  
//	public void spanningTree2() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ"}); 
//		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		System.out.println(t);
//		
//		assertEquals( "SEZ{\n"
//					+ "\tNEZ{\n"
//					+ "\t\tW50{}\n"
//					+ "\t}\n"
//					+ "}\n",
//					t.toString());
//		
//	}
//	@Test  
//	public void spanningTree3() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ"});
//		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		System.out.println(t);
//		
//		assertEquals( "SEZ{\n"
//					+ "\tNEZ{\n"
//					+ "\t\tW50{}\n"
//					+ "\t}\n"
//					+ "}\n",
//					t.toString());
//	}
//
//	
//	@Test  
//	public void spanningTree4() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ","W50"});
//		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
//		n.addNeighbors("W50", new String[] {"NEZ","SEZ","E50"});
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		System.out.println(t);
//		
//		assertEquals( 	"SEZ{\n" + 
//						"	W50{\n" + 
//						"		E50{}\n" + 
//						"	}\n" + 
//						"	NEZ{}\n" + 
//						"}\n",
//					t.toString());
//	}
//	
//	
//	
//	
//	@Test  
//	public void spanningTree6() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ","E50"});	
//		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
//		n.addNeighbors("W50", new String[] {"E50"});
//		n.addNeighbors("E50", new String[] {"SEZ"});
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		assertEquals(   "SEZ{\n" + 
//						"	E50{}\n" + 
//						"	NEZ{\n" + 
//						"		W50{}\n" + 
//						"	}\n" + 
//						"}\n",
//				t.toString());
//
//		
//		System.out.println(t);
//	}
//
//	@Test  
//	public void spanningTree7() {
//		
//		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
//		
//		n.addNeighbors("SEZ", new String[] {"NEZ", "W50", "E50"});	
//		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
//		n.addNeighbors("W50", new String[] {"E50", "NEZ"});
//		n.addNeighbors("E50", new String[] {"SEZ"});
//		
//		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
//		
//		assertNotNull(t);
//		
//		System.out.println(t);
//	}
	
	
	@Test 
	public void toString1() {
		
		Filesystem f = new Filesystem("/");

		assertEquals("name:/  bytes:0\n" ,
					f.toString());
	}

	@Test 
	public void toString2() {
		
		Filesystem f = new Filesystem("/");
		f.addChild("/", new Filesystem.File("foo", 10));
		f.addChild("/", new Filesystem.File("boom", 10));
		f.addChild("foo", new Filesystem.File("bar", 10));
		f.addChild("foo", new Filesystem.File("baz", 10));

		// use \t (tabs) for indentation
		assertEquals("name:/  bytes:30\n" + 
					"	name:foo  bytes:20\n" + 
					"		name:bar  bytes:10\n" + 
					"		name:baz  bytes:10\n" + 
					"	name:boom  bytes:10\n" ,
					f.toString());
	}
	
	
	

	
}
