package Task1;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        TwoDimensionalArray array = new TwoDimensionalArray();
        int[][] a = {
                {1, 2, 3},
                {3, 4, 5},
        };
        int[][] b = {
                {1, 2, 3},
                {3, 4, 5},
        };
        int[][] c = {
                {1, 2},
                {1, 2},
                {3, 4},
        };
        System.out.println(Arrays.deepToString(array.add(a, b)));
        System.out.println(Arrays.deepToString(array.subtract(a, b)));
        System.out.println(Arrays.deepToString(array.multiply(a, c)));
        System.out.println(Arrays.deepToString(array.transpose(a)));
    }
}