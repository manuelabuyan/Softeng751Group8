package ParaTaskSort;

public class Main {
	
	public static void main(String[] args){
		ParaTaskSort ptSort = new ParaTaskSort(100, -100, 100);
		
		
		ptSort.sequentialSort();
		
		ptSort.unsortArray();
		
		ptSort.parallelSort();

	}

}
