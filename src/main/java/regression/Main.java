package regression;

import regression.model.RegressionModel;

public class Main {

    public static void main(String[] args) {
        // small precision test

        // simple regression where y = 10x
        double[] y = {10, 20, 30, 40, 50, 60};
        double[][] x = {{1}, {2}, {3}, {4}, {5}, {6}};

        var model = new RegressionModel();
        model.fit(x, y);
        System.out.println("y = 10x :: EVALUATION");
        model.evaluateModel();

        // multiple regression where y = 1.5 * x1 + 2 * x2 + 3
        y = new double[]{6.5, 10, 8.5, 8, 13.5, 17, 27.5, 33, 18};
        x = new double[][]{{1, 1}, {2, 2}, {1, 2}, {2, 1}, {3, 3}, {4, 4}, {7, 7}, {8, 9}, {10, 0}};

        model.fit(x, y);
        System.out.println();
        System.out.println("y = 1.5 * x1 + 2 * x2 + 3 :: EVALUATION");
        model.evaluateModel();
    }
}
