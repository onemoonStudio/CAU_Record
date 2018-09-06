package lecture.date_0406;

public class TestVoidMethod {
    public static void main(String[] args) {
        double scoreA = 76.1;
        printGrade(scoreA);
        System.out.println("your score is " + returnGrade(scoreA));

    }

    public static void printGrade(double score){
        if (score >= 90.0) {
            System.out.println('A');
        }
        else if (score >= 80.0) {
            System.out.println('B');
        }
        else if (score >= 70.0) {
            System.out.println('C');
        }
        else if (score >= 60.0) {
            System.out.println('D');
        }
        else {
            System.out.println('F');
        }
    }

    public static char returnGrade(double score){
        if (score >= 90.0) {
            return 'A';
        }
        else if (score >= 80.0) {
            return 'B';
        }
        else if (score >= 70.0) {
            return 'C';
        }
        else if (score >= 60.0) {
            return 'D';
        }
        else {
            return 'F';
        }
    }
}
