#include <iostream>

#define INF 1000

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
			if (A[i][j] != INF ) {
				order[t][0] = i;
				order[t][1] = j;
				order[t++][2] = A[i][j];
				// i j distance
			}
		}
	}

	for (int i = 5; i >= 0; i--) {
		for (int k = 0; k <= i; k++) {
			if (order[k][2] >= order[k + 1][2]) swapInKru(k, k + 1, order);			
		}
	}

}

void mergeGroup(int n, int parent, int child, int group[]) {

	int target = group[child];
	for (int i = 0; i < n; i++) {
		if (group[i] == target) 
			group[i] = group[parent];
	}
}

void kruskal(int n, int w[5][5], int result[7][3]) {
	int order[7][3] = { 0, };
	sortInKru(5, w, order);

	int group[5] = { 0, };
	for (int i = 0; i < 5; i++) group[i] = i;
	
	int temp[3] = {0,};
	for(int i=0;i<7;i++) {
		
		if (group[order[i][0]] != group[order[i][1]]) {
			result[i][0] = order[i][0];
			result[i][1] = order[i][1];
			result[i][2] = order[i][2];
			mergeGroup(5, order[i][0], order[i][1], group);
			
		}
		
		
	}
	
}

void main() {
	int source[5][5] = { 0, };

	// all element sould be less than 1000(INF)
	
	source[0][1] = 1;
	source[0][2] = 3;
	source[1][2] = 3;
	source[1][3] = 6;
	source[2][3] = 4;
	source[2][4] = 2;
	source[3][4] = 5;

	for (int i = 0; i < 5; i++) 
		for (int k = 0; k < 5; k++) 
			if (source[i][k] == 0) source[i][k] = INF;


	int order[7][3] = { 0, };
	kruskal(5, source, order);

	for (int i = 0; i < 7; i++) {
		if(order[i][2] != 0)
		printf("%d -- %d  with %d \n", order[i][0], order[i][1],order[i][2]);
	}
}