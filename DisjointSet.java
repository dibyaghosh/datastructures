import java.util.Random;

public class DisjointSet{

	int[] parents;
	boolean naive;

	public DisjointSet(int n){
		this(n,false);
	}

	public DisjointSet(int n, boolean naive){
		this.naive = naive;
		parents = new int[n];
		for(int i = 0; i < n; i++){
				parents[i] = i;
		}
	}

	public void join(int i, int j){
		if (naive){
			
			joinNaive(i,j);
		}
		else {
			joinPC(i,0,j,0);
		}
	}

	private boolean joinNaive(int i, int j){
		if(parents[i] != i) return joinNaive(parents[i],j);
		if(parents[j] != j) return joinNaive(i,parents[j]);
		parents[i] = j;
		return true;
	}

	private int joinPC(int i, int icount, int j, int jcount){
		if (parents[i] != i) {
			parents[i] = joinPC(parents[i],icount+1,j,jcount);
			return parents[i];
		}
		if (parents[j] != j) {
			parents[j] = joinPC(i,icount,parents[j],jcount+1);
			return parents[j];
		}
		if (icount > jcount){
			parents[j] = i;
			return i;
		}
		else {
			parents[i] = j;
			return j;
		}
	}

	public int height(int i) {
		if(parents[i]==i) return 0;
		return 1 + height(parents[i]);
	}

	public boolean connected(int i, int j){
		if(parents[i] != i) return connected(parents[i],j);
		if(parents[j] != j) return connected(i,parents[j]);
		return i == j;
	}

	public static void timeJoin(DisjointSet d,int n){
		for(int i = 1; i < n; i++){
			d.join(0,i);
		} 
	}

	public static void timeConnection(DisjointSet d, int n){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				d.connected(i,j);
			}	
		} 
	}

	public static void main(String[] args){
		DisjointSet dsPathCompressed = new DisjointSet(1000);
		DisjointSet dsNaive = new DisjointSet(1000,true);
		long startTime;
		double timeTaken;

		// INSERTING INTO PATH COMPRESSED
		startTime = System.nanoTime();
		timeJoin(dsPathCompressed,1000);
		timeTaken = (System.nanoTime()-startTime)/1000000;
		System.out.println("(Path Compressed) Making Connections: "+ timeTaken + "ms");

		// INSERTING INTO NAIVE
		startTime = System.nanoTime();
		timeJoin(dsNaive,1000);
		timeTaken = (System.nanoTime()-startTime)/1000000;
		System.out.println("(Naive Implement) Making Connections: "+ timeTaken + "ms");


		// Checking Connections for PATH COMPRESSED
		startTime = System.nanoTime();
		timeConnection(dsPathCompressed,1000);
		timeTaken = (System.nanoTime()-startTime)/1000000;
		System.out.println("(Path Compressed) Finding connections: "+ timeTaken + "ms");


		// Checking  for NAIVE
		startTime = System.nanoTime();
		timeConnection(dsNaive,1000);
		timeTaken = (System.nanoTime()-startTime)/1000000;
		System.out.println("(Naive Implement) Finding connections: "+ timeTaken + "ms");


	}
}