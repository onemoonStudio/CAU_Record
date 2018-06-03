package com.sutda.money;

import com.sutda.user.User;

import java.util.Scanner;

public class bettingSystem {
    private int moneyInThisSet;
    private int moneyPerPerson;
    private int[] bettingInThisSet;
    private int baseMoney;
    private int maxBettingMoney;

    public bettingSystem( int baseMoney) {
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

    public void bettingOption(User user){
        Scanner sc = new Scanner(System.in);

        System.out.println("어떤 방식으로 배팅을 하시겠습니까?");
        System.out.println("1. 만원단위 입력");
        System.out.println("2. 올인 (최대 "+user.getMoney()+"만원)");
        System.out.println("3. 다이 ");
        System.out.println("숫자로 방식을 입력해주세요");
        int opt = Integer.parseInt(sc.nextLine());

        switch (opt){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                break;
        }


    }

    public void bettingLoop(){

    }


    public int getMoneyInThisSet() {
        return moneyInThisSet;
    }

    public void setMoneyInThisSet(int moneyInThisSet) {
        this.moneyInThisSet = moneyInThisSet;
    }

    public int getMoneyPerPerson() {
        return moneyPerPerson;
    }

    public void setMoneyPerPerson(int moneyPerPerson) {
        this.moneyPerPerson = moneyPerPerson;


//        public void startgame () {
//            // 이 부분을 프로세스로 빼고 , betting base 쪽을 만든다.
//
//            for (int i = 0; i < users.length; i++)
//                if (users[i].alive && users[i].getMoney() < baseMoney) {
//                    System.out.println(users[i].getName() + "의 돈이 부족합니다. 배팅금액을 낮춰주세요");
//                    System.out.println("현재 배팅액 : " + this.baseMoney + " "
//                            + users[i].getName() + "의 남은 돈 :" + users[i].getMoney());
//                    System.out.print("배팅 금액 설정 : ");
//                    Scanner sc = new Scanner(System.in);
//                    setBaseMoney(sc.nextInt());
//                    System.out.println("게임을 다시 시작합니다.");
//                    startgame();
//                }
//            // 최소 금액보다 배팅액이 더 큰 경우
//
//            System.out.println("게임이 시작 되었습니다.");
//            // 살아있는 사람들 확인
////        aliveUsers = this.checkAliveUser();
////        for(int k=0;k<aliveUsers.length;k++)
////            System.out.print(aliveUsers[k].getName() +" ");
////        System.out.println(" 님이 참여합니다. \n");
//
//            // 판돈 배팅
////        for(int k=0;k<aliveUsers.length;k++)
////            aliveUsers[k].betMoney(this.baseMoney);
////        System.out.println(this.baseMoney+" 만원 만큼 배팅했습니다.");
////
////        setMoneyPerPerson(this.baseMoney);
////        setMoneyInThisSet(this.moneyPerPerson*aliveUsers.length);
////
////        this.userBetting();
//
//
//        }
//
//        public boolean userBettingLoop (User[]aliveUsers){
//
//            return false;
//        }


    }



}


