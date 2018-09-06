package assignments;

// Leap Year


import java.util.Scanner;

public class Ass_3_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		int days = leapYear(year)? 366:365;
		System.out.println(days);
	}
	public static boolean leapYear(int year) {
		if((year%4 ==0 && year%100 != 0) || year %400 ==0)
			return true;
		return false;
	}

}
