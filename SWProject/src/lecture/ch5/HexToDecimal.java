package lecture.ch5;

import java.util.Scanner;

public class HexToDecimal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("input Hex String ");
		String hexaStr = sc.nextLine();
		System.out.println(hexaStr +" is hexaNumber of " +(hexaToDecimal(hexaStr)));
	}
	
	public static int hexaToDecimal( String hx) {
		int length = hx.length();
		int result = 0;
		for(int i=0;i<length;i++) {
			result += (int)Math.pow(16.0, length-1-i)*hxCharToInt(hx.charAt(i));
			// 16 * length-1-i 가 아니다!
		}
		return result;
	}
	
	public static int hxCharToInt(char ch) {
		if('A' <= ch&& ch <= 'F' ) {
			return (10 + ch-'A');
		}else {
			return (int)(ch-'0');
		}
	}
	

}
