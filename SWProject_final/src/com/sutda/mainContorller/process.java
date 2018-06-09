/*
메인 프로세스 입니다.
해당 클래스에서 전체적인 흐름이 진행되고 , 조합됩니다.
while 을 통해서 한명의 유저가 남을 때 까지 진행됩니다.

users : 처음 게임에 참여한 사람들
aliveUser : 게임이 진행되면서 살아있는 사람들을 가려냅니다.
allinGame : 올인게임인지 아닌지 체크하는 값 입니다.
deck : 카드 더미 입니다.

 */

package com.sutda.mainContorller;

import com.sutda.card.Deck;
import com.sutda.money.BettingSystem;
import com.sutda.user.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.sutda.money.BettingSystem.allinFlag;
import static com.sutda.money.BettingSystem.bettingInThisSet;

public class process {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean allinGame = false;

        User users[];
        User aliveUser[];
        Deck deck = null;


        Start st = new Start();
        Thread.sleep(1000);
        users = st.initUser();

        aliveUser = checkAliveUser(users);

        while(aliveUser.length != 1){
            deck  = new Deck();

            System.out.println("현재 게임에 참여한 사람은 "+aliveUser.length+"명 입니다.");
            System.out.println(" 패를 분배합니다. \n");

            // 패 분배
            for(int i=0;i<aliveUser.length;i++){
                System.out.println(aliveUser[i].getName() +"님이 패를 가져옵니다.");
                Thread.sleep(500);
                users[i].getCard(deck.draw());
                Thread.sleep(500);
                users[i].getCard(deck.draw());
                Thread.sleep(1000);

                System.out.println("3초 뒤에 패를 확인하겠습니다. 다른 사람은 확인할 수 없도록 해주세요");
                Thread.sleep(3000);

                //System.out.println(aliveUser[i].getName()+"님의 패는 <"+aliveUser[i].rank.checkPare()+"> 입니다.");
                System.out.println(aliveUser[i].getName()+"님의 패는 아래와 같습니다.");
                aliveUser[i].cardOne.printCard();
                aliveUser[i].cardTwo.printCard();
                System.out.println("5초 뒤에 화면이 내려갑니다.");
                Thread.sleep(5000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            }

            System.out.println("게임을 시작합니다.");
            BettingSystem betting = new BettingSystem(1);
            System.out.println("판돈을 배팅합니다.");
            betting.bettingBase(aliveUser);

            for(int k=0;k<aliveUser.length;k++){
                if(!allinGame){
                    betting.bettingOption(aliveUser[k],k,0);
                    allinGame = allinFlag;
                    Thread.sleep(3000);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                }else{
                    betting.allinGame(aliveUser,k);
                    break;
                }

                if(betting.bettineEnd(aliveUser)) break;

                if(k == aliveUser.length-1) k = 0;
                Thread.sleep(1000);
            }


            System.out.println("배팅이 끝났습니다. 패를 확인합니다.");
            // 배팅이 끝나면 살아남은 사람들 끼리 랭킹 비교
            Thread.sleep(1000);
            int checkRank = 1000;
            int winnerIdx = 10;

            for(int k=0;k<aliveUser.length;k++){
                System.out.println(aliveUser[k].getName()+"님의 패는 "+aliveUser[k].rank.checkPare()+" 입니다. ");
                if(bettingInThisSet[k] != -1 && checkRank >= aliveUser[k].rank.getRank()){
                    checkRank = aliveUser[k].rank.getRank();
                    winnerIdx = k;
                    Thread.sleep(1000);
                }
            }



            Thread.sleep(500);
            System.out.println("우승자는 "+aliveUser[winnerIdx].getName()+"님 입니다. 축하합니다.");
            System.out.println("판돈 "+betting.getMoneyInThisSet()+"만원을 가져갑니다.\n\n");
            betting.winner(aliveUser[winnerIdx]);

            // 게임이 끝난 뒤 설정
            betting.afterEndGame(aliveUser);
            aliveUser = null;
            aliveUser = checkAliveUser(users);


            if(aliveUser.length != 1)
            for(int p=0;p<5;p++){
                System.out.println((5-p)+"초 뒤에 게임이 시작됩니다.");
                Thread.sleep(1000);
            }
            System.out.println("\n\n");




        }


        System.out.println("전체 게임이 종료 되었습니다.");
        Thread.sleep(500);
        System.out.println("우승자는 "+aliveUser[0].getName()+"님 입니다. 축하드립니다.");
        Thread.sleep(3000);

    }

    public static User[] checkAliveUser(User[] users){

        int userNum = 0;

        for(int i=0;i<users.length;i++)
            if(users[i].alive) userNum++;

        User[] alive = new User[userNum];

        userNum =0;
        for(int i=0;i<users.length;i++)
            if(users[i].alive) {
                alive[userNum++] = users[i];
                users[i].setHowmanyCard(0);
            }

        return alive;
    }
}
