package assignments;

// 10 -> 16
public class Ass_5_13 {
	
	public static void main(String[] args) {
//		System.out.println(decToHex(33));
		System.out.format("hello %.2f\n",0.125);
		System.out.printf("hello %.3f\n",0.125);
		float hello = 0.12329789f;
		float test = (int)(hello*10000) / 10000.0f;
		System.out.println(test);
		
		System.out.printf("%4d",123);
		System.out.printf("%4d\n",123);
		System.out.printf("%f\n",0.123e+3f);
		
	}
	
	public static String decToHex(int dec) {
		String result="";
		
		while(true) {
			int temp = dec%16;
			char tempC;
			
			if(temp >=10) {
				tempC = (char)('a'+temp-10);
				result = tempC + result;
			}else {
				result = Integer.toString(temp) + result;
			}
			
			if(dec<16) {
				break;
			}
			dec /= 16;
		}
		
		return result;
	}
}
