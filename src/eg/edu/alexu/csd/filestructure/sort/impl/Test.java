package eg.edu.alexu.csd.filestructure.sort.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;

public class Test {

	public static void main(String[] args) {
		  IHeap<Integer> heap = new Heap<>();
		    try
		    {
		      PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
		      Random r = new Random();
		      Random pick = new Random();
		      
		      for (int i = 0; i < 1000000; i++) {
		        int numToPick = pick.nextInt(Integer.MAX_VALUE);
		        int val = r.nextInt(Integer.MAX_VALUE);
		        
		        if (numToPick % 4 == 0) {
		          if (!pq.isEmpty()) {
		        	  System.out.println(pq.poll());
		        	  System.out.println( heap.extract());
		          
		          } else {
		        	  System.out.println(heap.size());
		          }
		          
		           
		        } else {
		          pq.add(Integer.valueOf(val));
		          heap.insert(Integer.valueOf(val));
		        }
		      }
		    } catch (Throwable e) {
		      e.printStackTrace();
		    }
		  
	}

}
