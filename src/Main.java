/**
 * Author: Jack Robbins
 * Simple driver class for the heap visualization
 */
public class Main{

	public static void main(String[] args){
		//Create and initialize the MinHeap	
		MinHeap heap = new MinHeap(10);
		
		heap.insert(8)
			.insert(1)
			.insert(5)
			.insert(7)
			.insert(11)
			.insert(10)
			.insert(2)
			.insert(4)
			.insert(0)
			.insert(7)
			.insert(9)
			.insert(20)
			.insert(15)
			.insert(21)
			.insert(-1);
		//Hand off to printing method
		heap.printAsBinaryTree();
	}
}
