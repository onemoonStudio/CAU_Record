package dialogEx;

import javax.swing.JOptionPane;

public class Ch3_4_ConfirmDialog {

	public static void main(String[] args) {
		
		// while loop
		int option = 0;
//		while(option == JOptionPane.YES_NO_OPTION) {
//			
//			break;
//		}
		option = JOptionPane.showConfirmDialog(null,"continue?");
		int hello = JOptionPane.YES_NO_CANCEL_OPTION;
		System.out.println(option);
		// option 	-> yes : 0
		// 			-> no : 1
		// 			-> cancel : 2
		// 			-> close : -1
	}

	
	

}
