#include <iostream>
#define INF 9999
#define MAX 100

void prime(int n, const int w[][MAX], int setOfEdge[MAX][2]) {
	int vnear=0;
	int nearst[MAX] = { 0, };
	int distance[MAX] = { 0, };
	
	for (int i = 0; i < n; i++) {
		nearst[i] = 0;
		distance[i] = w[0][i];
	}
	
	distance[0] = -1;

	//@@@
	
	printf("near => ");
	for (int p = 0; p < 5; p++) {
		printf("%d ", nearst[p]);
	}
	printf("\ndist =>");
	for (int p = 0; p < 5; p++) {
		printf("%d ", distance[p]);
	}
	printf("\n");
	printf("\n");

	//@@@

	for (int i = 0; i < n - 1; i++) {

		int min = INF;
		for (int k = 0; k < n; k++) {

			if (distance[k] >= 0 && distance[k] <= min) {
				vnear = k;
				min = distance[k];
			}
		}

		printf("vnear %d \n", vnear);
		
		setOfEdge[i][0] = vnear;
		setOfEdge[i][1] = nearst[vnear];
		distance[vnear] = -1;
			
		for (int k = 0; k < n; k++) {

			if (distance[k] >= 0 && distance[k] > w[vnear][k]) {
				distance[k] = w[vnear][k];
				nearst[k] = vnear;
			}

		}

		//@@@

		printf("near => ");
		for (int p = 0; p < 5; p++) {
			printf("%d ", nearst[p]);
		}
		printf("\ndist =>");
		for (int p = 0; p < 5; p++) {
			printf("%d ", distance[p]);
		}
		printf("\n");
		printf("\n");
		//@@@

	}
	
	

}



int main() {

	int Mat[MAX][MAX] = { 0, };

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

			else {

				int temp = Mat[i][k];

				Mat[k][i] = temp;

			}

		}

	}

	// arr intialization

	int n = 5;

	int setOfEdge[MAX][2] = { 0, };



	prime(n, Mat, setOfEdge);

	for (int i = 0; i < 4; i++) {
		printf("%d - %d \n", setOfEdge[i][0], setOfEdge[i][1]);
	}


}