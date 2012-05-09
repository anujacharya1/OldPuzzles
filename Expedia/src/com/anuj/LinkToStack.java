package com.anuj;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class LinkToStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LinkedList<String> ll = new LinkedList(); 
		// add elements to the linked list 
		ll.add("F"); 
		ll.add("B"); 
		ll.add("D"); 
		ll.add("E"); 
		ll.add("C"); 
		ll.add("A2"); 
		ListIterator itr = ll.listIterator();
		Stack stack = new Stack<String>(); 
	    while(itr.hasNext())
	    {	
	    	stack.push(itr.next());
	    
	    }
	    while (!stack.empty()){
	    	  System.out.print(stack.pop() + "  ");
	    	  }

	}

}
