package regression.model;

import lombok.Getter;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.util.Arrays;

public class RegressionModel {

    @Getter
    private OLSMultipleLinearRegression regression;

    public RegressionModel() {
        this.regression = new OLSMultipleLinearRegression();
    }

    public void fit(double[][] independentVariables, double[] dependentVariable) {
        if (independentVariables.length != dependentVariable.length) {
            throw new IllegalArgumentException("Independent variables must have the same length as the dependent variable.");
        }
        regression.newSampleData(dependentVariable, independentVariables);
    }

    public double[] predict(double[][] newInputs) {
        double[] beta = regression.estimateRegressionParameters();
        double[] predictions = new double[newInputs.length];

        if (newInputs.length != beta.length - 1) {
            throw new IllegalArgumentException("Inputs must have the same length as the regression coefficients.");
        }

        for (int i = 0; i < newInputs.length; i++) {
            predictions[i] = beta[0]; // Intercept
            for (int j = 0; j < newInputs[i].length; j++) {
                predictions[i] += beta[j + 1] * newInputs[i][j];
            }
        }
        return predictions;
    }

    public void evaluateModel() {
        var regParams = regression.estimateRegressionParameters();
        System.out.println("Intercept: " + regParams[0]);
        System.out.println("Regression coefficients: " + Arrays.toString(Arrays.copyOfRange(regParams, 1, regParams.length)));
        System.out.println("R-squared: " + regression.calculateRSquared());
        System.out.println("Adjusted R-squared: " + regression.calculateAdjustedRSquared());
        System.out.println("STD error: " + regression.estimateRegressionStandardError());
    }
}
