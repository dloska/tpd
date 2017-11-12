package Zadanie2;

import java.util.Collections;

public class TwoPersonZeroSumGame {
    private static final double EPSILON = 1E-8;

    private final int m;
    private final int n;
    private LinearProgramming lp;
    private double constant;

    public TwoPersonZeroSumGame(double[][] payoff) {
        m = payoff.length;
        n = payoff[0].length;

        double[] c = new double[n];
        double[] b = new double[m];
        double[][] A = new double[m][n];
        for (int i = 0; i < m; i++)
            b[i] = 1.0;
        for (int j = 0; j < n; j++)
            c[j] = 1.0;

        constant = Double.POSITIVE_INFINITY;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (payoff[i][j] < constant)
                    constant = payoff[i][j];

        if (constant <= 0) constant = -constant + 1;
        else constant = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = payoff[i][j] + constant;

        lp = new LinearProgramming(A, b, c);

    }

    public double value() {
        return 1.0 / scale() - constant;
    }

    private double scale() {
        double[] x = lp.primal();
        double sum = 0.0;
        for (int j = 0; j < n; j++)
            sum += x[j];
        return sum;
    }

    public double[] row() {
        double scale = scale();
        double[] x = lp.primal();
        for (int j = 0; j < n; j++)
            x[j] /= scale;
        return x;
    }

    public double[] column() {
        double scale = scale();
        double[] y = lp.dual();
        for (int i = 0; i < m; i++)
            y[i] /= scale;
        return y;
    }

    private static void test(String description, double[][] payoff) {
        StdOut.println();
        StdOut.println(description);
        StdOut.println("------------------------------------");
        int m = payoff.length;
        int n = payoff[0].length;
        double maxMin = WaldCriterion.computeMaxMin(payoff);
        double minMax = WaldCriterion.computeMinMax(payoff);

        StdOut.print("maxMin: " + maxMin);
        StdOut.print("minMax: " + minMax);

        if (maxMin == minMax) {
            System.out.println("\nZnaleziono punkt siodłowy: " + minMax + "\n");
        }

        TwoPersonZeroSumGame zerosum = new TwoPersonZeroSumGame(payoff);
        double[] x = zerosum.row();
        double[] y = zerosum.column();

        StdOut.print("x[] = [");
        for (int j = 0; j < n - 1; j++)
            StdOut.printf("%8.4f, ", x[j]);
        StdOut.printf("%8.4f]\n", x[n - 1]);

        StdOut.print("y[] = [");
        for (int i = 0; i < m - 1; i++)
            StdOut.printf("%8.4f, ", y[i]);
        StdOut.printf("%8.4f]\n", y[m - 1]);

        double outputValue = zerosum.value();
        int desiredValue = 4;
        if (outputValue == desiredValue) {
            StdOut.println("DESIRED VALUE = " + desiredValue + " FOUND =  " + outputValue);
        } else {
            StdOut.println("value =  " + outputValue);
        }

    }

    private static void test6(int inputA) {
        double[][] payoff = {
                {-4, 5, 6},
                {inputA, 3, 3},
                {-5, 4, 5}
        };

        test("Zad2 for parameter inputA of " + inputA, payoff);
    }

    public static void main(String[] args) {

        for (int i = -300; i < 300; i++) {
            test6(i);
        }

    }

}
