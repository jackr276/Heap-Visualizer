/**
 * Author: Jack Robbins
 * This class represents a minHeap as an array, and is mainly here to serve
 * an associated print method
 */

import java.util.Arrays;


public class MinHeap{
	//Our heap is stored as an array
	private int[] heap;
	private int size;
	private int capacity;

	/**
	 * Initialize with an initial capacity, and initial size is of course 0
	 */
	public MinHeap(int capacity){
		this.capacity = capacity;
		this.heap = new int[capacity];
		this.size = 0;
	}


	/**
	 * The parent index will always be half of the child index
	 */
	private int parent(int i){
		return (i - 1) / 2;
	}


	/**
	 * Likewise, the left child index is always twice the parent + 1
	 */
	private int leftChild(int i){
		return 2 * i + 1;
	}


	/**
	 * Likewise, the right child index is always twice the parent + 2
	 */
	private int rightChild(int i){
		return 2 * i + 2;
	}


	/**
	 * Basic helper swap method
	 */
	private void swap(int i, int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}


	/**
	 * Provides our auto-resize capability
	 */
	private void resize(){
		//Always double the capacity on resize
		capacity *= 2;
		heap = Arrays.copyOf(heap, capacity);
	}


	/**
	 * Insert method for our minheap
	 */
	public MinHeap insert(int item){
		//Automatically call resize()
		if(size >= capacity){
			resize();
		}

		//Insert item into heap
		heap[size] = item;
		//Keep track of the insertedIndex
		int insertedIndex = size;
		//We added one more, so increase this
		size++;

		//So long as the index is more than 0, and we are less than the parent...
		while(insertedIndex > 0 && heap[insertedIndex] < heap[parent(insertedIndex)]){
			//Swap the two values
			swap(insertedIndex, parent(insertedIndex));

			//Update the value
			insertedIndex = parent(insertedIndex);
		}

		//Convenience enhancement -- allow for chaining method calls
		return this;
	}

	/**
	 * As part of a heap class, print a nice visualization of a heap to the
	 * console
	 */
	public void printAsBinaryTree(){
		//For the user, print out the array to begin with
		System.out.println("Heap: " + Arrays.toString(heap) + "\n");
		//calculate a given line width	
		final int WIDTH = this.size * 5;
		//Define an array to hold everything that we are printing
		int[] printing = new int[this.size];

		//Current level we are on
		int current_level = 0;
		//An adjustment factor of sorts
		int x = 1;	

		//Set the "root" effectively
		printing[0] = this.heap[0];

		//Go through the entire array
		for(int i = 0, j = 1; i < size; i++, j++){
			//Calculate the appropriate number of '-' or ' ' to print to the console
			//If we have an odd i, go back(-1) and if we have an even i go forward(1)
			int position = (int)(printing[parent(i)] + ((i % 2) == 1 ? -1 : 1)
							* (WIDTH / Math.pow(2, current_level + 1) + 1));
		
			//Print the appropriate number of '-' or ' '
			for(int k = 0; k < position - x; k++){
				//If we land on an odd or 0 index, print out spaces, dashes otherwise
				System.out.printf("%c", (i == 0 || i % 2 == 1) ? ' ' : '-');
			}

			//Print the actual number out
			System.out.printf("%d", heap[i]);
			//Put i back in as the parent
			printing[i] = x = position + 1;

			//We can tell if we need to "drill down" a level
			if(j == Math.pow(2, current_level)){
				//Print a newline, go down a level, and reset our variables
				System.out.println();
				current_level++;
				x = 1;
				j = 0;
			}
		}			
	}
}
