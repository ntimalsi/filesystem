package edu.olemiss.csci211.Assignment2;

import java.util.NoSuchElementException;

import edu.olemiss.csci211.Assignment2.Network.Node;
import edu.olemiss.csci211.collections.*;

/** Represents a network in which nodes each have a set of 
 *  neighboring nodes. It is possible to traverse the 
 *  network by hopping from neighbor to neighbor.
 *  
 * @author rhodes
 *
 */
public class Network {
	
	List<Node> nodes= new LinkedStack<Node>();

	
	class Node {
		
		List<Node> neighbors= new LinkedStack<Node>();

		private String name;
		private boolean isVisited=false;
		private boolean isInTree=false;
		
		
		public Node(){
			
		}

		public Node(String Name){
			
			this.name = Name;
		}
		
		public Node(String Name, List<Node> neighbors){
			
			this(Name);
			this.neighbors = neighbors.shallowCopy();
		}

		public void addNeighbor(Node newNeighbor){
			
			this.neighbors.add(newNeighbor);
		}
		
		
		@Override
		public boolean equals(Object o) {
			
			Node n = (Node) o; 
			return this.name.equals(n.name);
		}
				
		public boolean isVisited() {
			
			return this.isVisited;
		}
		
		public void clearVisited() {
			
			this.isVisited=false;
		}
		

		public void setVisited() {
			
			this.isVisited=true;
		}
	
		///////////
		public boolean isInTree() {
			
			return this.isInTree;
		}
		
		public void clearIsInTree() {
			
			this.isInTree=false;
		}
		

		public void setIsInTree() {
			
			this.isInTree=true;
		}

		
		public String toString() {
			
			return this.name;
		}
	}

	
	public Network(String [] nodeNames) {
		
		if(nodeNames == null) {
			
			throw new IllegalArgumentException("nodeNames array is null.");
		} else {
			for(String name: nodeNames) {
				
				Node n = new Node(name);
				addNode(n);
			}
		}
	}
	
	
	public void addNeighbors( String nodeName, String [] neighborNames) {
		
		Node node=nodes.find( new Node(nodeName) );
		
		if (node == null)
			throw new NoSuchElementException("Couldn't find node with name "+nodeName);
		
		for(String neighborName: neighborNames) {
	
			Node neighborNode=nodes.find( new Node(neighborName) );
			
			if (neighborNode == null)
				throw new NoSuchElementException("Couldn't find neighbor with name " + neighborName);

			
			node.addNeighbor(neighborNode);
		}
	}
	
	private void addNode(Node n) {
		
		this.nodes.add(n);
	}
	
	
	private void clearIsInTree() {
		
		for(Node n: nodes) {
			
			n.clearIsInTree();
		}
	}
	
	private void clearVisited() {
		
		for(Node n: nodes) {
			
			n.clearVisited();
		}
	}

	
	/** Indicate whether a path exists between the start and the goal. 
	 * 
	 * @param goalName name of the goal node
	 * @param startName name of the starting node
	 * @return true if the goal is reachable from the start, false otherwise
	 */
	public boolean isReachable(String goalName, String startName) {
		
		
		Node goal  = nodes.find(new Node(goalName));
		Node start = nodes.find(new Node(startName));
		
		
		Stack<Node> unVisitedNodes= new LinkedStack<Node>();
		
		clearVisited();
		
		unVisitedNodes.push(start);
		
		
		do {
			
			Node n = unVisitedNodes.pop();
			
			n.setVisited();
		
			for(Node neighbor: n.neighbors) {
			
				if (neighbor == goal) {
					
					return true;
				} else {
					
					if(! neighbor.isVisited())
						unVisitedNodes.push(neighbor);
				}
			}
		} while(!unVisitedNodes.isEmpty());
		
		return false;
	}

	
	//just testing
	// not part of a2
	public boolean traverse( String startName) {
		
		
		Node start = nodes.find(new Node(startName));
		
		
		Stack<Node> unVisitedNodes= new LinkedStack<Node>();
		
		clearVisited();
		
		unVisitedNodes.push(start);
		
		
		do {
			
			Node n = unVisitedNodes.pop();
			
			n.setVisited();
			
			System.out.println(n);
		
			for(Node neighbor: n.neighbors) {
					
				if(! neighbor.isVisited())
					unVisitedNodes.push(neighbor);
				
			}
		} while(!unVisitedNodes.isEmpty());
		
		return false;
	}
	

	public GeneralTree<Node> spanningTree( String rootName) {
		
		Node start = nodes.find(new Node(rootName));	
		
//		List<Node> unVisitedNodes= new LinkedStack<Node>();
		List<Node> unVisitedNodes= new LinkedQueue<Node>();

		GeneralTree<Node> spanningTree = new GeneralTree<Node>();

		clearIsInTree();
		
		spanningTree.setRootData(start);
		start.setIsInTree();

		unVisitedNodes.add(start);
		
		do {
			
			Node n = unVisitedNodes.remove();
			assert(n.isInTree());
			
			for(Node neighbor: n.neighbors) {

				if(! neighbor.isInTree()){
					spanningTree.addChild(n, neighbor);
					neighbor.setIsInTree();
					unVisitedNodes.add(neighbor);
				} 

			}

		} while(!unVisitedNodes.isEmpty());
		
		return spanningTree;
	}

	
	
}
