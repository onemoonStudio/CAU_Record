#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>


int run_now = 1;


void *thr_func(void *arg){
	int print_c2 = 0;

	while(print_c2++ <20){
		if(run_now ==2 ){
			printf("2");
			run_now =1;
			fflush(stdout);

		}else{
			sleep(1);
		}	
	}
}

int main(){
	int res;
	pthread_t a_thread;
	void *thread_result;
	int print_c1 = 0;

	printf("\twating for thread finished... \n");
	res = pthread_create(&a_thread , NULL , thr_func , NULL);

	while(print_c1++ < 20){
		if(run_now ==1 ){
			printf("1");
			run_now=2;
			fflush(stdout);
			
		}else{
			sleep(1);
		}
	}


	thread_result = pthread_join(a_thread,&thread_result);
	printf("\nthread joined\n");
	exit(EXIT_SUCCESS);

}
