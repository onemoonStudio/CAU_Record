package lecture.ch6;

public class SelectionSort {
	public static void main(String[] args) {
		int A[] = {10,64,23,12,75,1,5,2};
		selectionSort2(A);
		for(int el :A) {
			System.out.print(el+" " );
		}
	}
	
	public static void selectionSort(int A[]) {
		int len = A.length;
		for(int i =0; i<len ;i++) {
			// 0 ~ n-1
			for(int k=0;k<len-1-i;k++) {
				if(A[k] > A[len-1-i]) {
					int temp = A[k];
					A[k] = A[len-1-i];
					A[len-1-i] = temp;
				}
			}
		}
	}
	
	public static void selectionSort2(int A[]) {
		int len = A.length;
		for(int i=len-1;i>=0;i--) {
			for(int k=0;k<i;k++) {
				if( A[k] > A[k+1] ) {
					int temp = A[k];
					A[k] = A[k+1];
					A[k+1] = temp;
				}
			}
		}
	}
	
}
