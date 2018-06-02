package com.sutda.card;

import java.util.ArrayList;
import java.util.Random;


public class CardSet {

    private ArrayList<Card> deck;
    private int ranNum[];

    public CardSet() {
        deck = new ArrayList<>();
        this.createRand();
        for(int i=0;i<20;i++){
            if(ranNum[i] >10) deck.add(new Card(ranNum[i]-10,2));
            else deck.add(new Card(ranNum[i],1));
        }


    }

    public void createRand(){

        Random rand = new Random();

        this.ranNum = new int[20];

        for(int i=0;i<20;i++){
            ranNum[i] = i+1;
        }

        for(int i=ranNum.length-1;i>0;i--){
            int j= rand.nextInt(i+1);

            int temp = ranNum[i];
            ranNum[i] = ranNum[j];
            ranNum[j] = temp;
        }


    }

    public Card draw(){
        Card d_card = deck.get(0);
        deck.remove(0);
        return d_card;


    }


}
