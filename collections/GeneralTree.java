package edu.olemiss.csci211.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.olemiss.csci211.Assignment2.Filesystem.File;

public class GeneralTree<E> implements Iterable<E>{
	
	public class Node{ 
		
		public E data;
		public List<Node> children;
		
		/** Construct a node with the given element e.
		 * 
		 * @param e the value that will be contained in the new node.
		 */
		public Node(E e){
			
			this.data=e;
			children = new LinkedQueue<Node>();
		}
		
		/** Add the given node n to the list of children of this node.
		 * 
		 * @param n the node to be added as a child
		 */
		public void addChild(Node n){
			
			children.add(n);
		}
		
		@Override
		public boolean equals(Object o) {
			
			return ((Node) o).data.equals(this.data);
		}
		
	}
	
	
	protected Node root;
	private int size;
	
	
	/** Construct an empty tree.
	 * 
	 */
	public GeneralTree(){
			
	}

	
	/** Assign the given data to the root node of this tree. If no root
	 * node exists, then one will be created, and assigned the given data.
	 * If a root node already exists, it will be assigned the given data,
	 * but its children (if any) will be undisturbed.
	 * 
	 * @param newnodeData
	 */
	public void setRootData(E newnodeData) {
		
		if( root == null)
			this.root = new Node(newnodeData);	
		else 
			this.root.data = newnodeData;
	}
	
	
	/** Return the data value found in the root node of this tree.
	 * 
	 * @return the root data value
	 * @throws NoSuchElementException if no root node is present
	 */
	public E getRootData() {
		
		if( root == null)
			throw new NoSuchElementException("No root node found.");	
		else 
			return this.root.data;
	}

	/** Return the number of nodes in the tree.
	 *  
	 * @return the number of nodes
	 */
	public int size() {
		
		return this.size;
	}

	
	/** Add a new node containing <tt> newnodeData </tt> as a child of a node containing <tt> parentData </tt>. 
	 * If no node matching <tt>parentData</tt> is found, an exception is thrown. If more than one matching node exists,
	 * the new child node is added to only one such node.
	 * 
	 * @param parentData used to identify the parent node
	 * @param newnodeData the data assigned to the new child node
	 * @throws NoSuchElementException if no match for parentData is found.
	 * 
	 */
	public void addChild(E parentData, E newnodeData) {
		
		Node parent = this.find(parentData);
		
		if (parent == null) {
			throw new NoSuchElementException("No parent node found.");
		}
	
		Node c = new Node(newnodeData);
		parent.addChild(c);
		
		this.size++;
	}
	
	
	
	public Node find(E target) {
		
		Node n = findHelper(root, target);
		
		if (n== null)
			throw new NoSuchElementException("No matching node found.");
		
		return n;
			
	}
	
	/** Indicates whether the tree contains the given target value.
	 * 
	 * 
	 * @param target the value being searched for
	 * @return true if the target is found, false otherwise.
	 */
	public boolean contains(E target) {
		
		Node n = findHelper(root, target);
		
		return n != null;			
	}

	
	private Node findHelper(Node n, E target) {
	
		if(n == null)
			return null;
		
		if (n.data.equals(target))
			return n;
		
		for(Node child: n.children) {
			
			Node result = findHelper(child, target);
			
			if ( result != null)
				return result;		
		}
		
		return null;
	}
	

	/**
	 * Returns a string produced by a preOrder traversal of the tree. 
	 * Could be useful for testing and debugging.
	 * @return a string representing the contents of the tree.
	 */
	public String toString(){
		
		return preOrder("",root); 
	}
	
	
	
	/**
	 * A preOrder traversal of the tree that returns a string representing the 
	 * contents of the tree. Brackets and indentation are used to indicate the
	 * structure of the tree.
	 * 
	 * @param t the tree to be traversed.
	 * @return A String representing the contents of the tree.
	 */
	private String preOrder(String indent, Node t){
		
		if (t == null)
			return "";
				
		String result = indent + t.data + "{"; 
		
		if (t.children.isEmpty())
			return result + "}\n";  // Print a leaf node on one line.
		else
			result += "\n"; // Parent nodes span multiple lines.
		
		for (Node child: t.children) {
			
			result += preOrder(indent + "\t", child);
		}
		
		result += indent + "}\n";
		
		return result;
	}
	
		
	
	@Override
	public GeneralTreeIterator iterator() {
		
		return new GeneralTreeIterator(this);
	}
	
	/** An iterator class for GeneralTrees. For A2, we'll do this the
	 * "easy way". That is, when the iterator is constructed,
	 * we'll do a pre-order traversal of the entire tree, and when each node is visited,
	 * we'll add the node to a list.
	 * 
	 * Then we can use the list iterator to help with our implementation of the GeneralTreeIterator 
	 */
	private class GeneralTreeIterator implements Iterator<E>{
		
		
		Queue<Node> nodeList = new LinkedQueue<Node>();
		Iterator<Node> nodeListIter;
		
		public GeneralTreeIterator(GeneralTree<E> t){
			
			helper(t.root);
			nodeListIter = nodeList.iterator();
		}
		
		private void helper(Node t) {
			
			if(t!=null) {
				
				nodeList.add(t);
				
				for(Node c:t.children) {
					
					helper(c);
				}
			}
		}

		@Override
		public boolean hasNext() {
			
			return  nodeListIter.hasNext();
		}

		@Override
		public E next() {
		
			return nodeListIter.next().data;
		}
	}
}



