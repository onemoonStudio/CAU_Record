// 종합소득 세율 계산 프로그램
// 종합소득세율표: https://blog.naver.com/oktax13/221212481844 를 활용
// 세전 급여를 입력하면 그에 해당하는 세율을 고려해 세금을 출력
// man이라는 int 값을 사용해 조금 더 보기 쉽게 만들었다.
// 특히 5억이 넘어가는 경우에는 21억이 넘을 수 있기 때문에 상황에 맞춰 long값 또한 할당해 주었다.
// 종합소득세율표에 맞춰서 switch 문으로 분기를 나눠서 계산했다.

package report.first;

import java.util.Scanner;

public class Number3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String incomeStr = sc.nextLine();
        long incomeL = 0;
        int income = 0;
        if(incomeStr.length() > 9){
            incomeL = Long.parseLong(incomeStr);
            System.out.println(incomeL);
        }else{
            income = Integer.parseInt(incomeStr);
        }

        int man = 10000;
        double incomeTax = 0;

        if(income != 0)
        if( income < 1200*man) {
            // 6%
            incomeTax = income * (0.06);
        }else if( income < 4600*man ){
            // 15% / 108
            incomeTax = income*(0.15) - 108*man;
        }else if( income < 8800*man ){
            // 24% / 522
            incomeTax = income*(0.24) - 522*man;
        }else if( income < 15000*man ){
            // 35% / 1490
            incomeTax = income*(0.35) - 1490*man;
        }else if( income < 30000*man ){
            // 38% /1940
            incomeTax = income*(0.38) - 1940*man;
        }else if( income < 50000*man ){
            // 40% /2540
            incomeTax = income*(0.40) - 2540*man;
        }else{
            // 42% / 3540
            incomeTax = income * (0.42) - 3540 * man;
        }

        if(incomeL != 0){
            incomeTax = incomeL * (0.42) - 3540 * man;
        }
        System.out.println("소득세는 " + (long)incomeTax + " 원 이며 지방세는 " + (long)(incomeTax*(0.1)) +" 원 입니다." );

    }
}

// 1200 => 6% / 0
// ~4600 => 15% / 108
// ~8800 => 24% / 522
// ~15000 => 35% / 1490
// ~30000 => 38% / 1940
// ~50000 => 40% / 2540
// 50000~ => 42% / 3540
