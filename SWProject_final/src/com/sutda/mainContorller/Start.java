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
        System.out.print("don\'t loose your money \n");
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
                System.out.println(" if you ready ");
                System.out.println(" input y or yes and press enter to play the game ");
            }
        }
    }

    public void guide(){
        System.out.print("\n start like this\n");
        System.out.print(" 1. set how many people to participate in this game \n");
        System.out.print(" 2. choose number for job (random) \n");
        System.out.print(" 3. check your money and start betting \n");
        System.out.print(" 4. rob people money! until only one person has money! \n");
        System.out.println();

    }

    public User[] initUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("how Many Player ?");
        int howMany = Integer.parseInt(sc.nextLine());

        User[] users = new User[howMany];

        for(int i=0;i<howMany;i++){
            System.out.println("input Player "+(i+1)+"\'s name");
            User tempUser = new User();
            tempUser.setName(sc.nextLine());
            users[i] = tempUser;
        }

        System.out.println();
        for(User user : users){
            System.out.print(user.getName()+" ");
        }
        System.out.print(" are ready \n");

        return users;
    }



}
