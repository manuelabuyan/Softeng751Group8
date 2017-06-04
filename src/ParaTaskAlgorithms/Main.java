package ParaTaskAlgorithms;

public class Main {
	
	public static void main(String[] args){
		ParaTaskSort ptSort = new ParaTaskSort(100, -100, 100);
		long startTime = System.currentTimeMillis();
		ptSort.sequentialSort();
		long endTime = System.currentTimeMillis();
		System.out.println("Sequential sort time: " + (endTime - startTime) );
		
		ptSort.unsortArray();
		
		startTime = System.currentTimeMillis();
		ptSort.parallelSort();
		endTime = System.currentTimeMillis();
		System.out.println("Parallel sort time: " + (endTime - startTime) );
		
	}

}
