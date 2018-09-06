/*
���������� ����� ���� �� ������ ���� ( ���� ���� ) ���� ã�� ���� ������ ������ �����ϴ� �˰���
6x6 ����� ���� �� [6][6] �� �ƴ϶� [7][7] �� �ϴ°��� �ξ� ���ϴ�
*/
#include <iostream>

int findMinMul(int i, int j, int d[6] ,int a[][7],int pathMat[][7]) {
	int min = 0;
	int subResult = 0;
	int path = 0;
	for (int k = i; k < j; k++) {
		subResult = a[i][k] + a[k + 1][j] + d[i-1] * d[k] * d[j];
		if (min == 0) { min = subResult; pathMat[i][j] = k; }
		else if (min > subResult) {
			min = subResult;
			pathMat[i][j] = k;
		}
	}
	
	return min;
	
}

void order(int i, int j, int pathMat[][7]) {
	if (i == j) printf("%d",i);
	else {
		int k = pathMat[i][j];
		printf("(");
		order(i, k, pathMat);
		order(k+1, j, pathMat);
		printf(")");
	}
}

void main() {
	int arr[7] = { 5,2,3,4,6,7,8 };
	// 6 matrix
	int mulMat[7][7] = { 0, };
	int pathMat[7][7] = { 0, };

	for (int i = 1; i < 6; i++) { // 1~5
		for (int k = 0; k < 6 - i; k++) {
			mulMat[k+1][k+i+1] = findMinMul(k+1, k + i+1,arr,mulMat,pathMat);
		}
	}

	/*for (int i = 0; i < 7; i++) {
		for (int k = 0; k < 7; k++) {
			printf("%d ",pathMat[i][k]);
		}
		printf("\n");
	}

	printf("\n%d\n", mulMat[1][6]);*/
	order(1, 6, pathMat);
	printf("\n");
}
