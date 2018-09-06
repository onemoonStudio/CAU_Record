#include <iostream>

static int node = 8;

bool promising(int idx , int vIndex[], bool w[][8]){
    int j=0;
    bool check = true;

    if(idx == node-1 && !w[vIndex[node-1]][vIndex[0]]) check = false;
    else if(idx > 0 && !w[vIndex[idx-1]][vIndex[idx]])
        check = false;
    else{
        while(j<idx && check){
            if(vIndex[j] == vIndex[idx])
                check = false;
            j++;
        }
    }

    return check;
}

void hamiltonian(int idx , int vIndex[],bool w[][8]){
//    printf("%d ",idx);
    if(promising(idx , vIndex, w)){
        if(idx == node-1){
            for(int i=0;i<node;i++)
                printf("%d 노드 ",vIndex[i]+1);
            printf("\n");
        }
        else{
            for(int j=1;j<node;j++){
                vIndex[idx+1] = j;
                hamiltonian(idx+1 , vIndex, w);
            }
        }
    }
}

int main(){
    bool w[8][8] = {false,};
    w[0][1] = true;
    w[0][2] = true;
    w[0][6] = true;
    w[0][7] = true;

    w[1][2] = true;
    w[1][6] = true;
    w[2][3] = true;
    w[2][5] = true;

    w[3][4] = true;
    w[4][5] = true;
    w[5][6] = true;
    w[6][7] = true;

    for(int i=0;i<8;i++){
        for(int j=0;j<8;j++){
            if(w[i][j]) w[j][i] = true;
        }
    }

    int vIndex[8] = {0,};
    vIndex[0] = 0;
    hamiltonian(0,vIndex,w);


}