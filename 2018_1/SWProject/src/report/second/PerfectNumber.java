//	(완벽한수) 양의 정수가 자신을 제외한 그 수의 모든 양의 약수의 합과 같으면 완벽한 수라고 불린다. 
//	예를 들어, 6은 완벽한 수인데 6 = 3 + 2 + 1이기 때문이다. 
//	다음은 28 = 14 + 7 + 4 + 2 + 1 이다.  
//	10000보다 작은 숫자들 중에서 4개의 완벽한 수가 있다. 
//	이 네개의 모든 수를 찾아내는 프로그램을 작성하라.

// 입력 : 없음
// 출력 : 4개의 완벽한 수 

package report.second;

public class PerfectNumber {
	public static void main(String[] args) {
		for(int i=2;i<10000;i++) {
			int temp = 0;
			for(int k = i/2 ;k>1;k--) {
				if(i%k == 0) {
					temp += k;
				}
			}
			temp += 1;
			if(temp == i) {
				System.out.println(i);
			}
			
		}
		
	}

}
