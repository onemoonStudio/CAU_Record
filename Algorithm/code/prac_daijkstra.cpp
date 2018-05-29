#include <iostream>
#define INF 1000

void daijkstra(int n, int source[][6]) {
	int vnear; 
	int length[6] = { 0, };
	int dis[6] = { 0, };
	
	
	int visitedSet[6] = { 0, };

	for (int i = 0; i < n; i++) {
		length[i] = source[0][i];
		
	}
		

	vnear = 0;

	for (int i = 0; i < n - 1; i++) {
		for (int l = 0; l < n; l++) 
			printf("%d ", length[l]);
		printf("\n");

		
		
		int min = INF;

		for (int j = 0; j < n; j++) {
			if (length[j] > 0 && min > length[j]) {
				vnear = j;
				min = length[j];
			}
		}

		printf(" go to %d \n", vnear);

		visitedSet[i + 1] = vnear;

		
		for (int k = 0; k < n; k++) {
			if (length[k] > length[vnear] + source[vnear][k]) 
				length[k] = length[vnear] + source[vnear][k];
		}
		dis[vnear] = length[vnear];
		length[vnear] = -1;

	}

	
	for (int i = 0; i < n; i++)
		printf("%d ", dis[i]);
	

	
}

void main() {
	int source[6][6] = { 0, };
	source[0][1] = 10;
	source[0][2] = 30;
	source[0][3] = 15;
	source[1][4] = 20;
	source[4][5] = 20;
	source[2][5] = 5;
	source[3][2] = 5;
	source[4][5] = 20;
	source[5][4] = 20;
	for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++)
			if (source[i][j] == 0) source[i][j] = INF;

	daijkstra(6, source);

}