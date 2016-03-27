import java.util.ArrayList;

public class PriorityQueue<Key extends Comparable<Key>> {
	private ArrayList<Key> heap;
	boolean direction;

	private static final boolean MINIMUM = true;
	private static final boolean MAXIMUM = false;

	PriorityQueue(){
		heap = new ArrayList<Key>();
		direction = MINIMUM;
	}

	public void add(Key k){
		heap.add(k);
		readjustUp(heap.size()-1);
	}

	private void readjustUp(int location){
		if(location == 0){
			return;
		}
		int parent = (location-1) / 2;
		int cmp = heap.get(location).compareTo(heap.get(parent));
		if((direction == MINIMUM && cmp < 0) || (direction == MAXIMUM && cmp > 0)){
			swap(parent,location);
			readjustUp(parent);
		} 	
	}

	private void readjustDown(int location){
		int leftIndex = location*2 + 1;
		int rightIndex = location*2 + 2;
		if(leftIndex >= size() && rightIndex >= size()) return;
		int better = betterIndex(leftIndex,rightIndex);
		int cmp = heap.get(location).compareTo(heap.get(better));
		if (direction == MINIMUM && cmp > 0){
			swap(location,better);
			readjustDown(better);
		} else if (direction == MAXIMUM && cmp < 0){
			swap(location,better);
			readjustDown(better);
		}
	}

	private void swap(int i, int j){
		Key temp = heap.get(i);
		heap.set(i,heap.get(j));
		heap.set(j,temp);
	}

	private int betterIndex(int i, int j){
		if(i >= size()) return j;
		if(j >= size()) return i;
		int cmp = heap.get(i).compareTo(heap.get(j));
		if(direction == MINIMUM){
			return (cmp > 0)?j:i;
		} else {
			return (cmp > 0)?i:j;
		}	
	}
	public Key pop(){
		if(size() == 1){
			return heap.remove(0);
		}
		Key top = heap.get(0);
		heap.set(0,heap.remove(heap.size()-1));
		readjustDown(0);
		return top;
	}

	public int size(){
		return heap.size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public static void main(String[] args){
		PriorityQueue<Integer> pq = new PriorityQueue();
		for(int i = 0; i < 10; i++){
			pq.add(i);
		}
		while(!pq.isEmpty()){
			System.out.println(pq.pop());
		}

	}
}