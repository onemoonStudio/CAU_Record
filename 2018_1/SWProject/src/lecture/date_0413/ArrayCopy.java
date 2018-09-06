package lecture.date_0413;

public class ArrayCopy {
	public static void main(String[] args) {
		int srcArr[] = {1,2,3,5};
		int copyedArr[] = new int[4];
		
		System.arraycopy(srcArr, 0, copyedArr, 0, srcArr.length);
		for(int k : srcArr) {
			System.out.print(k+ " ");
		}System.out.print("\n");
		for(int k : copyedArr) {
			System.out.print(k+ " ");
		}System.out.print("\n");
		
	}

}
