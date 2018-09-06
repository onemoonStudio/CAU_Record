#include <iostream>

const int node = 4;
const int color = 3;

bool promising(int depth,bool w[][4] , int vColor[4]) {
    printf("promising -> %d \n",depth);
    bool check = true;
    int idx = 0;

    while(idx < depth && check){
        if(w[depth][idx] && vColor[depth] == vColor[idx])
            check = false;

        idx ++;
    }

    return check;
}

void graphColoring(int depth,bool w[][4], int vColor[4]) {

    if (promising(depth,w,vColor)) {
        if (depth == node - 1) {
            for(int i=0;i<node;i++)
                printf("%d node is colored by %d \n",i,vColor[i]);
        }else{
            for(int newColor = 0; newColor < color ; newColor++){
                vColor[depth+1] = newColor;
                graphColoring(depth+1,w,vColor);
            }

        }
    }
}

int main() {

    bool w[4][4] = {
            { false , true , true , true },
            { true , false , true , false },
            { true , true , false , true },
            { true , false , true , false }
    };

    int vColor[4] = { 1,0,0,0};

    printf("첫번째 노드에 1 번째 컬러를 칠했다.\n");
    // 첫번째 노드에 1 번째 컬러를 칠했다.
    graphColoring(1,w,vColor);
    // 현재 첫번째 노드의 컬러가 0 인 경우로 체크했고,
    // 모든 값을 구하고 싶다면 0~3 을 vColor[0] 에 넣어서 graphColoring을 돌려야 한다.

}