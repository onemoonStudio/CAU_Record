package lecture.ch4;

import java.util.Scanner;

public class WhileContinue {
	public static void main(String[] args) {
		int count =0;
		while(count <5) {
			count ++;
			System.out.print(count + " count !\n");
			if(count >3) {
				continue;
			}
			// if continue is activated below continue statements are not executed
			System.out.print(count + " count !!\n");
		}
		System.out.println("\n\n");
		
		
		// Generate a random number to be guessed
	    int number = (int)(Math.random() * 101);

	    Scanner input = new Scanner(System.in);
	    System.out.println("Guess a magic number between 0 and 100");

	    int guess = -1;
	    while (true) {
	      // Prompt the user to guess the number
	      System.out.print("\nEnter your guess: ");
	      guess = input.nextInt();

	      if (guess == number) {
	    	  System.out.println("Yes, the number is " + number);
	    	  break;
	      }
	      else if (guess > number)
	        System.out.println("Your guess is too high");
	      else
	        System.out.println("Your guess is too low");
	    } 
	    
	}

}
