package report.fourth;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileIO_Count {

	public static void main(String[] args) throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int charnum=0;
		int word=0;
		int sentence=0;
		
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			  // Get the selected file
			  java.io.File open_file = fileChooser.getSelectedFile();
			
			  // Create a Scanner for the file
			  Scanner input = new Scanner(open_file);
			
			  // Read text from the file
			  while (input.hasNext()) {
				  String line = input.nextLine();
				  
				  charnum += returnchar(line);
				  word += returnword(line);
				  sentence += returnsentence(line);
				  
				  System.out.println(line);
			  }
			  
			  // Close the file
			  input.close();
			  
		    }
		    else {
		      System.out.println("No file selected");
		    }	
		
		if(args.length == 0 || args.length>1) {
			System.out.println("you need one argument for filename saved");
		}else {
			
			java.io.File file = new java.io.File(args[0]);
			if (file.exists()) {
			      System.out.println("File already exists");
			      System.exit(0);
			    }
			
			java.io.PrintWriter output = new java.io.PrintWriter(file);

			// Write formatted output to the file
		    output.println(charnum);
		    output.println(word);
		    output.println(sentence);

		    
		    System.out.println(charnum +" @@@@@@@ ");
		    // Close the file
		    output.close();
		    
		}
		System.exit(0);
	}
	
	public static int returnchar(String line) {
		String temp = line.replaceAll(" ", "");
		return temp.length();
	}
	
	public static int returnword(String line) {
		String temp[] = line.split(" ");
		return temp.length;
	}
	
	public static int returnsentence(String line) {
		String temp[] = line.trim().split("[.?!]");
		return temp.length;
	}
	

}
