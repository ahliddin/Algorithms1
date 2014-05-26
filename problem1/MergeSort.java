package problem1;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class MergeSort {
	static long inversions;
	public static void mergeSort (int [] arr, int [] aux, int left, int right) {
		
		if (left == right) 
			return;
			
		int mid = (left + right)/2;
	
		mergeSort(arr, aux, left, mid);
		mergeSort(arr, aux, mid+1, right);
		merge (arr, aux, left, right);
		
		for (int i=left; i<=right; i++) {
			arr[i] = aux[i];
		}	
	}
	
	public static void merge (int [] arr, int[] aux, int left, int right) {
		int arr1Left = left;
		int arr1Right = (left+right)/2;
		
		int arr2Left = (left+right)/2 + 1;
		int arr2Right = right;
		
		int auxIndex = left;
		
		while (arr1Left <= arr1Right && arr2Left <=arr2Right) {
			if (arr[arr1Left] <= arr[arr2Left]) {
				aux[auxIndex++]  = arr[arr1Left++];
			}
			else {
				aux[auxIndex++] = arr[arr2Left++];
				inversions += (1+arr1Right-arr1Left);
			}
		}
		while (arr1Left <= arr1Right) 
			aux[auxIndex++] = arr[arr1Left++];
		
		while (arr2Left <= arr2Right) 
			aux[auxIndex++] = arr[arr2Left++];
		
	}
	
	public static void writeValuesToFile (File fout, int[] arr) {
		try {
			PrintWriter writer = new PrintWriter(fout);
			for (int i=0; i<arr.length; i++) {
				writer.println(arr[i]);
			}
			writer.close();
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	//	catch (IOException ioe) {
	//		ioe.printStackTrace();
	//	}
	
	}
	
	
	public static void main (String [] args) {
		File file = new File("IntegerArray.txt");
		File fout = new File("output.txt");
		
		
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		int [] arr;
		String line;
		
		try {
			BufferedReader br = new BufferedReader (new FileReader(file));
			while ( (line=br.readLine()) != null ) {
				try {
					arrList.add(Integer.valueOf(line));
				}
				catch (NumberFormatException nfe) {
					System.out.println ("Wrong number, reason: " + nfe.getMessage());
					return;
				}
			}
			
		}
		catch (FileNotFoundException fnfe) {
			System.out.println ("fnfe: " + fnfe.getMessage());
			return;
		}
		catch (IOException ioe) {
			System.out.println ("ioe: " + ioe.getMessage());
			return;
		}
		
		
		System.out.println ("Total amount of numbers added: " + arrList.size());
		
		arr = new int[arrList.size()];
		for (int i=0; i<arr.length; i++) {
			arr[i] = arrList.get(i).intValue();
		}
		
		
		
		//int[] arr = {5, 2, 6, 10, 23, 13, -1000};
		mergeSort (arr, new int[arr.length], 0, arr.length-1);
		
		writeValuesToFile (fout, arr);
		
		System.out.println ("\ninversions: " + inversions );
		
		inversions = 0;
		
		mergeSort (arr, new int[arr.length], 0, arr.length-1);
		System.out.println ("\ninversions: " + inversions );
		
	}
	

}
