#define _CRT_NO_SECURE_WARNINGS
#include <stdio.h>
#include <string.h>

int main(){
	
	char s1[30] = "What a nice developer";	
	char *ptr = strtok(s1," ");


	while(ptr != NULL) // until token box to empty
	{
		printf("%s\n" , ptr);
		ptr = strtok(NULL," ");
	}

	return 0;

}

