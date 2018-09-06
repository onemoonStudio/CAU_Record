# 모두 합쳐놓음

## ch 3

```c
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
```

## Sorting

```c

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

```

## ch5 DP

```c

#define MAX 100
#define INF 1000

int findMin(int a, int b) {
	if (a > b) return b;
	// i think here is hint of shortest path else adjacent
	else return a;
}

// binomial efficient
// version 1 recursive
int bin(int n , int k) {
	if (k == 0 || n == k) {
		return 1;
	}
	else {
		return bin(n - 1, k - 1) + bin(n - 1, k);
	}
}

//version 2 dp
int bin2(int n, int k ,int A[][MAX]) {
	A[0][0] = 1;
	for (int i = 1; i <= n; i++) {
		A[i][0] = 1;
		for (int j = 1; j <= k; j++) {
			
			if (i == j) { A[i][j] = 1; }
			else A[i][j] = A[i - 1][j - 1] + A[i - 1][j];
			printf("i : %d , j : %d , val : %d\n", i, j, A[i][j]);
		}
	}
	return A[n][k];
}

// floyd function
void floyd(int n ,const int w[][MAX] , int d[][MAX] ) {
	for (int k = 0; k <= n; k++) {
		// why k <= ? 
		// because it start adjacent matrix to last vertax
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// start matrix
				if (i == j) d[i][j] = 0; // from to from -> Nop!
				else {
					if (k == 0) d[i][j] = w[i][j];
					else {
						d[i][j] = findMin(d[i][j], d[i][k - 1] + d[k - 1][j]);	
					}
				}
				// end matrix
			}
		}

		// check d
		/*for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				printf("%d ", d[i][j]);
			}
			printf("\n");
		}
		printf("\n %d \n", k);*/
	}
}

//if you want shortest path 
// step 1 floyd function 
// step 2 check shortest path - > new array path[][]
// step 3 recursive path function start -> p[][] and p[][] -> to

void pathMatrix(int n, const int w[][MAX], int d[][MAX], int p[][MAX]) {
	for (int k = 0; k <= n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) d[i][j] = 0;
				else {
					if (k == 0) d[i][j] = w[i][j];
					else if (d[i][j] > d[i][k - 1] + d[k - 1][j]) {
						p[i][j] = k;
						d[i][j] = d[i][k - 1] + d[k - 1][j];
					}
				}
			}
		}
	}
}

void shortestPath(int from, int to, int path[][MAX]) {
	int checkPath = path[from - 1][to - 1];
	if (checkPath != 0) {
		shortestPath(from, checkPath, path);
		printf("%d ", checkPath);
		shortestPath(checkPath, to, path);
	}
}

void printPath(int from, int to, int path[][MAX]) {
	printf("path start from %d ",from);
	shortestPath(from, to, path);
	printf(" and destination %d\n", to);
}

```