package com.example.dsdesign;

public class Hamil {
    public static void main(String[] args) {

        int[][] w = new int[8][8];
        w[0][1] = 1;
        w[0][2] = 1;
        w[0][3] = 1;
        w[1][3] = 1;
        w[1][5] = 1;
        w[2][3] = 1;
        w[2][4] = 1;
        w[3][5] = 1;
        w[4][6] = 1;
        w[5][7] = 1;
        w[6][7] = 1;
        int[] path = new int[8];


        for(int i=0;i<8;i++){
            for(int k=0;k<8;k++){
                if(w[i][k] == 1 && (i != k))
                    w[k][i] = 1;
            }
        }


        ham(0,w,path);








    }

    public static void ham(int idx_i ,int[][] w, int[] path){

        int idx_j;
        int n = 8;

        if(promising(idx_i,w,path))
            if(idx_i == n-1){
                for(int num : path) System.out.print(num +" ");
                System.out.print("0");
                System.out.println();
            }

            else
                for(idx_j = 1;idx_j < n ;idx_j++){
                    path[idx_i+1] = idx_j;
                    ham(idx_i+1,w , path);
                }
    }

    public static boolean promising(int idx_i , int[][] w , int[] path){

        int idx_j = 1;
        int n = 8;
        boolean result = true;

        if(idx_i == n-1 && !( w[path[n-1]][0] == 1) )
            result = false; // 처음 마지막 이어져있어야함
        else if( idx_i > 0 && !(w[path[idx_i-1]][path[idx_i]] == 1))
            result = false; // 이전 노드랑 이웃해야함
        else{ // 노드가 이미 선택되면 안됨
            while((idx_j < idx_i) && result){
                if(path[idx_i] == path[idx_j])
                    result = false;
                idx_j++;
            }
        }
        return result;
    }
}
