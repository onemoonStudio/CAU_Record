package lecture.date_0406;

import java.util.Scanner;

public class GreatestCommonDivisorMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("input first variable : ");
        int a = sc.nextInt();

        System.out.print("input second variable : ");
        int b = sc.nextInt();

        System.out.print("variables gcd is : " + gcd(a,b));



    }

    public static int gcd(int a,int b){
        int gcd = 1;
        int k = 1;

        while( k <= a && k <= b){
            if( a%k ==0 && b%k ==0)
                gcd = k;

            k++;
        }
        return gcd;
    }
}
