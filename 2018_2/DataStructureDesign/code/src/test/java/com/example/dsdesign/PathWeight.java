package com.example.dsdesign;

import java.util.*;

public class PathWeight {

    public int val;
    public String path;

    public PathWeight(int val,String path){
        this.val = val;
        this.path = path;
    }

    public static void main(String[] args) {
        int howMD = 8;
        int howMP = 4;
//        int[][] event = new int[howMD][howMP];
        int[][] event =
                {{8,7,6,6},{9,1,7,1},{9,6,1,6},{9,1,7,6},{4,1,2,5},{7,3,3,1},{7,8,1,9},{9,7,2,2}};
        int[] days = new int[howMD];

        days[0] = 0;
        days[7] = 3;

//        Random rand = new Random();
//        for(int i=0;i<8;i++){
//            for(int k=0;k<4;k++){
//                event[i][k] = rand.nextInt(10);
//            }
//        }





        for(int i=0;i<8;i++){
            for(int k=0;k<4;k++){
                System.out.print(event[i][k]+" ");
            }
            System.out.println();
        }

        int[] max = new int[1];
        for(int i=0;i<4;i++){
            if(i == 3) {
                for(int k=i;k<howMD;k++) max[0]+= event[k][i];
            }else
                max[0] += event[i][i];
        }

//        System.out.println("firstMax : " + max[0]);



//        System.out.println(travel(max,0,0,0,days,event) + " @ ");

        String[] realpath = {""};

        System.out.println(travel(max,0,0,0,event ,"" ,realpath));

        System.out.println(realpath[0]);
        String result[] = realpath[0].split(">");




    }

    public static int idleEvent( int day , int place , int eve[][] ){
        int max = 0;
        int howMD = 8;
        int howMP = 4;
        int where = place;

        for(int d = day;d<howMD;d++){
            int subMax = 0;
            for(int p = where;p<howMP;p++){
                if( eve[d][p] > subMax) subMax = eve[d][p];
            }
            max += subMax;
        }
        return max;
    }

    public static int travel( int[] max , int nowscore , int day ,int place , int[][] eve ,String paths ,String[] realpath){
        int howMD = 8;
        int howMP = 4;
        paths = paths.concat(day+","+place+">");
        nowscore += eve[day][place];

        if(place == howMP-1 ){ // 마지막 지역으로 왔다면

            for(int d=day+1; d < howMD;d++){
                nowscore += eve[d][place];
            }
//            System.out.println(nowscore);
            if(max[0] < nowscore){
                max[0] = nowscore;
                System.out.println(paths);
                System.out.println(nowscore);
                realpath[0] = paths;
            }

            return nowscore;

        }else if (day == howMD-1){// 못가는 경우
            return 0;
        }else{ // 진행하는 경우
//            System.out.println("day : "+day +" place : "+place + " max[0] " + max[0] + " score : " +nowscore );
            int stay_idle = nowscore + idleEvent(day+1 , place  , eve );
            int across_idle = nowscore + idleEvent(day+1 ,place+1 , eve );

            if( stay_idle > max[0] && across_idle > max[0] ) { // 둘다 유망하다면
                return Math.max(
                        travel(max,nowscore , day+1 , place  , eve , paths , realpath) ,  // stay
                        travel(max,nowscore , day+1 , place+1 , eve , paths , realpath)    // across
                );
            }else if (stay_idle > max[0])
                return travel(max,nowscore , day+1 , place  , eve , paths , realpath);
            else if (across_idle > max[0])
                return travel(max,nowscore , day+1 , place+1  , eve, paths , realpath);
            else
                return 0;

        }

    }

//    public static int idleEvent( int day , int place , int days[] , int eve[][] ){
//        int max = 0;
//        int howMD = 8;
//        int howMP = 4;
//        int where = place;
//
//        for(int d = day;d<howMD;d++){
//            int subMax = 0;
//            for(int p = where;p<howMP;p++){
//                if( eve[d][p] > subMax) subMax = eve[d][p];
//            }
//            max += subMax;
//        }
////        System.out.println("@ day : "+day +" place : "+place + " max : "+max);
//        return max;
//    }
//
//    public static int travel( int[] max , int nowscore , int day ,int place , int[] days , int[][] eve ){
//        int howMD = 8;
//        int howMP = 4;
//        nowscore += eve[day][place];
//
//        if(place == howMP-1 ){ // 마지막 지역으로 왔다면
//
//            for(int d=day+1; d < howMD;d++){
//                nowscore += eve[d][place];
//            }
////            System.out.println(nowscore);
//            if(max[0] < nowscore) max[0] = nowscore;
//            return nowscore;
//
//        }else if (day == days.length-1){// 못가는 경우
//            return 0;
//        }else{ // 진행하는 경우
////            System.out.println("day : "+day +" place : "+place + " max[0] " + max[0] + " score : " +nowscore );
//            int stay_idle = nowscore + idleEvent(day+1 , place , days , eve );
//            int across_idle = nowscore + idleEvent(day+1 ,place+1 , days , eve );
//
//            if( stay_idle > max[0] && across_idle > max[0] ) { // 둘다 유망하다면
//                return Math.max(
//                        travel(max,nowscore , day+1 , place , days , eve) ,  // stay
//                        travel(max,nowscore , day+1 , place+1 , days , eve)    // across
//                );
//            }else if (stay_idle > max[0])
//                return travel(max,nowscore , day+1 , place , days , eve);
//            else if (across_idle > max[0])
//                return travel(max,nowscore , day+1 , place+1 , days , eve);
//            else
//                return 0;
//
//        }
//    }
}
