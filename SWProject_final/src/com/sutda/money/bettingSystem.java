package com.sutda.money;

import com.sutda.user.User;

import java.util.Scanner;

public class bettingSystem {
    private int moneyInThisSet;
    private int moneyPerPerson;
    private int[] bettingInThisSet;
    private User[] users;
    private User[] aliveUsers;
    private int baseMoney;
    public boolean endGame;

    public bettingSystem(User[] users) {
        this(users,1);
    }

    public bettingSystem(User[] users,int baseMoney){
        this.users = users;
        this.baseMoney = baseMoney;
        this.setBaseMoney(this.baseMoney);
        this.endGame = false;
    }

    public void setBaseMoney(int baseMoney){
        System.out.println("기본 판돈은 "+baseMoney+"만원 부터 시작입니다.");

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
    }

    private User[] checkAliveUser(){
        int userNum = 0;
        for(int i=0;i<users.length;i++)
            if(users[i].alive) userNum++;
        if(userNum <= 1) {
            // exit game
            System.out.print("우승자는 ");
            for(int i=0;i<users.length;i++)
                if(users[i].alive) System.out.print(users[i].getName());
            System.out.print("입니다 축하합니다.\n");
            this.endGame = true;
            return null;
        }else{
            User[] alive = new User[userNum];
            userNum =0;
            for(int i=0;i<users.length;i++){
                if(users[i].alive) alive[userNum++] = users[i];
            }

            return alive;
        }
    }

    public void startgame(){

        for(int i=0;i<users.length;i++)
            if(users[i].alive && users[i].getMoney() < baseMoney ) {
                System.out.println(users[i].getName()+"의 돈이 부족합니다. 배팅금액을 낮춰주세요");
                System.out.println("현재 배팅액 : "+this.baseMoney + " "
                        +users[i].getName()+"의 남은 돈 :"+users[i].getMoney());
                System.out.print("배팅 금액 설정 : ");
                Scanner sc = new Scanner(System.in);
                setBaseMoney(sc.nextInt());
                System.out.println("게임을 다시 시작합니다.");
                startgame();
            }
            // 최소 금액보다 배팅액이 더 큰 경우

        System.out.println("게임이 시작 되었습니다.");
        // 살아있는 사람들 확인
        aliveUsers = this.checkAliveUser();
        for(int k=0;k<aliveUsers.length;k++)
            System.out.print(aliveUsers[k].getName() +" ");
        System.out.println(" 님이 참여합니다. \n");

        // 판돈 배팅
        for(int k=0;k<aliveUsers.length;k++)
            aliveUsers[k].betMoney(this.baseMoney);
        System.out.println(this.baseMoney+" 만원 만큼 배팅했습니다.");

        setMoneyPerPerson(this.baseMoney);
        setMoneyInThisSet(this.moneyPerPerson*aliveUsers.length);

        this.userBetting();



    }

    public void userBetting(){
        while(true){

        }
    }


}
