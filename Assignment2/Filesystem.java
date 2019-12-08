package edu.olemiss.csci211.Assignment2;

import java.util.NoSuchElementException;

import edu.olemiss.csci211.collections.GeneralTree;

/** A very simple approximation of a filesystem, implemented as a
 * GeneralTree of File objects. Any file can behave as a directory,
 * meaning we can add child files to any file. 
 * 
  */
public class Filesystem extends GeneralTree<Filesystem.File> {

	
	/** Files in this filesystem have two attributes -- A name and a size.
	 * The name is just a string, and should be unique. The size is an
	 * int representing the number of bytes the file occupies. There are
	 * two ways this value can be computed. In the simple case, the file
	 * is a leaf node in the filesystem tree, and the file size is whatever
	 * value was given to the File constructor.
	 * However, if the {@link addchild} method is called on a file at least once,
	 * the file is no longer a leaf node, and behaves like a directory. 
	 * In this case, the file size is recursively computed as the sum of 
	 * the sizes of all the descendant leaf nodes. For non-leaf nodes, 
	 * whatever file size was originally given to the File constructor is
	 * ignored.
	 */
	public static class File {
		
		String name;
		int size;
		
		
		/** Construct a File object with the given name and size.
		 * 
		 * @param name the name of the file
		 * @param size the size of the file
		 */
		public File(String name, int size) {
			this.name = name;
			this.size = size;
		}
		
		
		/** Construct a File object with the given name. Size is 
		 * set to 0.
		 * 
		 * @param name the name of the file
		 */
		public File(String name) {
			this.name = name;
			this.size = 0;
		}
		
		@Override
		public boolean equals(Object o) {
			
			return ((File) o).name.equals(this.name);
		}
		
		@Override
		public String toString() {
			
			return "name:"+name + "  bytes:" + size;
		}
	}
	
	
	/** Construct a new Filesystem tree with a root node 
	 * with the given name. 
	 * 
	 * @param rootName
	 */
	public Filesystem(String rootName) {
		
		this.setRootData(new File(rootName, 0));
	}
	
	/** Add a new File to the Filesystem as a child of the File named <tt> parentName </tt>. 
	 * If no node matching <tt>parentName</tt> is found, an exception is thrown. If more than one matching node exists,
	 * the new child File is added to only one such node.
	 * 
	 * @param parentName used to identify the parent node
	 * @param childFile the node to be added to the Filesystem
	 * @throws NoSuchElementException if no match for parentData is found.
	 */
	public void addChild(String parentName, File childFile) {
		
		super.addChild(new File(parentName), childFile );
		
	}

	
	/** Recompute the <b>size</b> value for all the File objects in the Filesystem. 
	 * There are two ways this value can be computed. In the simple case, the file
	 * is a leaf node in the filesystem tree, and the file size is whatever
	 * value was given to the File constructor.
	 * However, if the {@link addchild} method is called on a file at least once,
	 * the file is no longer a leaf node, and behaves like a directory. 
	 * In this case, the file size is recursively computed as the sum of 
	 * the sizes of all the descendant leaf nodes. For non-leaf nodes, 
	 * whatever file size was originally given to the File constructor is
	 * ignored.
	 * 
	 */
	public void updateSizes() {
		
		updateSizes(this.root);
		
	}

		
	
	
	protected int updateSizes(Node t){
		int result=0;
		
		
		if (t == null)
			return 0;

		if (t.children.isEmpty())
			return t.data.size;
			
		for (Node child: t.children) {
			
			result += updateSizes(child);
		}
		
		t.data.size = result;
		
		return result;
	}
	
	
	/** Return the number of bytes occupied by the filesystem. 
	 * 
	 * @return the number of bytes
	 */
	public int getRootSize() {
		
		if(this.root != null) {
			
			return this.root.data.size;
		} else
			return 0;
	}
	
	
	
	
	@Override
	public String toString() {
		
		updateSizes();
		
		return toStringHelper("",this.root);	
	}

	
	
	
//	String toStringHelper2(String prefix, Node t) {
//		
//		
//		prefix += t.data.name + "/";
//		String result = prefix;
//		
//		if(t.children.isEmpty())
//			return result +"\n";
//		
//		for(Node f: t.children) {
//			
//			result += toStringHelper2(prefix, f);
//			//result += "\n";
//		}
//		
//		return result +"\n";
//	}

	
	
	String toStringHelper(String indent, Node t) {
		
		String result="";
		
		result  = indent + t.data + "\n";

		for(Node f: t.children) {
			
			result += toStringHelper(indent +"\t", f);
			//result += "\n";
		}
		
		return result;
	}


	
	
	
}
	

