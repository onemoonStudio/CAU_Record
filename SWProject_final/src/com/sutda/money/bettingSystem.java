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

    public void setBaseMoney(int baseMoney) {
        System.out.println("기본 판돈은 " + baseMoney + "만원 부터 시작입니다.");
    }

    public void bettingBase(User[] aliveUsers){
        // basemoney 배팅하면서 게임에 대한 조건을 찾는다.

        this.bettingInThisSet = new int[aliveUsers.length];
        this.maxBettingMoney = this.baseMoney;

        for(int i=0;i<aliveUsers.length;i++){
            aliveUsers[i].betMoney(this.baseMoney);
            bettingInThisSet[i] = this.baseMoney;
            if(maxBettingMoney > aliveUsers[i].getMoney())
                this.maxBettingMoney = aliveUsers[i].getMoney();
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
        System.out.println("5. 나의 패 확인하기 ");
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
                }else if(userInput < this.moneyPerPerson){
                    System.out.println("배팅금액이 내야 할 금액보다 작습니다. 다시 입력해주세요");
                    this.bettingOption(user,userNumber,1);
                    return;
                }else{
                    user.betMoney(userInput);
                    bettingInThisSet[userNumber] += userInput;
                    this.moneyPerPerson = bettingInThisSet[userNumber];
                    System.out.println(bettingInThisSet[userNumber]+"만원 만큼 배팅하셨습니다.");
                }
                break;
            case 2: // 콜
                if(bettingInThisSet[userNumber] < this.moneyPerPerson){
                    user.betMoney(this.moneyPerPerson - bettingInThisSet[userNumber]);
                    bettingInThisSet[userNumber] += this.moneyPerPerson - bettingInThisSet[userNumber];
                    System.out.println(bettingInThisSet[userNumber]+"만원 만큼 배팅하셨습니다.");
                }
                break;
            case 3: // 올인
                System.out.println("올인을 선택하셨습니다. 모든 사람이 최대로 배팅합니다.");
                user.betMoney(this.maxBettingMoney - bettingInThisSet[userNumber]);
                this.moneyPerPerson = this.maxBettingMoney;
                allinFlag = true;
                break;
            case 4: // 다이
                System.out.println("다이를 선택하셨습니다. 현재까지 배팅한 금액은 돌려받지 못합니다.");
                bettingInThisSet[userNumber] = -1;
                break;
            case 5: // 나의 패 확인하기
                user.rank.checkPare();
                bettingOption(user,userNumber,0);
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
                bettingOption(user,userNumber,0);
                break;
        }
        System.out.println("다른 사람의 턴으로 넘어갑니다.");

    }

    public void allinGame(User[] aliveUsers){
        // allin 이 선택된 경우 -> allinflag == true
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<aliveUsers.length;i++){
            if(bettingInThisSet[i] == -1 || bettingInThisSet[i] == this.maxBettingMoney) {
                System.out.println("다른 사람의 턴으로 넘어갑니다.");
            }else{
                System.out.println("올인 게임입니다. 총 "+this.maxBettingMoney+"만큼 내야 합니다.");
                System.out.println("현재 "+bettingInThisSet[i]+"만큼 배팅했습니다.");
                System.out.println("1. 추가 배팅후 올인게임 참가");
                System.out.println("2. 다이");
                int input = Integer.parseInt(sc.nextLine());
                if(input == 1)
                    bettingOption(aliveUsers[i],i,3);
                else
                    bettingOption(aliveUsers[i],i,4);
            }
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
        return result;
    }

}


