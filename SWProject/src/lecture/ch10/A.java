package lecture.ch10;

public class A extends B{
    public static void main(String[] args) {
        A test = new A();
        System.out.println(test.toString());
        // A < B < C

    }

    private int hello = 0;
    public int world = 1;


    public A(){
        System.out.println("A craeted");
    }

    public void wow(){
        System.out.println("hello world ! !");
    }

    @Override
    public String toString() {
        this.wow();
        return  "A class !!!";
    }
}
