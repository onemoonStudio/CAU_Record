package lecture.ch8;

public class SBTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello world");
        sb.append(" hello java",0,11);
        System.out.println(sb.toString());
        sb.delete(0,sb.length());
        System.out.println(sb.toString());
        sb.append("lleoll");
        System.out.println(sb.toString());

        System.out.println((sb.equals(sb.reverse().toString()))? true : false );
    }
}
