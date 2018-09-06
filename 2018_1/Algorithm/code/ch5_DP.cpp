#include <iostream>

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

void main() {
	// @@@@@@@@@@@@@@@@@
	// binomial efficient
	// @@@@@@@@@@@@@@@@@

	int A[MAX][MAX] = { 0, };
	//printf("%d \n", bin2(4, 2,A));
	
	// @@@@@@@@@@@@@@@@@
	// floyd
	// @@@@@@@@@@@@@@@@@
	
	int w[MAX][MAX] = { 0, };
	// {INF,}  Nop! it doesn't work
	int d[MAX][MAX] = { 0, };

	w[0][1] = 1; w[0][3] = 1; w[0][4] = 5;
	w[1][0] = 9; w[1][2] = 3; w[1][3] = 2;
	w[2][3] = 4;
	w[3][2] = 2; w[3][4] = 3;
	w[4][0] = 3;

	for (int l = 0; l < 5; l++) {
		for (int m = 0; m < 5; m++) {
			if (w[l][m] == 0) { w[l][m] = INF; }
			if (l == m) w[l][m] = 0;
		}
	}
	// matrix w initialization is done
	// start floyd function
	/*floyd(5,w, d);
	printf("\nresult is \n\n");
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			printf("%d ", d[i][j]);
		}
		printf("\n");
	}*/
	// @@@@@@@@@@@@@@@@@
	// shortestpath
	// @@@@@@@@@@@@@@@@@
	int path[MAX][MAX] = { 0, };
	pathMatrix(5, w, d, path);

	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			printf("%d ", path[i][j]);
		}
		printf("\n");
	}
	printPath(5, 3, path);

}