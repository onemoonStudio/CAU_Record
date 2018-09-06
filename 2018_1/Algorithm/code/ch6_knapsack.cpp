#include <iostream>

static int itemW[4] = { 0,5,10,20 };
static int itemP[4] = { 0,50,60,140 };

int knapsack(int n, int W, int Arr[][30]) {

	if (n != 0 && W > 0) {
		if (itemW[n] > W) return Arr[n - 1][W];

		int sub_result_1 = knapsack(n - 1, W, Arr);
		int sub_result_2 = itemP[n] + knapsack(n - 1, W - itemW[n], Arr);

		if (sub_result_1 > sub_result_2) return sub_result_1;
		else return sub_result_2;

	}
	else return 0;
}

void main() {
	int Arr[4][30] = { 0, };
	int result = knapsack(3, 30, Arr);

	printf("%d\n", result);
}