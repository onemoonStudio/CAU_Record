#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

char message[] = "hello world";
void *thr_function(void *arg)
{
	printf("thread is Running , arg -> %s \n" , arg);
	sleep(3);
	strcpy(message , "Bye!");

	pthread_exit((void*)"thank you for the CPU Time");
}

int main()
{
	int res;
	pthread_t a_thread;
	void *thr_result;

	res = pthread_create(&a_thread , NULL , thr_function,(void*)message);

	if(res != 0)
	{
		printf("creation thread failedi\n");
		exit(EXIT_FAILURE);
	}

	printf("wating for thread finished... \n");

	res = pthread_join(a_thread , &thr_result);

	if(res != 0){
		printf("thread join fail");
		exit(EXIT_FAILURE);
	}

	printf("thread joined is return %s\n" , thr_result);
	printf("message is now %s \n",message);
	exit(EXIT_SUCCESS);

	
}

