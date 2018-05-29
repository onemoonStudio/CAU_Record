#include <iostream>
#include <math.h>


static int n = 4;
static int col[4] = { 0, };
static bool done = false;

bool promising(int idx) {
	bool check = true;
	for (int i = 0; i < idx; i++) {
		if (col[i] == col[idx] || fabs(col[i] - col[idx]) == fabs(i - idx)) {
			check = false;
			break;
		}
	}
	return check;
}

void queens( int idx) {
	if (promising(idx)) {
		if (idx == n-1 ) {
			for (int i = 0; i < n; i++)
				printf("( %d %d ) \n", i, col[i]);
			done = true;
			return;
		}
		else {
			for (int i = 0; i < n; i++) {
				col[idx + 1] = i;
				queens(idx + 1);
			}
		}
	}
}

void main() {
	

	for (int i = 0; i < 4; i++) {
		printf("%d @ \n" ,i);
		col[0] = i;
		queens(i);
		if (done) {
			printf("good \n");
			break;
		}
	}
	
	
}