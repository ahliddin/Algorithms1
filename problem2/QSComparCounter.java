package Problem2;

import java.io.*;
import java.util.ArrayList;

//*****************   Vogella Quicksort ***************/
class Quicksort  {
  private int[] numbers;
  private int number;

  public void sort(int[] values) {
    // check for empty or null array
    if (values ==null || values.length==0){
      return;
    }
    this.numbers = values;
    number = values.length;
    quicksort(0, number - 1);
  }

  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = numbers[low + (high-low)/2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (numbers[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (numbers[j] > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j) {
    int temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }
} 
//***************** END of   Vogella Quicksort ***************/


//***********       My QuickSort               ****//
enum PivotType {
	LEFT,
	RIGHT,
	MEDIAN
}

class QS {
	static int counter;
	public static void sortAndCount (int[] arr, int left, int right, PivotType pType ) {
		if (left >= right) 
			return;
		
		int p = left;
		int lrunner = left+1;
		counter += (right-left);
		
		switch (pType) {
			case RIGHT: swap (arr, left, right);
						break;
			case MEDIAN: int m = getMedianPivot (arr, left, right);
						swap (arr, left, m);
						break;
		}
			
		for (int i=left+1; i<=right; i++) {
			if (arr[i] < arr[p]) {
				swap (arr, lrunner, i);
				lrunner++;
			}
		}
		swap (arr, lrunner-1, p);
		sortAndCount (arr, left, lrunner-2, pType); // lrunner-2  - because we don't want to include the pivot.
		sortAndCount (arr, lrunner, right, pType);
	
	}
	
	public static int getMedianPivot (int[] arr, int left, int right) {
		int m = left+(right-left)/2;
		
		if (arr[left] <= arr[m]) {
			if (arr[m] <= arr[right]) 
				return m;
			return (arr[left] >= arr[right] ? left : right);
		}
		else {
			if (arr[left] <= arr[right])
				return left;
			return (arr[right] >= arr[m] ? right : m);
		}
	}
	
	public static void swap (int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	
}
public class QSComparCounter {
	public static int[] getArrayFromFile () throws IOException {
		File file = new File ("C:\\Users\\ibrah01\\Dropbox\\Study\\Coursera\\Algorithms1\\Problem2\\QuickSort.txt");
		BufferedReader br = new BufferedReader (new FileReader (file));
		String line;
		int[] arr;
		
		ArrayList<String> al = new ArrayList();
		while ( (line = br.readLine()) != null) {
			al.add(line);
		}
		arr = new int[al.size()];
				
		for (int i=0; i<arr.length; i++) 
			arr[i] = Integer.parseInt (al.get(i));
		
		return arr;
	}
	
	public static void main (String... args) {
		int[] arr; //= {1, 2, 3, 4, 5, 6};
		int[] arr2; // = {1, 2, 3, 4, 5, 6};
		int[] arr3; // = {1, 2, 3, 4, 5, 6};
		Quicksort vogellaQS = new Quicksort();
		
		try {
			arr = getArrayFromFile();
		}
		catch (IOException fnfe) {
			System.out.println(fnfe);
			return;
		}
		long start = System.nanoTime();
		QS.sortAndCount(arr, 0, arr.length-1, PivotType.LEFT);
		long end = System.nanoTime();
		System.out.println("\nLeft pivot. Counter: " + QS.counter);
		System.out.println("Time: " + (end-start));
		
		
		try {
			arr2 = getArrayFromFile();
		}
		catch (IOException fnfe) {
			System.out.println(fnfe);
			return;
		}
		QS.counter = 0;
		start = System.nanoTime();
		QS.sortAndCount(arr2, 0, arr2.length-1, PivotType.RIGHT);
		end = System.nanoTime();
		System.out.println("\nRight pivot. Counter: " + QS.counter);
		System.out.println("Time: " + (end-start));
		
		
		
		
		try  {
			arr3 = getArrayFromFile();
		}
		catch (IOException fnfe) {
			System.out.println(fnfe);
			return;
		}
		QS.counter = 0;
		
		start = System.nanoTime();
		QS.sortAndCount(arr3, 0, arr3.length-1, PivotType.MEDIAN);
		end = System.nanoTime();
		System.out.println("\nMedian pivot. Counter: " + QS.counter);
		System.out.println("Time: " + (end-start));
		
		//********Vogella quicksort *******///
		try {
			arr = getArrayFromFile();
		}
		catch (IOException fnfe) {
			System.out.println(fnfe);
			return;
		}
		start = System.nanoTime();
		vogellaQS.sort(arr);
		end = System.nanoTime();
		System.out.println("\nVogella Quicksort medium pivot.");
		System.out.println("Time: " + (end-start));
		
		
	}
}