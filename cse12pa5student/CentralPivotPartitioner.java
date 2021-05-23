// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner{
	
	// sort through the entire array
	public void sort (String[] array) {
		qsort(array, 0, array.length);
	}
	
	// choose a pivot and partition around that pivot
	public void qsort(String[] array, int low, int high) {
		if (high - low <= 1) { 
			return; 
		}
		else if (low > high) {
			return;
		}
		int splitAt = partition(array, low, high);
		qsort(array, low, splitAt);
		qsort(array, splitAt + 1, high);
	}
	
	public int partition(String[] array, int low, int high) {
		if(low == high){
			return low;
	    }
	    int pivotIndex = high - 1;
	    String pivot = array[pivotIndex];
	    int smallerBefore = low;
		int largerAfter = high - 2;
		while(largerAfter >= smallerBefore) {
			if(array[smallerBefore].compareTo(pivot) > 0) {
				swap(array, smallerBefore, largerAfter);
				largerAfter--;
			}
			else {
				smallerBefore++;
			}
		}
		swap(array, smallerBefore, pivotIndex);
		return smallerBefore;
	}
		
		// helper method to swap elements in the array when sorting
	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
}
