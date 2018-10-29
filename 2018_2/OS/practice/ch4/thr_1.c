#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUMBER_OF_THREADS 10

void *print_hello_world(void *tid)
{
	printf("hello world . greetings from thread %d\n" , tid);
	pthread_exit(NULL);
	// it's doesn't matter when remove exit 
}

int main()
{
	pthread_t threads[NUMBER_OF_THREADS];
	int status , i;

	for( i=0;i<10;i++){
		printf("main here. Creating threads %d\n" , i);
		status = pthread_create(&threads[i] , NULL , print_hello_world, (void *)i);
		printf("check status %d\n", status);

		if(status != 0){
			printf("Oops , pthread_create returned error code %d",status);
			exit(-1);
		}
	}
	exit(NULL);
}

