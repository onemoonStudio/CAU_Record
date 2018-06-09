/*
베팅 시스템 클래스 입니다. 개인이 선택할 수 있는 선택지에 대해서 나타냈습니다.

moneyInThisSet : 현재 게임에 놓여있는 판돈을 나타냅니다.
moneyPerPerson : 각자 내야하는 돈을 나타냅니다. 이 금액을 모두 내야 서로 패를 확인하고 우승자를 가립니다.
bettingInThisSet : 유저들이 이번 게임에서 얼마를 냈는지 확인합니다.
baseMoney : 판돈을 설정합니다.
maxBettingMoney : 유저들의 현재 돈을 보고 올인게임으로 들어가면 얼마를 배팅할 것인지 미리 정해놓습니다.
allinFlag : 한명이 올인을 선택하게 된다면 해당 게임을 올인 게임이 됩니다. 이를 체크하기 위해서 boolean을 만들었습니다.
 */

package com.sutda.money;

import com.sutda.user.User;

import java.util.Scanner;

public class BettingSystem {
    private int moneyInThisSet;
    private int moneyPerPerson;
    public static int[] bettingInThisSet;
    private int baseMoney;
    private int maxBettingMoney;
    public static boolean allinFlag;

    public BettingSystem(int baseMoney) {
        this.baseMoney = baseMoney;
        this.setBaseMoney(this.baseMoney);
    }

    public int getMoneyInThisSet(){
        return this.moneyInThisSet;
    }

    public void setBaseMoney(int baseMoney) {
        System.out.println("기본 판돈은 " + baseMoney + "만원 부터 시작입니다.");
    }

    public void bettingBase(User[] aliveUsers){
        // basemoney 배팅하면서 게임에 대한 조건을 찾는다.

        this.bettingInThisSet = new int[aliveUsers.length];
        this.maxBettingMoney = aliveUsers[0].getMoney();

        for(int i=0;i<aliveUsers.length;i++){
            if(maxBettingMoney > aliveUsers[i].getMoney())
                this.maxBettingMoney = aliveUsers[i].getMoney();
            aliveUsers[i].betMoney(this.baseMoney);
            bettingInThisSet[i] = this.baseMoney;

        }
        this.moneyInThisSet = this.baseMoney * aliveUsers.length;
        this.moneyPerPerson = this.baseMoney;

    }

    public void bettingGuide(User user,int userNumber){

        System.out.println("*********************************");
        System.out.println("현재 판돈 "+this.moneyInThisSet+" 각자 "+this.moneyPerPerson+ " 만원을 내야 합니다.");
        System.out.println(user.getName()+"님은 "+bettingInThisSet[userNumber]+"만원 을 배팅하셨습니다.");
        System.out.println("어떤 방식으로 배팅을 하시겠습니까?");
        System.out.println("1. 배팅 (만원단위 입력) ");
        System.out.println("2. 콜 (단위 맞추기) ");
        System.out.println("3. 올인 (최대 "+this.maxBettingMoney+"만원)");
        System.out.println("4. 다이 ");
        System.out.println("5. 나의 상태 및 패 확인하기 ");
        System.out.println("*********************************");

    }
    public void bettingOption(User user,int userNumber , int opt){
        Scanner sc = new Scanner(System.in);
        if(opt == 0){
            bettingGuide(user,userNumber);
            System.out.println("숫자로 배팅방식을 입력해주세요 ");
            opt = Integer.parseInt(sc.nextLine());
        }

        switch (opt){
            case 1: // 배팅
                System.out.println("원하는 추가 배팅 금액을 입력해주세요");
                System.out.println("현재 "+user.getMoney()+"만원을 가지고 있습니다.");

                int userInput = Integer.parseInt(sc.nextLine());

                if(userInput > user.getMoney()){
                    System.out.println("가지고 있는 돈이 적습니다. 다시 입력해주세요");
                    this.bettingOption(user,userNumber,1);
                    return;
                }else if(userInput + bettingInThisSet[userNumber] < this.moneyPerPerson){

                    System.out.println("배팅금액이 내야 할 금액보다 작습니다. 다시 입력해주세요");
                    this.bettingOption(user,userNumber,1);
                    return;
                }else{
                    user.betMoney(userInput);
                    bettingInThisSet[userNumber] += userInput;
                    this.moneyInThisSet += userInput;
                    this.moneyPerPerson = bettingInThisSet[userNumber];
                    System.out.println(bettingInThisSet[userNumber]+"만원 만큼 배팅하셨습니다.");
                }
                break;
            case 2: // 콜
                if(bettingInThisSet[userNumber] < this.moneyPerPerson){
                    user.betMoney(this.moneyPerPerson - bettingInThisSet[userNumber]);
                    moneyInThisSet += (this.moneyPerPerson - bettingInThisSet[userNumber]);
                    bettingInThisSet[userNumber] += this.moneyPerPerson - bettingInThisSet[userNumber];
                    System.out.println(bettingInThisSet[userNumber]+"만원 만큼 배팅하셨습니다.");
                }
                break;
            case 3: // 올인
                System.out.println("올인을 선택하셨습니다. 모든 사람이 최대로 배팅합니다.");
                int allinBetting = this.maxBettingMoney - bettingInThisSet[userNumber];
                user.betMoney(allinBetting);
                this.moneyPerPerson = this.maxBettingMoney;
                bettingInThisSet[userNumber] += allinBetting;
                this.moneyInThisSet += allinBetting;
                allinFlag = true;
                break;
            case 4: // 다이
                System.out.println("다이를 선택하셨습니다. 현재까지 배팅한 금액은 돌려받지 못합니다.");
                bettingInThisSet[userNumber] = -1;
                break;
            case 5: // 나의 상태 확인하기
                System.out.print("이번 게임에서 "+user.getName()+"님은 "+bettingInThisSet[userNumber]+"만큼 배팅하셨으며,");
                System.out.println(" 현재 "+user.getMoney()+"만큼 돈을 가지고 있습니다.");
                System.out.println(user.getName()+"님의 패는 아래와 같습니다.\n");
                user.cardOne.printCard();
                user.cardTwo.printCard();
                System.out.println("\n조합은 게임이 끝나면 알 수 있습니다.\n");

                bettingOption(user,userNumber,0);
                return;

            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
                bettingOption(user,userNumber,0);
                return;
        }
        System.out.println("3초 뒤 다른 사람의 턴으로 넘어갑니다.");


    }

    public void allinGame(User[] aliveUsers , int userIdx){
        // allin 이 선택된 경우 -> allinflag == true
        Scanner sc = new Scanner(System.in);

        if(bettingInThisSet[userIdx] == -1 || bettingInThisSet[userIdx] == this.maxBettingMoney) {
            System.out.println("다른 사람의 턴으로 넘어갑니다.");
        }else{
            System.out.println(aliveUsers[userIdx].getName()+"님의 턴 입니다.");
            System.out.println("올인 게임입니다. 총 "+this.maxBettingMoney+"만큼 내야 합니다.");
            System.out.println("현재 "+bettingInThisSet[userIdx]+"만큼 배팅했습니다.");
            System.out.println("1. 추가 배팅후 올인게임 참가");
            System.out.println("2. 다이");
            int input = Integer.parseInt(sc.nextLine());
            if(input == 1)
                bettingOption(aliveUsers[userIdx],userIdx,3);
            else
                bettingOption(aliveUsers[userIdx],userIdx,4);
        }


    }

    public boolean bettineEnd(User[] aliveUsers){
        boolean result = false;
        for(int el : bettingInThisSet){
            if(el == -1 || el == this.moneyPerPerson){result = true;}
            else {
                result = false;
                break;
            }
        }
        allinFlag = false;
        return result;
    }

    public void winner(User winner){
        winner.setMoney(winner.getMoney()+this.moneyInThisSet);
    }


    public void afterEndGame(User[] users){
        System.out.println("현재 상황을 알려드리겠습니다.");
        for(int i=0;i<users.length;i++){
            System.out.println(users[i].getName()+"님 현재 "+users[i].getMoney()+"만원만큼 가지고 있습니다.");
            if(users[i].getMoney() == 0) users[i]. setAlive(false);
        }


    }


}


