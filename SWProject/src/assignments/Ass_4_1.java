package assignments;

public class Ass_4_1 {

	public static void main(String[] args) {
		int count = 30;
		while(true) {
			count ++;
			if(Math.pow(count,2) > 12000 )
				break;
		}
		System.out.println(count);
	}

	
}

