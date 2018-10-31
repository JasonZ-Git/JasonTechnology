package crack_code_interview;

public class Q1_7_Rotate_Matrix {

    public static int[][] flipMatrix(int[][] original) {
        if (original == null || original.length == 0 || original.length != original[0].length) {
            throw new IllegalArgumentException();
        }

        if (original.length == 1) {
            return original;
        }

        int temp = 0;
        for (int i = 1; i < original.length; i++) {
            for (int j = 0; j < i; j++) {
                temp = original[i][j];
                original[i][j] = original[j][i];
                original[j][i] = temp;
            }
        }

        return original;
    }

    /**
     * [n-1-j, i] -> [i,j] -> [j, n-1-i] -> [n-1-i, n-1-j] -> [n-1-j, i]
     */
    public static int[][] rotateMatrix(int[][] original) {
        if (original == null || original.length == 0 || original.length != original[0].length) {
            throw new IllegalArgumentException();
        }

        if (original.length == 1) {
            return original;
        }
        
        int temp = 0;
        int n = original.length;
        
        for (int i = 0; i < original.length/2; i++) {
            int newI = n-1-i;
            
            for (int j = 0; j < original.length/2; j++) {
                
                int newJ = n-1-j;
                
                temp = original[i][j];
                
                original[i][j] = original[newJ][i];
                
                original[newJ][i] = original[newI][newJ];
                
                original[newI][newJ] = original[j][newI];
                
                original[j][newI] =  temp;
            }
        }
        
        return original;
    }
}
