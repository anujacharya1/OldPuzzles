package com.anuj;

public class Sorting {
	public static void main(String a[]) {
		int i;
		int arr[] = { -1, 5, -2, 3, 9, -10, -5, 3 };
		for (i = arr.length; i > 1; i--) {
			heapSort(arr, i - 1);
		}
		System.out.println("\n  After Shifting Array is\n-----------------------\n");
		for (i = 0; i < arr.length; i++)
			System.out.print(" " + arr[i]);
	}

	public static void heapSort(int array[], int arr_ubound) {
		int i, o;
		int lChild, rChild, mChild, root, temp;
		root = (arr_ubound - 1) / 2;

		for (o = root; o >= 0; o--) {
			for (i = root; i >= 0; i--) {
				lChild = (2 * i) + 1;
				rChild = (2 * i) + 2;
				if ((lChild <= arr_ubound) && (rChild <= arr_ubound)) {
					if (array[rChild] >= array[lChild])
						mChild = rChild;
					else
						mChild = lChild;
				} else {
					if (rChild > arr_ubound)
						mChild = lChild;
					else
						mChild = rChild;
				}

				if (array[i] < array[mChild]) {
					temp = array[i];
					array[i] = array[mChild];
					array[mChild] = temp;
				}
			}
		}
		temp = array[0];
		array[0] = array[arr_ubound];
		array[arr_ubound] = temp;
		return;
	}
}