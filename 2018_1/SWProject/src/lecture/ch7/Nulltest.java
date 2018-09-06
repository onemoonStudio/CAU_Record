package lecture.ch7;

public class Nulltest {
    public static void main(String[] args) {
        Nulltest test = new Nulltest();
        int x;
        // System.out.println("x is -> " + x);
        // Error - not initiallized


    }

    public int a;
    public boolean b;
    public char c;

    public Nulltest(){

//        this.c = '\u0032';

        System.out.println(this.a);
        System.out.println(this.b);
        System.out.println(this.c);
    }
}
