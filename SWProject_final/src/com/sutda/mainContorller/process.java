package com.sutda.mainContorller;

import com.sutda.card.Card;
import com.sutda.card.CardSet;
import com.sutda.user.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class process {
    public static void main(String[] args) throws Exception{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean endgame = false;

        User users[];
        User aliveUser[];

        Start st = new Start();
        Thread.sleep(1000);
        users = st.initUser();

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
