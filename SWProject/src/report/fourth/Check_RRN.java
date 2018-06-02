package report.fourth;

import java.util.Scanner;

public class Check_RRN {

	public static void main(String[] args) {
		int arr[] = new int[7];
		
		Boolean Valid = true;
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		if(input.length() != 14) Valid = false;
		int year = Integer.parseInt(input.substring(0,2));
		int month = Integer.parseInt(input.substring(2,4));
		if(1<= month && month <= 12) {} else {Valid = false;}
		
		int day = Integer.parseInt(input.substring(4,6));
		
		switch(month) {
		case 2:
			if(day <= 28) {} else {Valid = false;}
			break;
		case 1:
		case 3: 
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(day <= 31 ) {} else {Valid = false;}
			break;
		default:
			if(day<=30){} else {Valid = false;}
			break;
		}
		
		char gender = input.charAt(7);
		// 19 이전년도는 21C라고 가정한다
		if('1' <= gender && gender <= '2' && year > 19) {} else if('3' <= gender && gender <= '4' && year <= 19) {} else {Valid = false;}
		
		int sum=0;
		for(int i=7;i<=13;i++) {
			sum += Integer.valueOf(input.charAt(i))-'0';	
		}
		if(sum%10 == 0 ) {} else {Valid = false;}
		
		if(Valid) System.out.println("Valid");
		else {System.out.println("Invalid Social Security Number");}
		
		
	}

}
