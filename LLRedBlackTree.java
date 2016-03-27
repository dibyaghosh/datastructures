public class LLRedBlackTree<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;


	private class Node {
		Key key;
		Value value;
		Node left,right;
		boolean color;

		Node(Key k, Value v){
			key = k;
			value = v;
			color = RED;
		}

		public String toString(){
			return "(" + (color?"RED":"BLACK") + " " + key + " " + value + ")";
		}

	}

	private Node root;

	public void add(Key k, Value v){
		if (root == null) {
			root = new Node(k,v);
			root.color = BLACK;
		} else {
			addInternal(k,v, root);
		}
		root = fix(root);
	}

	private void addInternal(Key k, Value v, Node n){
		int cmp = k.compareTo(n.key);
		if (cmp < 0){

			if (n.left == null){
				n.left = new Node(k,v);
			}
			else {
				addInternal(k,v,n.left);
			}
		n.left = fix(n.left);

		} else if (cmp > 0){
			if (n.right == null){
			 	n.right = new Node(k,v);
			 }
			else {
				addInternal(k,v,n.right);
			}
			n.right = fix(n.right);
		}
	}

	public void printBFS(){
		int depth = 0;
		while(iterativeDeepening(root,depth++)){
			System.out.println();
		}
		System.out.println();
	}

	private boolean iterativeDeepening(Node n, int depth){
		if(n == null) return false;
		if(depth > 0){
			return iterativeDeepening(n.left,depth-1) || iterativeDeepening(n.right,depth-1);
		}
		System.out.print(n.toString() + " ");
		return n.left != null || n.right != null;
	}

	private Node fix(Node n){
		if(n == null){
			return n;
		}

		if(n.right != null && n.right.color == RED){
			n = rotateLeft(n);
		}
		if(n.left != null && n.left.color == RED){
			if (n.left.left != null && n.left.left.color == RED){
				n = rotateRight(n);
				n.left.color = BLACK;
				n.right.color = BLACK;
				n.color = RED;
			}
		}
		return n;
	}

	 Node rotateRight(Node n){
		Node newRoot = n.left;
		n.left = newRoot.right;
		newRoot.right = n;
		boolean tempColor = n.color;
		n.color = newRoot.color;
		newRoot.color = tempColor;
		return newRoot;
	}

	Node rotateLeft(Node n){
		Node newRoot = n.right;
		n.right = newRoot.left;
		newRoot.left = n;
		boolean tempColor = n.color;
		n.color = newRoot.color;
		newRoot.color = tempColor;
		return newRoot;
	}

	public Node getNode(Key k){
		return getNodeHelper(k,root);
	}

	private Node getNodeHelper(Key k, Node n){
		if (n == null){
			return null;
		}
		int cmp = k.compareTo(n.key);
		if(cmp < 0){
			return getNodeHelper(k,n.left);
		} else if (cmp == 0){
			return n;
		} else {
			return getNodeHelper(k,n.right);
		}
	}

	public int height(Node n){
		if(n == null){
			return 0;
		}
		return 1 + Math.max(height(n.left),height(n.right));
	}

	public int height(){
		return height(root);
	}
	public static void main(String[] args){
		LLRedBlackTree<Integer,Integer> tree = new LLRedBlackTree<Integer,Integer>();
		for(int i = 0; i < 10000000; i ++){
			tree.add(i,10);
		}
		//tree.add("e",10);
		System.out.println(tree.height());

	}
}