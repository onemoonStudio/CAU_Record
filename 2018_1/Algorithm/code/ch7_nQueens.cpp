#include <iostream>
#include <math.h>

static int def_n = 4;

bool promising(int idx, int col[]) {
	bool flag = true;
	for (int i = 0; i < idx; i++) {
		if (col[i] == col[idx] || (fabs(idx - i) == fabs(col[idx] - col[i])) ) {
			flag = false;
			break;
		}
	}
	return flag;
}

void nqueens(int i, int col[]) {
	if (promising(i, col)) {
		if (i == def_n - 1) {
			for (int r = 0; r < def_n; r++)
				printf("< %d %d > \n", r , col[r]);
			return;
		}
		for (int j = 0; j < def_n; j++) {
			col[i + 1] = j;
			nqueens(i + 1, col);
		}
	}
}

void main() {
	int col[4] = {0,};
	nqueens(-1, col);
	
}