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
            if (i > 0) term *= independentVariables.getelmt(0, i - 1);
            sum += term;
        }

        return sum;
    }

    private static matriks findNormalEstimationEquation(matriks sampleData) {
        matriks nee = new matriks(sampleData.getkolom(), sampleData.getkolom() + 1);
        for (int i = 0; i < nee.getbaris(); i++) {
            for (int j = i; j < nee.getkolom(); j++) {
                double element = elementOfNEEMatrix(sampleData, i, j);
                nee.setelmt(i, j, element);
                if (i != j && j < nee.getkolom() - 1) nee.setelmt(j, i, element);
            }
        }

        return nee;
    }

    private static double elementOfNEEMatrix(matriks sampleData, int indexRow, int indexColumn) {
        int nRowNEEMatrix = sampleData.getkolom();
        int nColumnNEEMatrix = sampleData.getkolom() + 1;
        double sum = 0;
        for (int i = 1; i <= sampleData.getbaris(); i++) {
            double formulaOfSigma = 1;
            if (0 < indexRow && indexRow < nRowNEEMatrix) {
                formulaOfSigma *= sampleData.getelmt(i - 1, indexRow - 1);
            }
            
            if (0 < indexColumn && indexColumn < nColumnNEEMatrix - 1) {
                formulaOfSigma *= sampleData.getelmt(i - 1, indexColumn - 1);
            }
            else if (indexColumn == nColumnNEEMatrix - 1) {
                formulaOfSigma *= sampleData.getelmt(i - 1, sampleData.getkolom() - 1);
            }

            sum += formulaOfSigma;
        }

        return sum;
    }
}
