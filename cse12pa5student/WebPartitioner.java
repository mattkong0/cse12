// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class WebPartitioner implements Partitioner{
	
	// URL: https://stackoverflow.com/questions/7198121/quicksort-and-hoare-partition
	// License: https://creativecommons.org/licenses/by-sa/3.0/
	// Author: Ofek Ron
	public void sort(String[] array) {// changed type of array from int to string
        sortHelper(array, 0, array.length - 1);
    }

    public void sortHelper(String[] array, int p, int r) {// changed type of from int to string
        if (p < r) {
            int q = partition(array, p, r);
            sortHelper(array, p, q);
            sortHelper(array, q + 1, r);
        }
    }

    public int partition(String[] array, int p, int r) {// changed type of array from int to string
        String pivot = array[p]; // change type from int to string
        int i = p - 1;
        int j = r + 1;

        while (true) {

            do {
                i++;
            }
            while (array[i].compareTo(pivot) < 0); //use compareTo method for strings

            do {
                j--;
            }
            while (array[j].compareTo(pivot) > 0); //use compareTo method for strings

            if (i < j) {
                swap(array, i, j);
            } else {
                return j;
            }
        }

    }

    public void swap(String[] array, int i, int j) {// changed type of array from int to string
        String temp = array[i];// change type from int to string
        array[i] = array[j];
        array[j] = temp;
    }


}//end class

