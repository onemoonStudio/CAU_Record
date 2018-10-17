#include <stdio.h>
#include <string.h>

int main(){

	char input[50];
	fgets(input ,sizeof(input) , stdin);
	char *exit_code ="exit\n";

	int check = strcmp(input,exit_code);
	printf("%d\n",check);



}
