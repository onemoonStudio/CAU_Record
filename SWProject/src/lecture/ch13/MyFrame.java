package lecture.ch13;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Frame");
		frame.setSize(300,200);
		frame.setLocation(200,200);
		frame.add(new JButton("add"));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
