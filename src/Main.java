public class Main{
	public static void main(String[] args){
		
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
		heap.printAsBinaryTree();
	}
}
