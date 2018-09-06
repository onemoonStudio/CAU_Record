package lecture.ch4;

import java.util.Scanner;

public class DecToHex {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input decimal number ");
		int inputDec = sc.nextInt();
		String result ="";
		
		while(true) {
			int num = inputDec%16;
			if(num >=10){
				char tempChar = 'A';
				tempChar += num -10;
				result = Character.toString(tempChar).concat(result);
			}else {
				result = Integer.toString(num).concat(result);
			}
			
			if(inputDec >15) {
				inputDec /= 16;
			}else {
				break;
			}
			
		}
		System.out.println(result);
		
	}

}
