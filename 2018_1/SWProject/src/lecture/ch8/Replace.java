package lecture.ch8;

public class Replace {

	public static void main(String[] args) {
		System.out.println("Welcome1".replace("e", "XX"));
		System.out.println("@");
		System.out.println("Welcome2".replaceAll("e", "XX"));
		System.out.println("@");
		System.out.println("Welcom[eo]3".replace("[eo]", "XX"));
		System.out.println("@");
		System.out.println("Welcome4".replaceAll("[eo]", "XX") );
		System.out.println("@");
		System.out.println("Welcome5".replaceAll("[^eo]", "XX"));
		// only replaceAll can use regularExpression not replace
	}

}
