import java.util.Arrays;

public class MinHeap{
	private int[] heap;
	private int size;
	private int capacity;

	public MinHeap(int capacity){
		this.capacity = capacity;
		this.heap = new int[capacity];
		this.size = 0;
	}

	private int parent(int i){
		return (i - 1) / 2;
	}

	private int leftChild(int i){
		return 2 * i + 1;
	}

	private int rightChild(int i){
		return 2 * i + 2;
	}

	private void swap(int i, int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	private void resize(){
		capacity *= 2;
		heap = Arrays.copyOf(heap, capacity);
	}

	public MinHeap insert(int item){
		if(size >= capacity){
			resize();
		}

		heap[size] = item;
		int insertedIndex = size;
		size++;

		while(insertedIndex > 0 && heap[insertedIndex] < heap[parent(insertedIndex)]){
			swap(insertedIndex, parent(insertedIndex));

			insertedIndex = parent(insertedIndex);
		}

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
