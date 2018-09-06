package lecture.ch6;

public class Sum_by_col {
    public static void main(String[] args) {
        int matrix[][] = {
                {1,2,3,4,5},
                {1,2,3,4},
                {1,2,3},
                {1,2},
                {1}
        };

        int total = 0;
        for(int col =0; col < matrix[0].length; col++){
            for(int row = 0; row < matrix.length;row++){
                if(matrix[row].length > col)
                    total += matrix[row][col];
            }
        }
        System.out.println(total);
    }

}
