package assignments;

public class Ass_5_1 {

	public static void main(String[] args) {
		System.out.println(reverse(123327849));
	}
	
	public static int reverse(int a) {
		int result = 0;
		while(true) {
			result = result*10 + a%10;
			if(a <10)
				break;
			a/=10;
		}
		
		return result;
	}
	

}
