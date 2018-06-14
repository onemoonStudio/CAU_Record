package lecture.ch10;

public class Apple extends Fruit {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        //Fruit test = new Fruit("test");
        // main 문 실행은 되지 않는다. 객체 생성만 되는 것 같다.

    }
    public Apple(){
        super("java");
        System.out.println("hello apple");

    }
}


