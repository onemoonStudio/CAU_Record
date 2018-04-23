package lecture.ch5;

public class UsingMath {

	public static void main(String[] args) {
		System.out.println("trigonometic "+trigonometric(3, 90));
		System.out.println(Math.rint(1.5) +" and " + Math.rint(2.5));
		// it returns likes round but it returns even number if there's same gap between odd , even
		System.out.println(Math.round(1.5) + " and " + Math.round(3.4));
		
	 int test1 = 1;
	 int test2 = 2;
	 int test3 = test1|test2;
	 int test4 = test1&test2;
	 
	 System.out.println(test3);
	 System.out.println(test4);
	}
	
	public static double trigonometric(int num , int degree) {
		double radians = Math.toRadians((double)degree);
		switch (num) {
		case 0:
			return Math.sin(radians);
		case 1:
			return Math.cos(radians);
		case 2:
			return Math.tan(radians);
		case 3:
			return Math.sin(Math.PI/2);
		default:
			return -1.0;
		}
	}

}
