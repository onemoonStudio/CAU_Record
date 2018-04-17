#include <iostream>
// selectionSort , bubbleSort , insertionSort

#define SWAP(a,b){int temp; temp = a; a = b; b = temp;}

void selectionSort(int A[], int n) {
	int max;
	for (int i = n-1; i > 0; i--) {
		// n-1 => 1
		max = 0;
		for (int k = 0; k < i; k++) {
			if (A[k] >= A[max]) max = k;
		}
		SWAP(A[max], A[i]);
	}
}

void bubbleSort(int A[], int n) {
	for (int i = n-1; i > 0; i--) {
		// n-1 ¹ø ½ÇÇà
		for (int k = 0; k < i; k++) {
			if (A[k] >= A[k + 1]) SWAP(A[k], A[k + 1]);
		}
	}
}

void insertionSort(int A[], int n) {
	for (int i = 1; i < n; i++) {
		int loc = i - 1;
		int newitem = A[i];
		while (loc >= 0 && A[loc] > newitem) {
			A[loc + 1] = A[loc];
			loc--;
		}
		A[loc+1] = newitem;
	}
}

void mergeSort(int A[], int n) {

}

int main() {
	int Arr[] = { 6,9,2,14,5,42,7,0 }; // n = 8
	for (int p = 0; p < 8; p++) {
		printf("%d ", Arr[p]);
	}
	printf("\n");
	//selectionSort(Arr, 8);
	//bubbleSort(Arr, 8);
	insertionSort(Arr, 8);
	for (int p = 0; p < 8; p++) {
		printf("%d ", Arr[p]);
	}
	printf("\n");
	
}