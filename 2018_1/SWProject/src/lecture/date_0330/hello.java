package lecture.date_0330;

import java.util.Scanner;

public class hello {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = (int)(Math.random()*100);
        int guess;
        System.out.println("Guess number between 0 100");
        while (true){
            guess = sc.nextInt();
            if(guess == number){
                System.out.println("Yes ! your right! the number is " + number);
                break;
            }else if(guess > number){
                System.out.println("guess is to high");
            }else{
                System.out.println("guess is to low");
            }
        }

    }
}
