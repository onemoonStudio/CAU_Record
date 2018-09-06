package report.third;

public class P4_Fan {
	public static void main(String[] args) {
		P4_Fan fan = new P4_Fan();
		System.out.println(fan.toString());
	}
	
	private final String speedArr[] = {"SLOW" , "MEDIUM","FAST"};
	private int speed;
	private boolean on;
	private double radius;
	private String color;
	
	public P4_Fan() {
		// TODO Auto-generated constructor stub
		this.speed = 1;	
		this.on = false;
		this.radius = 5.0;
		this.color = "blue";
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(this.on) {
			String temp = "speed is "+this.speedArr[this.speed-1]+"\ncolor is "+this.color+"\nand radius is "+this.radius;
			return temp;
		}else {
			String temp = "선풍기는 꺼져 있습니다. \ncolor is "+this.color+"\nand radius is "+this.radius;
			return temp;
		}
	}

}
