package lecture.ch3;

import java.util.Scanner;

public class Lottery {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("welcom to Lottery");
		
		int lotteryNumber = (int)(Math.random()*100);
		// 0 <= Math.random < 1 
		int lotterydigit1 = lotteryNumber / 10;
		int lotterydigit2 = lotteryNumber % 10;
		
		
		
		while(true) {
			int guessNumber = sc.nextInt();
			int guessdigit1 = guessNumber/10;
			int guessdigit2 = guessNumber%10;
			if(lotteryNumber == guessNumber ) {
				System.out.println("you win $10,000 ! congraturation");
			}else if ((lotterydigit1 == guessdigit2) && (lotterydigit2 == guessdigit1) ) {
				System.out.println("wow ! you win $3,000 ! not bad !" );
			}else if((lotterydigit1 == guessdigit2) ^ (lotterydigit2 == guessdigit1)){
				System.out.println("Match one digit: you win $1,000");
				System.out.println(lotteryNumber );
				break;
			}else
			{
				System.out.println("bye...");
			}
		}
		
	}
}
