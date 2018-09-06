package assignments;

public class Ass_3_2 {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}
	
	public static void test1() {
		int a = 1;
		int b = 2;
		b = a|b;
		System.out.println(b);
		// 3
	}
	
	public static void test2() {
		int a =1;
		int b = 3;
		System.out.println(a&b);
		// 1
		b = 2;
		System.out.println(a&b);
		// 0
	}

	public static void test3() {
		int x = 1;
		boolean temp = (x < 1 ) & (x++ < 10);
		System.out.println(x);
		// 2
	}
	
	public static void test4() {
		int x = 1;
		boolean temp = (1 > x) && ( 1 > x++);
		System.out.println(x);
		// 2 -> 1 
		// because 1>x is already false

	}
	public static void test5() {
		int x = 1;
		boolean temp = (1 == x) || (10 > x++);
		System.out.println(x);
		// 1

	}

}
