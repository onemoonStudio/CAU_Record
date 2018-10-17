#include <stdio.h>
#include <string.h>

int main(){

	char line[50];
	char *temp;
	char *command[10];
	int i=0;

	char *first;

	fgets(line,sizeof(line),stdin);
	first = line;
	temp = strtok(line," ");
	
	while(temp != NULL){
		command[i] = temp;
		i++;
		temp = strtok(NULL," " );
	}

	for(int k=0;k<10;k++){
		if(command[k] != NULL){
			printf("%s\n",command[k]);	
		}
	}

	puts(first);

	exit(0);
}
