package report.fourth;

public class MyStringTest {

     public static void main(String[] args) {
       MyString s = new MyString(new char[] {'A', 'B', 'C','D'});
       System.out.println(s.length());
       System.out.println(s.charAt(1));
       System.out.println(s.equals(new MyString(new char[] {'a', 'b', 'c','d'})));
       System.out.println(s.equals(new MyString(new char[] {'A', 'B', 'C', 'D'})));

       MyString s0 =  MyString.valueOf(345);
       char[] chars = s0.toChars();
       for (int i = 0; i < chars.length; i++) {
         System.out.print(chars[i]);
       }
       System.out.println();
       
       MyString s1 = s.substring(0,2);
       char[] chars1 = s1.toChars();
       for (int i = 0; i < chars1.length; i++) {
            System.out.print(chars1[i]);
      }
       System.out.println();
       
       MyString s2 = s.toLowerCase();
       char[] chars2 = s2.toChars();
       for (int i = 0; i < chars2.length; i++) {
            System.out.print(chars2[i]);
      }
       System.out.println();
     }
   }

   class MyString {
     private char[] chars;

     public MyString(char[] chars) {
       this.chars = chars;
     }

     public int length() {
      return chars.length;
     }

     public char charAt(int index) {
       return chars[index];
     }
     
     public boolean equals(MyString str) {
    	 	char temp[] = str.toChars();
    	 	if(chars == temp) return true;
    	 	else return false;
     }
     
     public MyString substring(int begin, int end) {
       char temp[] = new char[end-begin];
       System.arraycopy(chars, begin, temp, 0, end-begin);
       MyString result = new MyString(temp);
       return result;
     }

     public MyString toLowerCase() {
	    	 for(int i=0;i<chars.length;i++) {
	    		 if(chars[i] >= 'A' && chars[i] <= 'Z') {
	    			 int temp = 0;
	    			 temp = (int)(chars[i] - 'A') ;
	    			 temp = (int)'a' + temp;
	    			 chars[i] = (char)temp;
	    		 } 
	    	 }
	    	 MyString result = new MyString(chars); 
	    	 return result;
	    	 
     }

     public static MyString valueOf(int number) {
    	 	int temp = number;
    	 	int size = 0;
    	 	while(temp <10)
    	 		size++;
    	 	size++;
    	 	char sub_result[] = new char[size];
    	 	temp = number;
    	 	for(int i=0;i<size;i++) {
    	 		sub_result[size-i-1] = (char)(temp%10+(int)'0');
    	 		temp /= 10;
    	 	}
	 MyString result = new MyString(sub_result);
    	 return result;
     }

     public char[] toChars() {
       return chars;
     }
   }