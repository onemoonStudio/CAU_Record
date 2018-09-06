#include <iostream>
#define INF 1000

void dijkstra(int n, const int w[6][6], int d[6]) {
	int i, vnear;
	int min = 0;
	int touch[6] = { 0, };
	int length[6] = { 0, };

	for (int i = 1; i < 6; i++) length[i] = w[0][i];

	d[0] = 0;

	for (int m = 0; m < n - 1; m++) {

		min = INF;
		for (int i = 1; i < n; i++) {
			if (0 <= length[i] && length[i] < min) {
				min = length[i];
				vnear = i;
			}
		}
		// find vnear
		// print length
		/*for (int i = 0; i < 6; i++) {
			printf("%d ", length[i]);
		}
		printf("\n");*/
		
		for (int i = 1; i < n; i++) {
			if (length[vnear] + w[vnear][i] < length[i]) {
				length[i] = length[vnear] + w[vnear][i];
				touch[i] = vnear;
			}
		}

		d[vnear] = length[vnear];
		
		length[vnear] = -1;
		
	}
}

void main() {
	int w[6][6] = { 0, };
	int d[6] = { 0, };
	for (int i = 0; i < 6; i++)
		for (int k = 0; k < 6; k++) w[i][k] = INF;
	// 0 > A , 5 > F
	w[0][1] = 10;
	w[0][2] = 30;
	w[0][3] = 15;
	w[1][4] = 20;
	w[2][5] = 5;
	w[4][5] = 20;
	w[3][2] = 5;
	w[3][5] = 20;
	w[5][3] = 20;
	
	dijkstra(6, w, d);
	for (int el : d) {
		printf("%d ", el);
	}
	printf("\n");
}