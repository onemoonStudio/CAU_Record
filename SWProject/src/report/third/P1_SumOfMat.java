package report.third;

public class P1_SumOfMat {

	public static void main(String[] args) {
		int arr[][] = new int[4][4];
		for(int i=0;i<4;i++) {
			for(int k=0;k<4;k++) {
				arr[i][k] = i*4 + k+1; 
			}
		}
		int sum1=0;
		int sum2=0;
		for(int i=0;i<4;i++) {
			sum1 += arr[i][3-i];
			sum2 += arr[i][i];
		}
		
		System.out.println(sum1);
		System.out.println(sum2);
	}

}
