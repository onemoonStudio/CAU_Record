#include <iostream>

#define SWAP(a,b){int temp = a; a = b; b = temp;}

int binarySearch(int A[],int n, int key) {
	int low, mid, high;
	low = 0; high = n - 1;

	while (low <= high) {
		mid = (low + high) / 2;
		if (A[mid] == key) return mid;
		else if (A[mid] > key) high = mid - 1;
		else low = mid + 1;
	}
	return -1;
}

void fibonacci(int num , int A[]) {
	A[0] = 1; A[1] = 1;
	for (int i = 2; i < num; i++) {
		A[i] = A[i - 1] + A[i - 2];
	}
	printf("%dth fibonacci number is %d\n", num, A[num - 1]);
}

void mulMat(int A[][4], int B[][4], int C[][4]) {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			int sum = 0;
			for (int p = 0; p < 4; p++) {
				sum += A[i][p] * B[p][j];
			}
			C[i][j] = sum;
		}
	}
	
	for (int n = 0; n < 4; n++) {
		for (int m = 0; m < 4; m++) {
			printf("%d ", C[n][m]);
		}
		printf("\n");
	}

}

void exchangeSort(int n, int A[]) {
	printf("before change => [ ");
	for (int k = 0; k < n; k++) {
		printf("%d ", A[k]);
	}
	printf("]\n");


	for (int i = 0; i < n; i++) {
		for (int k = 0; k < n-i-1; k++) {
			if (A[k + 1] < A[k]) SWAP(A[k + 1], A[k]);
		}
	}


	printf("After change => [ ");
	for (int k = 0; k < n; k++) {
		printf("%d ", A[k]);
	}
	printf("]\n");
}

int main() {
	// @@@@@@@@@@@@@@
	// Binary Search 
	// @@@@@@@@@@@@@@
	int A[8] = { 1,4,7,8,10,12,16,22 };
	printf("%d", binarySearch(A, 8, 8));

	// @@@@@@@@@@@@@@
	// fibonacci Start 
	// @@@@@@@@@@@@@@

	//int A[1000] = { 0, };
	//fibonacci(10, A);
	

	// @@@@@@@@@@@@@@
	// multiply Matrix Start 
	// @@@@@@@@@@@@@@

	/*int A[4][4] = { 
	{ 2,0,0,0 },
	{ 0,2,0,0 },
	{ 0,0,2,0 },
	{ 0,0,0,2 } };
	int B[4][4] = {
		1,2,3,4,
		2,1,3,4,
		2,3,1,4,
		2,3,4,1 };
	int C[4][4] = { 0, };

	mulMat(A, B, C);*/

	// @@@@@@@@@@@@@@
	// exchangeSort Start
	// @@@@@@@@@@@@@@

	/*int A[8] = { 10,5,3,1,6,8,4,2 };
	exchangeSort(8, A);*/
	

	return 0;
}