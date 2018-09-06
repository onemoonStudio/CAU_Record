package report.third;

import assignments.Rectangle;

public class P3_Rectangle {

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(4, 40,"Red");
		Rectangle r2 = new Rectangle(3.5, 35.9,"Red");
		System.out.println("r1 information");
		r1.getInfo();
		System.out.println();
		System.out.println("r2 information");
		r2.getInfo();
	}
	
	private double width;
	private double height;
	private String color;// white
	
	public P3_Rectangle() {
		this(1.0,1.0);
	}
	
	public P3_Rectangle(double width,double height) {
		this(width,height,"white");
	}
	
	public P3_Rectangle(double width,double height ,String color) {
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getArea() {
		
		return (width*height);
	}
	
	public double getPerimeter() {
		return 2*(width+height);
	}
	
	public void getInfo() {
		System.out.println("this width is "+ this.width);
		System.out.println("this height is "+ this.height);
		System.out.println("this color is "+ this.color);
		System.out.println("this area is "+ getArea());
		System.out.println("this perimeter is "+ getPerimeter());
	}

}
