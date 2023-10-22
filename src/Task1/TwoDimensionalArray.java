package Task1;

public class TwoDimensionalArray {
    public int[][] add(int[][] a, int[][] b) {
        isSameDimensions(a, b);
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public int[][] subtract(int[][] a, int[][] b) {
        isSameDimensions(a, b);
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;

    }

    public int[][] multiply(int[][] a, int[][] b) {
        isMultipliedTogether(a, b);
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public int[][] transpose(int[][] a) {
        /*
        - chuyển các phần tử mảng 2 chiều vào mảng 1 chiều
        - lấy các phần tử trong mảng 1 chiều gán vào mảng 2 chiều mới khởi tạo
        int[][] result = new int[a[0].length][a.length];
        int[] temp = new int[a.length * a[0].length];
        int index = 0;
        for (int i = 0; i < a[0].length; i++) {
            for (int[] ints : a) {
                temp[index++] = ints[i];
            }
        }
        index = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = temp[index++];
            }
        }
        return result;
        */
        int[][] result = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    public static void isMultipliedTogether(int[][] a, int[][] b) {
        // kiểm tra điều kiện nhân 2 ma trận, chưa xét trường hợp các ma trận không đêù
        for (int[] ints : a) {
            if (ints.length != b.length) {
                throw new IllegalArgumentException("Multiplication of two matrices is only possible when the number of columns in the first matrix is equal to the number of rows in the second matrix.");
            }
        }
    }

    private static void isSameDimensions(int[][] a, int[][] b) {
        // kiểm tra điều kiện 2 ma trận có cùng kích thước hay không
        if (a.length != b.length) {
            throw new IllegalArgumentException("The matrices must have the same dimensions");
        } else {
            for (int i = 0; i < a.length; i++) {
                if (a[i].length != b[i].length) {
                    throw new IllegalArgumentException("The matrices must have the same dimensions");
                }
            }
        }
    }
}
