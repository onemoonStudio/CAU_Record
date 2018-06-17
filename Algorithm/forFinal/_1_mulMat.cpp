//
// 어렵지는 않으나 변수를 굉장히 꼼꼼하게 봐야한다!
// i j 등등 헷갈릴 수 있으므로 주의깊게 적자
// 최적의 해 다시 보기
//

#include <iostream>
#include "math.h"

using namespace std;
// cout cin 을 위해서는 namespace 를 사용하자

#define MAX 1000000;

const int n = 6;
const int d[7] ={5,2,3,4,6,7,8};
// 6개의 행렬

int calM(int M[][7],int i, int j){
    if(i == j) return 0;

    int tempMin = MAX;
    int temp;

    for(int k=i;k<j;k++){
        temp = M[i][k] + M[k+1][j] + d[i-1]*d[k]*d[j];
        tempMin = min(tempMin,temp);
    }

    return tempMin;
}

void mulMat(int result[][7]){

    for(int i=1 ; i < 6 ; i++){
        for(int j=1;j< 7-i ; j++){
            result[j][j+i] = calM(result,j,j+i);
            cout << j << j+i <<" ";
        }
        cout << endl;
    }
    cout << endl;

}


int main(){
    int result[7][7] = {0,};
    mulMat(result);
    for(int i=0; i<7;i++){
        for (int j=0;j<7;j++){
            printf("%4d",result[i][j]);
        }
        cout << endl ;
    }

}
