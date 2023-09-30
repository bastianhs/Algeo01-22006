package src;

public abstract class MultipleLinearRegression {
    public static matriks findEquation(matriks sampleData) {
        matriks normalEstimationEquation = findNormalEstimationEquation(sampleData);
        return spl.gauss(normalEstimationEquation);
    }

    public static double predict(matriks equation, matriks independentVariables) {
        double sum = 0;
        for (int i = 0; i < equation.getkolom(); i++) {
            double term = equation.getelmt(0, i);
            if (i > 0) {
                term *= independentVariables.getelmt(0, i - 1);
            }
            sum += term;
        }

        return sum;
    }

    private static matriks findNormalEstimationEquation(matriks sampleData) {
        matriks normalEstimationEquation = new matriks(sampleData.getkolom(), sampleData.getkolom() + 1);
        for (int i = 0; i < normalEstimationEquation.getbaris(); i++) {
            for (int j = i; j < normalEstimationEquation.getkolom(); j++) {
                double element = calculateElementOfNEEMatrix(sampleData, i, j);
                normalEstimationEquation.setelmt(i, j, element);
                if (i != j && j < normalEstimationEquation.getkolom() - 1) {
                    normalEstimationEquation.setelmt(j, i, element);
                }
            }
        }

        return normalEstimationEquation;
    }


    /*
     * NEE is an abbreviation of Normal Estimation Equation
     */
    private static double calculateElementOfNEEMatrix(matriks sampleData, int indexRow, int indexColumn) {
        int numberOfRowOfNEEMatrix = sampleData.getkolom();
        int numberOfColumnOfNEEMatrix = sampleData.getkolom() + 1;
        double sum = 0;
        for (int i = 1; i <= sampleData.getbaris(); i++) {
            double formulaOfSigma = 1;
            if (0 < indexRow && indexRow < numberOfRowOfNEEMatrix) {
                formulaOfSigma *= sampleData.getelmt(i - 1, indexRow - 1);
            }
            
            if (0 < indexColumn && indexColumn < numberOfColumnOfNEEMatrix - 1) {
                formulaOfSigma *= sampleData.getelmt(i - 1, indexColumn - 1);
            }
            else if (indexColumn == numberOfColumnOfNEEMatrix - 1) {
                formulaOfSigma *= sampleData.getelmt(i - 1, sampleData.getkolom() - 1);
            }

            sum += formulaOfSigma;
        }

        return sum;
    }
}
