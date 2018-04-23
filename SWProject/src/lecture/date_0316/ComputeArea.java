package lecture.date_0316;

import java.util.Scanner;

public class ComputeArea {
	// compute Area

	public static void main(String[] args) {
		ComputeArea _this = new ComputeArea();
		double radius = 0.0; // Declare radius
		
		// for page 10 
		
//		_this.page_4(radius);
	    Scanner sc = new Scanner(System.in);
//	    _this.page_10_1(radius,sc);
	    _this.page_10_2(sc);

	    
	}
	
	public void page_4(double radius) {
		double area; // Declare area
		// Assign a radius
	    radius = 20; // New value is radius

	    // Compute area
	    area = radius * radius * 3.14159;

	    // Display results
	    System.out.println("The area for the circle of radius " +
	      radius + " is " + area);
	}
	
	public void page_10_1(double radius,Scanner sc) {
		// Prompt the user to enter a radius
	    System.out.print("Enter a number for radius: ");
	    radius = sc.nextDouble();
	    
	    // Compute area
	    double area = radius * radius * 3.14159;

	    // Display result
	    System.out.println("The area for the circle of radius " +
	      radius + " is " + area);
	}
	
	public void page_10_2(Scanner sc) {
		// Prompt the user to enter three numbers
	    System.out.print("Enter three numbers: ");
	    double number1 = sc.nextDouble();
	    double number2 = sc.nextDouble();
	    double number3 = sc.nextDouble();

	    // Compute average
	    double average = (number1 + number2 + number3) / 3;

	    // Display result
	    System.out.println("The average of " + number1 + " " + number2
	      + " " + number3 + " is " + average);
	}
	
}
