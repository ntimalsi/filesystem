package edu.olemiss.csci211.Assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

//import edu.olemiss.csci211.Assignment2.Filesystem.File;

public class FilesystemTest {

	@Test
	public void constructor1() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
		
		
	}

	
	@Test
	public void addChild1() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
	}
	
	@Test
	public void updateSizes1() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
		f.addChild("/", new Filesystem.File("bar", 21));
		
		f.updateSizes();
		
		assertEquals(31, f.getRootSize());
		
		
	}

	@Test
	public void updateSizes2() {
		
		Filesystem f = new Filesystem("/");
		
		f.addChild("/", new Filesystem.File("foo", 10));
		
		f.addChild("/", new Filesystem.File("bar", 21));
		
		f.addChild("bar", new Filesystem.File("baz", 47));
		f.addChild("bar", new Filesystem.File("boom", 44));
		
		f.updateSizes();
		
		assertEquals(101, f.getRootSize());
	}

	@Test 
	public void toString1() {
		
		Filesystem f = new Filesystem("/");
		f.addChild("/", new Filesystem.File("foo", 10));
		f.addChild("/", new Filesystem.File("boom", 10));
		f.addChild("foo", new Filesystem.File("bar", 10));
		f.addChild("foo", new Filesystem.File("baz", 10));

		System.out.println(f);
	}

	
	
}
