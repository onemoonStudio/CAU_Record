/*
최대한 유저의 행동에 집중하도록 메소드들을 만들었습니다.
다른 클래스에서 유저에 많이 접근하므로 public 으로 만들어
접근의 제약을 없게 하였습니다.

cardOne : 가지고 있는 패의 첫번째 입니다.
cardTwo : 가지고 있는 패의 두번째 입니다.
howmanyCard : 패를 총 몇장 가지고 있는지 체크해 더 받는 경우를 없도록 합니다.
money : 게임에서 가지고 있는 돈을 나타냅니다.
name : 유저들을 구분할 수 있도록 이름을 지정할 수 있습니다.
alive : 돈이 없다면 게임에 참여할 수 없도록 만들었습니다.
 */

package com.sutda.user;

import com.sutda.card.Card;
import com.sutda.card.Rank;

public class User {
    public Card cardOne;
    public Card cardTwo;
    public int howmanyCard;
    public int money;
    public String name;
    public boolean alive;

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Rank rank;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHowmanyCard(int howmanyCard) {
        this.howmanyCard = howmanyCard;
    }

    public User() {
        this.howmanyCard = 0;
        this.setMoney(100);
        this.alive = true;
    }

    public Card getCardOne() {
        return cardOne;
    }

    public void setCardOne(Card cardOne) {
        this.cardOne = cardOne;
    }

    public Card getCardTwo() {
        return cardTwo;
    }

    public void setCardTwo(Card cardTwo) {
        this.cardTwo = cardTwo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void getCard(Card thisCard){
        if(howmanyCard == 0) {
            this.setCardOne(thisCard);
            howmanyCard++;
            System.out.println(" 패 한장을 받았습니다. ");
        }
        else if(howmanyCard == 1) {
            this.setCardTwo(thisCard);
            System.out.println(" 현재 수중에 패가 두장 있습니다. ");
            this.rank = new Rank(cardOne,cardTwo);
        }
        else
            System.out.println("더 이상 카드를 넣을 수 없습니다.");
    }



    public boolean betMoney(int betting){
        if(this.money < betting){
            System.out.println("돈이 부족해서 배팅할 수 없습니다.");
            return false;
        }else
        {
            System.out.println(betting+"만원 만큼 배팅합니다.");
            this.money -= betting;
            return true;
        }
    }
}
