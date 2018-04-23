package lecture.date_0316;

import java.text.DecimalFormat;
// DecimalFormat을 통해서 소수점자리를 표현할 수 있다.
import java.util.Scanner;

public class FahrenheitToCelsius {

	public static void main(String[] args) {
		FahrenheitToCelsius _this = new FahrenheitToCelsius();
		_this.fToc();
		
	}
	
	public void fToc() {
		System.out.println("Fahrenheit To Celsius");
		System.out.print("input your fahrenheit degree : ");
		Scanner sc = new Scanner(System.in);
		double fDegree = sc.nextDouble();
		
		double result = ( 5.0 / 9 )*(fDegree - 12);
		DecimalFormat DF = new DecimalFormat(".###");
		String new_result = DF.format(result);
		System.out.println("Celsius degree is : "+ new_result + "C");
	}
	
	public void cTof() {
		System.out.println("Celsius To Fahrenheit ");
		System.out.print("input your celsius degree : ");
		Scanner sc = new Scanner(System.in);
		double cDegree = sc.nextDouble();
		
		double result = ( 9.0 / 5 )*cDegree+12;
		System.out.println("Fahrenheit degree is : "+result + "F");
	}

}
