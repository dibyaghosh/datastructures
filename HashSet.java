import java.util.ArrayList;

public class HashSet<Key> {
	private static final defaultSize = 100;
	private ArrayList<Key>[] table;

	public HashSet(){
		table = new ArrayList<Key>[defaultSize];
	}

	public HashSet(int size){
		table = new ArrayList<Key>[size];
	}

	public void add(Key k){
		int hashCode = hash(k);
		if(table[hashCode] == null){
			table[hashCode] = new ArrayList<Key>();
		}
		table[hashCode].add(k);
	}

	public boolean contains(Key k){
		int hashCode = hash(k);
		return table[hashCode] != null && 
				table[hashCode].contains(k);

	}

	private int hash(Key k){
		return (k.hashCode() & 0x7fffffff) % table.length;
	}

}