package lecture.ch6;

public class InsertionSort {
	public static void main(String[] args) {
		int A[] = {10,64,23,12,75,1,5,2};
		insertionSort(A);
		for(int el :A) {
			System.out.print(el+" " );
		}
	}
	
	public static void insertionSort(int A[]) {
		int len = A.length;
		for(int i=1;i<len;i++) {
			int key = A[i];
			for(int k=i;k>=1;k--) {
				if(A[k-1] > A[k]) {
					A[k] = A[k-1];
					A[k-1] = key;
				}else {
					break;
				}
			}
		}
		
		
	}
}
