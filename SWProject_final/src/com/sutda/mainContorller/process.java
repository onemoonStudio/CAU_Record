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
        Start st = new Start();
        User te = new User();
        Thread.sleep(1000);
        users = st.initUser();

        CardSet deck = new CardSet();

//        while(!endgame){
//
//        }
        System.out.println(" 패를 분배합니다. \n");

        for(int i=0;i<users.length;i++){
            System.out.println(i+1 +"번째 플레이어가 패를 가져옵니다.");
            users[i].getCard(deck.draw());
            users[i].getCard(deck.draw());
            System.out.println();
        }







    }
}
