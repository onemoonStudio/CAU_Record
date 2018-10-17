#define _CRT_SECURE_NO_WARNINGS
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>

#define MAX_BUFFER 255

void prompt();
void cmdToArr(char inputStr[],char *totalLine[]);


int main(){
	char inputStr[MAX_BUFFER];
	char *totalLineArr[10];
	char *exit_code = "exit";
	pid_t pid;
	
	while(1){
		prompt();
                fgets(inputStr , sizeof(inputStr) , stdin);
                inputStr[strlen(inputStr)-1] = '\0';
                // save line except \n
                if(strcmp(exit_code,inputStr) == 0) exit(0);
                // compare line to exit
	
		pid = fork();
		if( pid < 0 ){
			perror("fork failed");
			exit(1);
		} else if( pid == 0 ){
			cmdToArr(inputStr,totalLineArr);
			execvp(totalLineArr[0], totalLineArr);
		} else{
			int stat_val;
			pid_t child_pid;
			child_pid = wait(&stat_val);
		}


	}
}

void prompt(){
	printf("Hyeontae Kim $ ");	
}
void cmdToArr(char inputStr[],char *totalLine[]){
	// memcpy(&totalLine,&inputStr,sizeof(inputStr));
	int i=0;
	char *temp = strtok(inputStr," ");

	while(temp != NULL){
		printf("%d",i);
		totalLine[i] = temp;
		i++;
		temp = strtok(NULL," ");
	}
	printf("%d",i);
	totalLine[i] = NULL;
}

