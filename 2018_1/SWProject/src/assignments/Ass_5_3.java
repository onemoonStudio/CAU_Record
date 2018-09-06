package assignments;

public class Ass_5_3 {

	public static void main(String[] args) {
		System.out.println(lowerCaseToUpperCase('c'));
		System.out.println(lowerCaseToUpperCase2('c'));
	}

	public static char lowerCaseToUpperCase(char ch) {
		return Character.toUpperCase(ch);
	}
	public static char lowerCaseToUpperCase2(char ch) {
		int temp = ch-'a'+'A';
		char result = (char)temp; 
		return result;
	}

}
