package lecture.ch7;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();

        System.out.println(date.toString());
        // get milliseconds since janurary 1 , 1970 GMT

        long tempTime = date.getTime();
        tempTime += 3600*24*1000;

        date.setTime(tempTime);

        System.out.println("1 day later ");
        System.out.println(date.toString());

    }
}
