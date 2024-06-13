public class Main{
	public static void main(String[] args){
		
		MinHeap heap = new MinHeap(10);
		
		heap.insert(0).insert(1).insert(5).insert(7).insert(11).insert(10).insert(2);
		heap.printAsBinaryTree();
	}
}
