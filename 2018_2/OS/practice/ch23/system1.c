#include <stdlib.h>
#include <stdio.h>

int main(){
	printf("Running ps with system\n");
	system("ps -u");
	printf("Done\n");
	exit(0);
}

