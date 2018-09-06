package lecture.date_0406;

public class TestMax {
    public static void main(String[] args) {
        int a = 1;
        int b = 5;
        System.out.println(Max(a,b));
    }

    public static int Max(int a, int b){
        if(a>b){
            return a;
        }
        return b;
    }
}
