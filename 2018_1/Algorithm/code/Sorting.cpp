#include <iostream>

#define SWAP(a,b){int temp; temp = a; a = b; b = temp;}
#define MAX 100

void selectionSort(int A[], int n) {
	for (int i = n-1; i > 0; i--) {
		int max = i;
		for (int k = 0; k < i; k++) {
			if (A[k] > A[max]) { SWAP(A[k], A[max]); }
		}
	}
}

void bubbleSort(int A[], int n) {
	for (int i = n - 1 ; i >= 0; i--) {
		for (int k = 0; k < i; k++) {
			if (A[k] > A[k + 1]) SWAP(A[k], A[k + 1]);
		}
	}
}

void insertionSort(int A[], int n) {
	for (int i = 1; i < n; i++) {
		int loc = i - 1;
		int newItem = A[i];
		while (loc >= 0 && A[loc] > newItem) {
			A[loc + 1] = A[loc];
			loc--;
		}
		A[loc+1] = newItem;

	}
}

void merge(int A[],int p , int q, int r) {
	int i=p, j = q+1, t=0;
	int temp[MAX];
	while (i <= q && j <= r) {
		if (A[i] < A[j]) temp[t++] = A[i++];
		else temp[t++] = A[j++];
	}
	while (i <= q) temp[t++] = A[i++];
	while (j <= r) temp[t++] = A[j++];

	i = p; t = 0;
	while (i <= r) {
		A[i++] = temp[t++];
	}
}

void mergeSort(int A[], int p, int r) {
	int q;
	if (p < r) {
		q = (p + r) / 2;
		mergeSort(A, p, q);
		mergeSort(A, q + 1, r);
		merge(A, p, q, r);
	}
}

int partition(int A[], int p, int r) {
	int i, j;
	int pivot = A[r];
	i = p-1; j = p;

	while (j < r) {
		if (A[j] <= pivot) {
			i++;
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
		printf("i => %d / j => %d / element => %d\n", i, j, A[j]);
		j++;
	}

	int temp = A[++i];
	A[i] = A[r];
	A[r] = temp;

	return i;
}

void quickSort(int A[],int p,int r) {
	int q=0;
	if (p < r) {
		q = partition(A, p, r);
		quickSort(A, p, q - 1);
		quickSort(A, q + 1, r);
	}
	
}

void heapify(int A[] , int k,int n) {
	int smaller = 0;
	int left = 2*k+1;
	int right = 2*k + 2;
	if (right <= n) {
		if (A[left] < A[right]) smaller = left;
		else smaller = right;
	}
	else if (left <= n) smaller = left;
	else return;

	if (A[smaller] < A[k]) {
		int temp = A[smaller];
		A[smaller] = A[k];
		A[k] = temp;
		heapify(A, smaller, n);
	}	
}
void buildHeap(int A[], int n) {
	for (int i = n / 2; i >= 0; i--) {
		heapify(A, i , n);
	}
}
void heapSort(int A[], int n) {
	buildHeap(A, n);
	for (int i = n; i > 0; i--) {
		int temp = A[0];
		A[0] = A[i];
		A[i] = temp;
		heapify(A, 0, i-1);
	}
}

void main() {
	int A[] = { 51,23,93,25,74,12,84,92,32 , 67 };
	int n = 10;
	for (int el : A) {
		printf("%d ", el);
	}
	printf("\n");

	//selectionSort(A, n);
	//bubbleSort(A, n);
	//insertionSort(A, n);
	//mergeSort(A, 0, n-1 );
	//quickSort(A, 0, n - 1);
	heapSort(A, n - 1);


	for (int el : A) {
		printf("%d ", el);
	}
	printf("\n");
	
}
