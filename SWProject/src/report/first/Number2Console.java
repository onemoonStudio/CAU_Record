// 급여대장 작성 프로그램 - 콘솔 활용
// 콘솔에 5가지 값을 받는다.
// 이름 , 주당 일한시간 , 시간당 급여 , 정부 세금 , 지방 세금
// string 값으로 받은 후 double 이나 int형 등으로 적절한 변형을 통해서 계산
// 특히 소수점 표현 시 println 이 아닌 printf 를 사용 ( 소수점 2자리 까지 표현 )
// 계산시에는 int형으로 변환 후 다시 소수점으로 표현

package report.first;

import java.util.Scanner;

public class Number2Console {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name;
        int workedInWeek;
        int payRate;
        int federalTax;
        int stateTax;

        System.out.print("Enter employee\'s name :");
        name = sc.next();
        System.out.print("Enter number of hours worked in a week:");
        workedInWeek = Integer.parseInt(sc.next());
        System.out.print("Enter hourly pay rate :");
        payRate = (int)(Double.parseDouble(sc.next())*100);
        System.out.print("Enter federal tax wirhholding rate :");
        federalTax = (int)(Double.parseDouble(sc.next())*100);
        System.out.print("Enter state tax withholding rate :");
        stateTax = (int)(Double.parseDouble(sc.next())*100);

        System.out.println("Employee Name : " + name);
        System.out.println("Hours Worked : " + workedInWeek);
        System.out.printf("Pay Rate : $%.2f \n" , (double)payRate/100);
        System.out.printf("Gross Pay : $%.1f \n",(double)(payRate*workedInWeek)/100 );

        System.out.println("Deductions :");

        System.out.printf("\tFederal withholding ( %.2f" , (double)(federalTax)/100);
        System.out.print("%");
        System.out.printf(" ) : %.2f\n", (double)(payRate*workedInWeek*federalTax)/10000);

        System.out.printf("\tState withholding ( %.2f" , (double)stateTax/100);
        System.out.print("%");
        System.out.printf(" ) : %.2f\n" , (double)(payRate*workedInWeek*stateTax)/10000);

        System.out.printf("\tTotal Deduction : $%.2f\n",(double)(payRate*workedInWeek)*(federalTax + stateTax)/10000);

        System.out.printf("Net Pay : \t%.2f \n" , (double)(payRate*workedInWeek)*(100 - federalTax - stateTax)/10000);

    }
}
