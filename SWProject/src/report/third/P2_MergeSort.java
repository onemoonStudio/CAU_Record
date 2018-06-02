package report.third;

public class P2_MergeSort {

	public static void main(String[] args) {
		int A[][] = new int[2][10];
		for(int i=0;i<20;i++) {
//			if(i==10) System.out.println(" ");
			if(i < 10 ) {
				A[0][i] = (int)(Math.random()*100);
			}else {
				A[1][i-10] = (int)(Math.random()*100);
			}
		}
		
		// Arr is initialized
		selectionSort(A,1);
		merge(A);
		
		for(int i=0;i<2;i++) {
			for(int k=0;k<10;k++) {
				System.out.print(A[i][k]+" ");
			}
			System.out.println();
		}
			
		
		
	}
	
	public static void selectionSort(int A[][],int n) {
		int len = 10;
		
		for(int p=0;p<=n;p++) {
			for(int i=len-1;i>0;i--) {
				int max = 0;
				
				for(int k=0;k<i;k++) {
					if(A[p][max]<=A[p][k]) {max = k;}
				}
				
				if(A[p][i] <= A[p][max]) {
					int temp = A[p][i];
					A[p][i] = A[p][max];
					A[p][max] = temp;
				}
			}
		}
		
	}
	
	public static void merge(int A[][]) {
		int i=0; int j=0; int t=0;
		int newA[] = new int[20];
		while(i<10 && j<10) {
			if(A[0][i] < A[1][j]) { newA[t++] = A[0][i++];}
			else {newA[t++] = A[1][j++];}
		}
		
		while(i<10) newA[t++] = A[0][i++];
		while(j<10) newA[t++] = A[0][j++];
		
		t=0;
		while(t<20) {
			if(t<10) A[0][t] = newA[t++];
			else A[1][t-10] = newA[t++];
		}
		
	}

}
