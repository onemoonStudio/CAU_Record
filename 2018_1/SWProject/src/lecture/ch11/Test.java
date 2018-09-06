package lecture.ch11;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
//        int h = 15;
//        System.out.printf("%4d",h);
        Tcl test1 = new Tcl();
        Tcl test2 = (Tcl)test1.clone();
        System.out.println(test2.hell);

        String test = "html!js!css";
        System.out.println(Arrays.toString(test.split("!")));
        System.out.println(Arrays.toString(test.split("!",1)));
        System.out.println(Arrays.toString(test.split("!",2)));
        System.out.println(Arrays.toString(test.split("!",3)));



    }
}
