//
// nearest 확인해보기
//
#include <iostream>

using namespace std;

#define INF 1000

int W[7][7] = {0,};
int N = 7;

void prim(){
    int vnear , tempMin;
    int nearest[7] = {0,};
    int distance[7] = {0,};

    //  F = empty_set

    for(int i=1; i<N;i++){
        nearest[i] = 0;
        distance[i] = W[0][i];
        // 초기에 노드가 0번째 밖에 없으므로 이것으로 초기화를 시켜준다.
    }
    distance[0] = -1;

    for(int repeat = 0 ; repeat < N-1;repeat ++ ){

        for(int el : distance) cout<< el << " ";
        cout << endl;

        tempMin = INF;
        for(int k=1;k<N;k++){
            if(0 <= distance[k] && distance[k] < tempMin){
                tempMin = distance[k];
                vnear = k;
            }
        }

        cout << vnear  << " node " << endl;
        distance[vnear] = -1;


        for(int k=1;k<N;k++){
            if(distance[k] > W[vnear][k]){
                distance[k] = W[vnear][k];
                nearest[k] = vnear;
            }
        }

        for(int el : nearest) cout<< el << " ";
        cout << endl << endl;


    }
}
int main(){
//    static int W[5][5] = {0,};
//    static int N = 5;
//    W[0][1] = 1; W[0][2] = 3;
//    W[1][2] = 3; W[1][3] = 6;
//    W[2][3] = 4; W[2][4] = 2;
//    W[3][4] = 5;
    W[0][1] = 8; W[0][2] = 9; W[0][3] = 11;
    W[1][4] = 10; W[2][4] = 5;
    W[2][3] = 13; W[2][5] = 12;
    W[3][6] = 8;
    W[5][6] = 7;

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(i == j)
                W[i][j] = 0;
            else if (W[i][j] != 0)
                W[j][i] = W[i][j];
            else{
                W[i][j] = INF;
            }
        }
    }

//    for(int i=0;i<5;i++) {
//        for (int j = 0; j < 5; j++) {
//            printf("%4d ",W[i][j]);
//        }
//        printf("\n");
//    }

    prim();

}
