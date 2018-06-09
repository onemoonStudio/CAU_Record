/*
게임을 시작하면서 필요한 기본 설정들을 입력 받습니다.
특히 유저 부분을 집중적으로 받으면서 기본적인 설정을 채워갑니다.
또한 가이드를 제시해 줌으로서 게임의 진행 방법을 알려줍니다.

 */

package com.sutda.mainContorller;

import com.sutda.user.User;

import java.util.Scanner;

public class Start {
    Scanner sc = new Scanner(System.in);
    boolean started;

    public Start() {
        this.started = false;

        for (int i=0;i<25;i++)
            System.out.print("#");
        System.out.print("\n Welcome to Sutda \n");
        System.out.print("혼자가 될 때까지 살아남으세요 \n");
        for (int i=0;i<25;i++)
            System.out.print("#");
        System.out.print("\n");


        System.out.print(" Start ? ( Y / N ) \n");

        this.startGame();

    }

    public void startGame(){
        while(!this.started){
            String checkStart = sc.nextLine();
            checkStart = checkStart.toLowerCase();

            if(checkStart.equals("yes") || checkStart.equals("y")){
                started = true;
                this.guide();
            }else{
                System.out.println(" 준비가되면 ");
                System.out.println(" y 나 yes 를 입력한 후 엔터를 누르면 게임이 시작됩니다.");
            }
        }
    }

    public void guide(){
        System.out.print("\n 이렇게 게임이 진행됩니다. \n");
        System.out.print(" 1. 게임에 참여할 인원의 수를 정해주세요 \n");
        System.out.print(" 2. 각자의 이름을 적어주세요 \n");
        System.out.print(" 3. 한명이 살아남을 때 까지 게임을 진행하세요 \n");
        System.out.println();
        System.out.println("행운을 빕니다!");

    }

    public User[] initUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("몇명이나 참여하나요 ?");
        System.out.print(">");
        int howMany = Integer.parseInt(sc.nextLine().trim());

        User[] users = new User[howMany];

        for(int i=0;i<howMany;i++){
            System.out.println((i+1)+"번째 참여자의 이름을 적어주세요");
            System.out.print(">");
            User tempUser = new User();
            tempUser.setName(sc.nextLine().trim());
            users[i] = tempUser;
        }

        System.out.println();
        for(User user : users){
            System.out.print(user.getName()+" ");
        }
        System.out.print(" 준비되었습니다. \n");

        return users;
    }



}
