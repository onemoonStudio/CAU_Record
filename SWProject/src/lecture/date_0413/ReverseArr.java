package lecture.date_0413;

public class ReverseArr {
	public static void main(String[] args) {
		int src[] = {1,5,7,3,2};
		int dest[] = new int[5];
		
		reverseArr(src,dest);
		for(int k:src) {
			System.out.print(k+" ");
		}System.out.print("\n");
		for(int k:dest) {
			System.out.print(k+" ");
		}System.out.print("\n");
	}
	
	public static void reverseArr(int[] src , int[] dest) {
		for(int i=0;i<src.length;i++) {
			dest[src.length-i-1] = src[i];
		}
	}
}
