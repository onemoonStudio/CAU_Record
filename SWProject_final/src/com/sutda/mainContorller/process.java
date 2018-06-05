package com.sutda.mainContorller;

import com.sutda.card.Card;
import com.sutda.card.CardSet;
import com.sutda.money.BettingSystem;
import com.sutda.user.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.sutda.money.BettingSystem.allinFlag;

public class process {
    public static void main(String[] args) throws Exception{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean endgame = false;
        boolean allinGame = false;

        User users[];
        User aliveUser[];

        Start st = new Start();
        Thread.sleep(1000);
        users = st.initUser();

        // 여러 설정들 여기 넣기

        CardSet deck = new CardSet();

//        while(!endgame){
//
//        }
        System.out.println(" 패를 분배합니다. \n");

        aliveUser = checkAliveUser(users);

        // 패 분배
        for(int i=0;i<aliveUser.length;i++){
            System.out.println(aliveUser[i].getName() +"님이 패를 가져옵니다.");
            users[i].getCard(deck.draw());
            users[i].getCard(deck.draw());
            System.out.println();
            Thread.sleep(500);
        }

        System.out.println("게임을 시작합니다.");
        BettingSystem betting = new BettingSystem(1);
        System.out.println("판돈을 배팅합니다.");
        betting.bettingBase(aliveUser);

        for(int k=0;k<aliveUser.length;k++){
            if(!allinGame){
                betting.bettingOption(aliveUser[k],k,0);
                allinGame = allinFlag;
            }else{
                betting.allinGame(aliveUser);
                break;
            }

            if(betting.bettineEnd(aliveUser)) break;

            if(k == aliveUser.length-1) k = 0;

        }







    }

    public static User[] checkAliveUser(User[] users){

        int userNum = 0;

        for(int i=0;i<users.length;i++)
            if(users[i].alive) userNum++;

        User[] alive = new User[userNum];

        userNum =0;
        for(int i=0;i<users.length;i++)
            if(users[i].alive) alive[userNum++] = users[i];

        return alive;
    }
}
