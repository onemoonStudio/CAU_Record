/*
 게임에 사용되는 패를 모아놓은 클래스 입니다.
 20장의 패를 Random 유틸을 통해서 무작위로 섞은 패가
 매 게임마다 새로 생성되어 만들어 집니다.
 또한 조금 더 제어가 쉽게 하기 위해서 Array 아 아닌 ArrayList 를 사용했습니다.

 deck : 실제 card 객체가 20개 들어있습니다.
 ranNum : 해당 배열을 통해서 랜덤한 덱을 구성하도록 만들었습니다.

 */

package com.sutda.card;

import java.util.ArrayList;
import java.util.Random;


public class Deck {

    private ArrayList<Card> deck;
    private int ranNum[];

    // 만들어진 어레이 중심으로 좀더 효율적으로 관리하기 위해서 ArrayList 를 만드는 함수입니다.
    public Deck() {
        deck = new ArrayList<>();
        this.createRand();
        for(int i=0;i<20;i++){
            if(ranNum[i] >10) deck.add(new Card(ranNum[i]-10,2));
            else deck.add(new Card(ranNum[i],1));
        }


    }

    // 1부터 20까지의 수를 섞은 array 를 설정합니다.
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

    // 패를 가져오는 경우 덱에서 패를 하나 지웁니다.
    public Card draw(){
        Card d_card = deck.get(0);
        deck.remove(0);
        return d_card;


    }


}
