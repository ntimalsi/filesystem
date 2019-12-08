package edu.olemiss.csci211.Assignment2;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.olemiss.csci211.Assignment2.Network.Node;
import edu.olemiss.csci211.collections.GeneralTree;

public class NetworkTest {

	@Test (expected = IllegalArgumentException.class)
	public void constructorWithNULLThrows() {
		
		Network n = new Network(null);
	}

	
	@Test 
	public void constructorwithValidArgsDoesntThrow1() {
		
		Network n = new Network(new String[] {"MEM"});
	}

	
	@Test 
	public void constructorwithValidArgsDoesntThrow2() {
		
		Network n = new Network(new String[] {"MEM","MIA"});
	}

	@Test 
	public void constructorwithValidArgsDoesntThrow3() {
		
		Network n = new Network(new String[] {});
	}

	
	
	
	@Test (expected = NoSuchElementException.class)
	public void addNeighbors1() {
		
		Network n = new Network(new String[] {"NEZ"});
		
		n.addNeighbors("SEZ", new String[] {"BWI"});	
	}

	
	@Test (expected = NoSuchElementException.class)
	public void addNeighbors2() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"BWI"});	
	}

	@Test 
	public void addNeighbors3() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
	}
	
	@Test 
	public void addNeighbors4() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "E50"});	
	}

	@Test 
	public void addNeighbors5() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"SEZ"});	
	}

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

	
	// not part of a2
	@Test 
	public void traverseTest1() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "E50"});	
		
		n.traverse("SEZ");
	
	}

	
	
	// SEZ SEZ
	// NEZ NEZ
	// W50 W50
	// E50 E50
	
	
	@Test  
	public void spanningTree1() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});	
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		System.out.println(t);
		
		assertEquals( "SEZ{\n"
					+ "\tNEZ{}\n"
					+ "}\n",
					t.toString());
	}

	@Test  
	public void spanningTree2() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		System.out.println(t);
		
		assertEquals( "SEZ{\n"
					+ "\tNEZ{\n"
					+ "\t\tW50{}\n"
					+ "\t}\n"
					+ "}\n",
					t.toString());
		
	}
	@Test  
	public void spanningTree3() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ"});
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		System.out.println(t);
		
		assertEquals( "SEZ{\n"
					+ "\tNEZ{\n"
					+ "\t\tW50{}\n"
					+ "\t}\n"
					+ "}\n",
					t.toString());
	}

	
	@Test  
	public void spanningTree4() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ","W50"});
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		n.addNeighbors("W50", new String[] {"NEZ","SEZ","E50"});
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		System.out.println(t);
		
		assertEquals( 	"SEZ{\n" + 
						"	W50{\n" + 
						"		E50{}\n" + 
						"	}\n" + 
						"	NEZ{}\n" + 
						"}\n",
					t.toString());
	}
	
	
	
	
	@Test  
	public void spanningTree6() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ","E50"});	
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		n.addNeighbors("W50", new String[] {"E50"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		assertEquals(   "SEZ{\n" + 
						"	E50{}\n" + 
						"	NEZ{\n" + 
						"		W50{}\n" + 
						"	}\n" + 
						"}\n",
				t.toString());

		
		System.out.println(t);
	}

	@Test  
	public void spanningTree7() {
		
		Network n = new Network(new String[] {"SEZ","NEZ","W50","E50"});
		
		n.addNeighbors("SEZ", new String[] {"NEZ", "W50", "E50"});	
		n.addNeighbors("NEZ", new String[] {"W50","SEZ"});
		n.addNeighbors("W50", new String[] {"E50", "NEZ"});
		n.addNeighbors("E50", new String[] {"SEZ"});
		
		GeneralTree<Network.Node> t = n.spanningTree("SEZ");
		
		assertNotNull(t);
		
		System.out.println(t);
	}
	
	


}
