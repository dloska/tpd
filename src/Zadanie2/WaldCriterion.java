package Zadanie2;

public class WaldCriterion {

    private static double findMax(double[] input) {
        double max = Double.MIN_VALUE;
        for (double anInput : input) {
            if (anInput > max)
                max = anInput;
        }
        return max;
    }

    private static double findMin(double[] input) {
        double min = Double.MAX_VALUE;
        for (double anInput : input) {
            if (anInput < min)
                min = anInput;
        }
        return min;
    }

    public static double computeMaxMin(double[][] matrix) {
        int rowLength = matrix.length;
        double[] mins = new double[rowLength];
        for (int i = 0; i < rowLength; i++) {
            mins[i] = findMin(matrix[i]);
        }
        return findMax(mins);
    }

    public static double computeMinMax(double[][] matrix) {
        int colLength = matrix.length;
        double[] maxs = new double[colLength];

        for (int j = 0; j < colLength; j++) {
            double max = Double.MIN_VALUE;
            for (int i = 0; i < colLength; i++) {
                if (matrix[j][i] > max) {
                    max = matrix[j][i];
                    maxs[j] = matrix[j][i];
                }

            }
        }
        return findMin(maxs);
    }

}
