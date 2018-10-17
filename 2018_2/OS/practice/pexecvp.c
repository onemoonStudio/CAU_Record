#include <sys/types.h>
#include <unistd.h>
#include <string.h>

int main(){

	char *main_cmd[10];
	main_cmd[0] = "ls";
	main_cmd[1] = "-a";
	main_cmd[2] = NULL;

	execvp(main_cmd[0],main_cmd);


}
