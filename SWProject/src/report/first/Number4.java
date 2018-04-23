// 60갑자로 무슨 해인지 출력하는 프로그램
// 60 갑자에 해당하는 두개의 배열을 만들었다. a[] , b[]
// % 를 이용해서 10 그리고 12 를 나눈 나머지를 계산한다.
// 나머지가 %10 의 나머지가 0인경우 와 %12 의 나머지가 0인 경우를 기준점으로 하고 현재 년도에서 뺀 값이 계산 대상
// 나머지가 각자의 배열 인덱스로 들어가 나온 String 값을 합쳐서 콘솔에 출력
// 한번 입력 후 숫자를 입력하면 다시 계산 , 엔터를 한번 더 입력할 경우 종료된다.

package report.first;

import java.util.Scanner;

public class Number4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a[] = {"갑" , "을" , "병", "정", "무", "기", "경", "신", "임", "계"};
        String b[] = {"자", "축", "인", "묘", "진", "사", "오", "미", "신", "유", "술", "해"};
        int inputYear;
        int forA;
        int forB;
        String temp;

        System.out.println("확인하고 싶은 년도의 수를 입력해주세요");
        while((temp = sc.nextLine()) != null && temp.length() != 0) {
            inputYear = Integer.parseInt(temp);
            forA = (inputYear - 1024) % 10;
            forB = (inputYear - 1024) % 12;
            System.out.println("입력한 년도 "+inputYear+"는 "+a[forA] + b[forB] +"년 입니다.");
            System.out.println("계속하시려면 숫자를 종료하시려면 엔터를 한번 더 입력해주세요");
        }
        // 1993 -> 계유년
        System.out.println("종료되었습니다.");
    }
}
