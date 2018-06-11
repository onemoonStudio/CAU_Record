#include <iostream>

using namespace std;

const int W = 16;
const int N = 4;
const int ip[5] = {0,40 , 30 , 50, 10};
const int iw[5] = {0,2,5,10,5};
int maxprofit = 0;
int numbest = 0;

bool promising(int i , int profit , int weight){
    int j , k ;
    int totWeight;
    double bound;

    if(weight >= W)
        return false;
    else{
        j = i+1;

        bound = (double)profit;
        totWeight = weight;

        while(j <= N && totWeight + iw[j] <= W){
            totWeight = totWeight + iw[j];
            bound = bound+ip[j];
            j++;
        }
        k = j;

        if(k <= N)
            bound = bound + (W - totWeight)*(ip[k]/iw[k]);

        int temp =(bound>maxprofit)?1:0;
        cout << i << " " << profit << " " << weight << " " << bound << " " << temp<< "\n";

        return (bound > maxprofit);
    }
}


void kanpsack(int i , int profit , int weight){

    if(weight <= W && profit > maxprofit){
        maxprofit = profit;
        numbest = i;
    }

    if(promising(i,profit,weight)){
        // i + 1 추가한 경우
        kanpsack(i+1,profit+ip[i+1],weight+iw[i+1]);
        // i+1 추가 안한 경우
        kanpsack(i+1,profit,weight);
    }


}



int main(){
    kanpsack(0,0,0);
    cout << "\nresult \n\n";
    cout << maxprofit << endl;
    cout << numbest << endl;
}