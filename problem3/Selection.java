package problem3;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;
import problem1.MergeSort;


public class Selection {

	public static int getPivot (int l, int r) {
		Random rand = new Random();
		
		return l + rand.nextInt(r-l+1);
	}

	static void swap (int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int select (int[] arr, int left, int right, int s) {

		
		int p = getPivot(left, right);
		int lrunner = left+1;
		System.out.println("left: " + left + " right: " + right + " s: " + s + " p: " + p);

		swap (arr, left, p);

		for (int i=left+1; i<=right; i++) {
			if (arr[i] < arr[left]) {
				swap (arr, i, lrunner);
				lrunner++;	
			}
		}
		swap (arr, left, lrunner-1);
		if ( lrunner-1 == s )
			return arr[lrunner-1];
		else if ((lrunner-1) < s) 
			return select (arr, lrunner, right, s);
		else 
			return select (arr, 0, lrunner-2, s);
		

		
	}

	public static int[] getArr() {
		File file = null;
		BufferedReader br = null;
		String line;
		ArrayList <String> list = new ArrayList();

		try {
			file = new File("Problem2/QuickSort.txt");
			br = new BufferedReader(new FileReader(file));
			
			while ( (line = br.readLine()) != null ) {
				list.add(line);

			}
		}
		catch (IOException e) {

			e.printStackTrace();
			return null;
		}

		
		
		int[] arr = new int[list.size()];

		for (int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(list.get(i));
		}

		return arr;
	}


	public static void main (String ... args) {


		int[] arr;
		long start, end;
		int sel = 5000;
		
		arr = getArr();
		start = System.nanoTime();
		System.out.println(select (arr, 0, arr.length-1, sel));
		end = System.nanoTime();
		System.out.println ("Running time: " + (end-start));
		
		arr = getArr();
		start = System.nanoTime();
		MergeSort.mergeSort(arr, new int[arr.length], 0, arr.length-1);
		end = System.nanoTime();
		System.out.println(arr[sel]);
		System.out.println ("Running time: " + (end-start));
		
		

	}

}
