// (반전된 정수 출력하기) 
// 반전된 순서로 정수를 출력하기 위해 다음의 메소드를 이용하여 작성하라.
// public static void reverse(int number)

// 입력: 123456789
// 출력: 987654321


package report.second;

public class ReverseInteger {
	public static void main(String[] args) {
		reverse(123456789);
	}
	
	public static void reverse(int number) {
		int result=0;
		while(number > 10) {
			result += number%10;
			result = result*10;
			number /= 10;
		}
		result += number;
		System.out.println(result);
	}
}
