// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner{
		
	// sort through the entire array
	public void sort (String[] array) {
		qsort(array, 0, array.length-1);
	}
		
	// choose a pivot
	public void qsort(String[] array, int low, int high) {
		if (high - low <= 1) { 
			return; 
		}
		else if (low > high) {
			return;
		}
		int splitAt = partition(array, low, high);
		qsort(array, splitAt, splitAt);
		qsort(array, splitAt + 1, high);
	}
		
	public int partition(String[] array, int low, int high) {
		if(low == high){
			return low;
	    }
	    int pivotIndex = low;
	    String pivot = array[pivotIndex];
	    int index = low + 1;
		for (int j = low + 1; j <= high; j++) {
			if (array[j].compareTo(pivot) < 0) {
				swap(array, Integer.parseInt(array[index]), Integer.parseInt(array[j]));
				index++;
			}
		}
		swap(array, Integer.parseInt(array[low]), Integer.parseInt(array[index - 1]));
		return index - 1;
	}
	
	// helper method to swap elements in the array when sorting
	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
}
