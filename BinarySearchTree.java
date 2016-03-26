import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {
	
	private class Node {
		T entry;
		Node left;
		Node right;
		Node(T e){
			entry = e;
		}

		boolean isLeaf(){
			return left == null && right == null;
		}

		void insert(T e){
			if(e.compareTo(entry) > 0){
				if (right == null){
					right = new Node(e);
				} else {
					right.insert(e);
				}
			} else {
				if (left == null){
					left = new Node(e);
				} else {
					left.insert(e);
				}
			}
		}
	}


	Node root;

	public BinarySearchTree() {
		root = null;
	}

	public void add(T element){
		if (root == null){
			root = new Node(element);
			return;
		}
		root.insert(element);
	}

	public void clear(){
		root = null;
	}

	public ArrayList<T> toArrayList(){
		ArrayList<T> arrayList = new ArrayList<T>();
		addToArrayList(arrayList,root);
		return arrayList;
	}

	private void addToArrayList(ArrayList<T> t, Node node){
		if (node == null) return;
		addToArrayList(t, node.left);
		t.add(node.entry);
		addToArrayList(t, node.right);
	}

	public static void main(String[] args){
		BinarySearchTree bst = new BinarySearchTree<Integer>();
		bst.add(10);
		bst.add(5);
		System.out.println(bst.toArrayList());
	}

}