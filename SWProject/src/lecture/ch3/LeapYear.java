package lecture.ch3;

import java.util.Scanner;

public class LeapYear {

	public static void main(String[] args) {
		System.out.println("check Leap Year");
		System.out.println("if Leap Year -> divisible by 4 but not by100 , or divisible by 400");
		
		Scanner sc = new Scanner(System.in);
		
		int checkYear = sc.nextInt();
		if((checkYear %4 ==0 && checkYear%100 !=0) || checkYear%400 ==0) {
			System.out.println(checkYear +" is LeapYear");
		}else {
			System.out.println(checkYear + " is not LeapYear");
		}
		
	}

}
