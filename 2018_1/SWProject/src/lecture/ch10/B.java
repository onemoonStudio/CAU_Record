package lecture.ch10;

public class B extends C{
    public B()
    {
        this("B test!! ");
        System.out.println("B created");
    }
    public B(String s){
        System.out.println(s);
    }
    @Override
    public void otest(){
        System.out.println("b test");
    }
}
