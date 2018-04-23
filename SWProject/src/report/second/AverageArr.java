//	(배열 평균) 다음과 같은 헤더를 갖고 평균을 반환하는 중복 정의된 메소드를 작성하라.
//	public static int average(int [] array);
//	public static double average(double [] array);
//	메소드 테스트를 위해 {1,2,3,4,5,6} 과 {6.0, 4.4, 1.9, 2.9, 3.4, 3.5} 를 사용하라.

// 입력 : 없음
// 출력 : 각 array 의 평균

package report.second;

public class AverageArr {
	public static void main(String[] args) {
		int intArray[] = {1,2,3,4,5,6};
		double doubleArray[] = {6.0, 4.4, 1.9, 2.9, 3.4, 3.5};
		
		System.out.println(average(intArray));
		System.out.println(average(doubleArray));
	}
	
	public static int average(int[] array) {
		int sum = 0;
		for(int el : array) {
			sum += el;
		}
		System.out.println("int array is my parameter");
		return (sum/array.length);
	}
	
	public static double average(double[] array) {
		double sum = 0;
		for(double el : array) {
			sum += el;
		}
		System.out.println("double array is my parameter");
		return (sum/array.length);
	}
}
