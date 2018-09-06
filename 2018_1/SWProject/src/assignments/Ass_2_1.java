package assignments;

public class Ass_2_1 {

	public static void main(String[] args) {
		double miles = 1.609;
		
		System.out.println(miles);
		
		int a =20;
		int temp = a%=50/a+3;
		// a%= 이 먼저 실행된다.
		System.out.println(temp);
		a = 78;
		System.out.println(3 % -4); // 3
		System.out.println(-10 % -4); // -2
		
		// 1파운드는 0.454 kg
		double pound = 2.4;
		System.out.println(pound*0.454);
	}

}
