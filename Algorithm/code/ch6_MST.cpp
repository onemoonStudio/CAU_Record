#include <iostream>

#define INF 9999
#define MAX 100

void prime(int n, int w[][MAX] , int setOfEdge[MAX][2]) {
	int min, vnear;
	int nearest[MAX] = { 0, };
	int distance[MAX] = { 0, };

	for (int i = 1; i < n; i++) {
		nearest[i] = 0;
		distance[i] = w[0][i];
	}

	for (int r = 0; r < 5; r++) printf("%d ", nearest[r]);
	printf("\n");
	for (int r = 0; r < 5; r++) printf("%d ", distance[r]);
	printf("\n");
	printf("\n");

	//repeat
	for (int i = 1; i < n; i++) {
		min = INF;
		// find vnear
		for (int k = 1; k < n; k++) {
			if (0 < distance[k]&& distance[k] < min) {
				// why positive ? -> because we dont want check tree vertex again
				min = distance[k];
				vnear = k;	
			}
		}

		setOfEdge[i][0] = nearest[vnear];
		setOfEdge[i][1] = vnear;
		distance[vnear] = -1;

		printf("v%d : (%d %d) \n\n", i,setOfEdge[i][0], setOfEdge[i][1]);

		for (int k = 1; k < n; k++) {
			if (w[k][vnear] < distance[k]) {
				distance[k] = w[k][vnear];
				nearest[k] = vnear;
				// nearest is element of Y
			}
		}

		
		for (int r = 0; r < 5; r++) printf("%d ", nearest[r]);
		printf("\n");
		for (int r = 0; r < 5; r++) printf("%d ", distance[r]);
		printf("\n");
		printf("\n");
		
	}
}
void swapInKru(int a, int b, int A[7][3]) {
	int temp[3] = { 0, };
	temp[0] = A[a][0];
	temp[1] = A[a][1];
	temp[2] = A[a][2];
	A[a][0] = A[b][0];
	A[a][1] = A[b][1];
	A[a][2] = A[b][2];
	A[b][0] = temp[0];
	A[b][1] = temp[1];
	A[b][2] = temp[2];

}

void sortInKru(int n, int A[5][5], int order[7][3]) {
	int t = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if ( A[i][j] != INF) {
				order[t][0] = i;
				order[t][1] = j;
				order[t++][2] = A[i][j];
			}
		}
	}
	
	for (int i = 5; i >= 0; i--) {
		for (int k = 0; k <= i; k++) {
			if (order[k][2] >= order[k + 1][2]) swapInKru(k, k + 1, order);
		}
	}

}
void mergeGroup(int n,int parent, int child, int group[]) {
	int target = group[child];
	for (int i = 0; i < n; i++) {
		if (group[i] == target) {
			group[i] = group[parent];
		}
	}
}
void kruskal(int n, int m, int A[5][5]) {
	// n -> point , m -> edge
	// n 5, m 7
	int order[7][3] = {0,};
	sortInKru(5, A, order);
	// order arr is sorted edge array
	for (int i = 0; i < 7; i++) 
		printf("%d %d %d\n", order[i][0], order[i][1], order[i][2]);
	
	// @@
	
	int numberOfEdge = 1;
	int vertexGroup[5];
	int totalweight = 0;

	for (int i = 0; i < 5; i++) vertexGroup[i] = i;

	for (int i = 0; i < 7; i++) {
		if (numberOfEdge < n) {
			if (vertexGroup[order[i][0]] != vertexGroup[order[i][1]]) {
				numberOfEdge++;
				mergeGroup(5,order[i][0], order[i][1], vertexGroup);
				
				totalweight += order[i][2];
				/*printf("connect %d - %d and totalWeight is %d \n", order[i][0], order[i][1], totalweight);
				for (int i = 0; i < 5; i++) printf("%d ", vertexGroup[i]);
				printf("\n");*/
			}
		}
		else break;
	}	
}

int main() {
	int Mat[5][5] = { 0, };
	Mat[0][1] = 1;
	Mat[0][2] = 3;
	Mat[1][2] = 3;
	Mat[1][3] = 6;
	Mat[2][3] = 4;
	Mat[2][4] = 2;
	Mat[3][4] = 5;

	for (int i = 0; i < 5; i++) {
		for (int k = 0; k < 5; k++) {
			if (Mat[i][k] == 0) {
				Mat[i][k] = INF;
			}
			/*else {
				int temp = Mat[i][k];
				Mat[k][i] = temp;
			}*/		
		}
	}
	// arr intialization
	int n = 5;
	//int setOfEdge[MAX][2] = { 0, };

	//prime(n, Mat,setOfEdge);
	
	
	kruskal(n, 7, Mat);
	
}