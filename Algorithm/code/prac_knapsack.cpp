#include <iostream>
#include <math.h>

#define max(a, b)((a>b)? a:b)

static int totalw = 30;


int knapsack(int i, int w,int pr[3],int we[3] , int result[][30]) {
	
	
	if (w > totalw) return knapsack(i, totalw, pr, we, result);
	else if (i == 0) {
		printf("%d %d %d\n",i,w ,result[0][i]);
		return result[0][i];
	}else if (w <= totalw) {
		int sub_result_1 = knapsack(i - 1, w, pr, we, result);
		int sub_result_2 = pr[i] + knapsack(i - 1, totalw - w, pr, we, result);
		printf("%d %d %d\n", i,w,max(sub_result_1, sub_result_2));
		return max(sub_result_1, sub_result_2);
	}
}

void main() {
	int p[3] = { 50,60,140 };
	int w[3] = { 5,10,20 };
	int result[4][30] = { 0, };

	result[0][5] = 50;
	result[0][10] = 60;
	result[0][20] = 140;
	result[0][30] = 140;
	int wow = knapsack(3,30,p, w, result);

	printf("\n@@@ %d @@@\n", wow);
}