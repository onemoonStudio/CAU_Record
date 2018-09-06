package lecture.ch6;

public class BinarySearch {

	public static void main(String[] args) {
		int A[] = {1,3,7,8,10,15,25,32};
		System.out.println(A.length);
		
		System.out.println(binSearch(A,31));
	}
	
	public static int binSearch(int A[], int key) {
		
		int low , mid, high;
		low = 0; high = A.length-1;
		while(high >= low) {
			mid = (low+high)/2;
			if(A[mid] == key)
				return mid;
			else if(A[mid] > key )
				high = mid-1;
			else 
				low = mid+1;
		}
		return -low-1;

	}
}