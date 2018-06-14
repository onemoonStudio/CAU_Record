package lecture.ch7;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random rnd = new Random();
        System.out.println(rnd.nextInt());
        System.out.println(rnd.nextDouble());
        System.out.println(rnd.nextDouble());
        System.out.println(rnd.nextFloat());
        System.out.println(rnd.nextFloat());
    }
}
