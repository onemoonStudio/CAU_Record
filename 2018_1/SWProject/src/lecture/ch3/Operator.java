package lecture.ch3;

public class Operator {

	public static void main(String[] args) {
		int x=3;
		int y=3 ;
		int o = 1;
		int p=2;
		// if operator just write once
		// java checks first argument and if its true check seconds argument
		// but if first argument is false then java doesn't check second one
		if((x >2) | (x++ <10)) {}
		if((y>2) || (y++ < 10)) {}
		if((o<0) & (o++ <10)) {}
		if((p<0) && (p++ <10)) {}
		System.out.println(x);
		System.out.println(y);
		System.out.println(o);
		System.out.println(p);
		
		// 2개 있는게 더 효율적이다!
	}

}
