// W 패턴 출력하기
// String 과 공백으로 W를 표현함
// 반복을 위해서 string builder 를 사용함

package report.first;

public class Number1 {
    public static void main(String[] args) {
        String line_1 = "W                  W";
        String line_2 = " W        W       W ";
        String line_3 = "  W      W W     W  ";
        String line_4 = "   W    W   W   W   ";
        String line_5 = "     W        W     ";

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<3 ;i++){
            sb.append(line_1);
            sb.append("   ");
        }
        sb.append("\n");
        for(int i = 0 ; i<3 ;i++){
            sb.append(line_2);
            sb.append("   ");
        }
        sb.append("\n");
        for(int i = 0 ; i<3 ;i++){
            sb.append(line_3);
            sb.append("   ");
        }
        sb.append("\n");
        for(int i = 0 ; i<3 ;i++){
            sb.append(line_4);
            sb.append("   ");
        }
        sb.append("\n");
        for(int i = 0 ; i<3 ;i++){
            sb.append(line_5);
            sb.append("   ");
        }

        System.out.println(sb);

    }
}
