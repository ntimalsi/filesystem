/**
 * 
 */
package edu.olemiss.csci211.Assignment2;


import edu.olemiss.csci211.collections.*;

/** The main class for A1.  This is really a motivating example for our collections data structures, so
 * there's no tasks in this file.
 * 
 * @author rhodes
 *
 */
public class A2{

	/**
	 * @param args ignored, for now.
	 */
	public static void main(String[] args) {
		
		// Make a network of nodes with the following three names.
		Network net = new Network(new String[] {"atlanta", "boston", "chicago"});
			
		// Set boston as the only neighbor of atlanta.
		net.addNeighbors( "atlanta", new String[] {"boston"});
		
		// boston is a neighbor of atlanta, so it should be reachable. 
		System.out.println("Boston reachable from Atlanta?: " + net.isReachable("boston", "atlanta") );

		// atlanta is not a neighbor of boston, so it should not be reachable.
		System.out.println("Atlanta reachable from boston?: " + net.isReachable("atlanta", "boston") );

		
		// boston is not a neighbor of chicago, so it should not be reachable.
		System.out.println("Boston reachable from chicago?: " + net.isReachable("boston", "chicago") );
		
		
		Network n2 = new Network(new String[] {"John McWhorter","Boris Johnson","Lisa Simpson","Kevin Bacon"});
		
		n2.addNeighbors("John McWhorter", new String[] {"Boris Johnson", "Lisa Simpson"});	
		n2.addNeighbors("Boris Johnson", new String[] {"Kevin Bacon", "Lisa Simpson"});
		n2.addNeighbors("Kevin Bacon", new String[] {"Lisa Simpson"});
		
		
		System.out.println("Kevin Bacon reachable from John McWhorter?: " + n2.isReachable("Kevin Bacon", "John McWhorter") );
		System.out.println("Kevin Bacon reachable from Lisa Simpson?: " + n2.isReachable("Kevin Bacon", "Lisa Simpson") );
	}
	
	

}
