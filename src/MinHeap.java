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

	public void printAsBinaryTree(){
		final int LINE_WIDTH = 70;
		int[] printing = new int[this.size];
		int current_level = 0;
		int x = 1;	

		printing[0] = this.heap[0];
		for(int i = 0, j = 1; i < size; i++, j++){
			int position = (int)(printing[parent(i)] + ((i % 2) == 0 ? -1 : 1)
							* (LINE_WIDTH * Math.pow(2, current_level + 1) + 1));
			
			for(int k = 0; k < position - x; k++){
				System.out.printf("%c", (i==0 || i % 2 ==1) ? ' ' : '-');
			}

			System.out.printf("%d", heap[i]);
			printing[i] = x = position + 1;

			if(j == Math.pow(2, current_level)){
				System.out.println();
				current_level++;
				x = 1;
				j = 0;
			}
		}			
	}
}
