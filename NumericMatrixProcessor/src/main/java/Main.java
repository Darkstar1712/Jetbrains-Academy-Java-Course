import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }

    //Runs the program
    public static void start() {
        while (true) {
            double[][] matrix1;
            double[][] matrix2;
            double constant;
            Scanner sc = new Scanner(System.in);

            System.out.println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit\nYour choice: ");
            int command = sc.nextInt();

            if (command == 0) {
                break;
            }

            switch (command) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    //Instantiate the matrix
                    System.out.println("Enter size of matrix: ");
                    matrix1 = createMatrix(sc.nextInt(), sc.nextInt());

                    //Create the matrix
                    System.out.println("Enter matrix: ");
                    matrix1 = fillMatrix(matrix1);

                    //Instantiate the constant
                    System.out.println("Enter constant: ");
                    constant = sc.nextDouble();
                    matrix1 = multiplyMatrixByAConstant(matrix1, constant);

                    System.out.println("The result is: ");
                    for (int i = 0; i < matrix1.length; i++) {
                        for (int j = 0; j < matrix1[i].length; j++) {
                            System.out.print((matrix1[i][j] + " "));
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    //Instantiate the first matrix
                    System.out.println("Enter size of first matrix: ");
                    matrix1 = createMatrix(sc.nextInt(), sc.nextInt());

                    //Create the first matrix
                    System.out.println("Enter first matrix: ");
                    matrix1 = fillMatrix(matrix1);

                    //Instantiate the second matrix
                    System.out.println("Enter size of second matrix: ");
                    matrix2 = createMatrix(sc.nextInt(), sc.nextInt());

                    //Create the second matrix
                    System.out.println("Enter second matrix: ");
                    matrix2 = fillMatrix(matrix2);

                    multiplyMatrices(matrix1, matrix2);
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    double[][] matrix;
                    double determinant;

                    System.out.println("Enter matrix size: ");
                    matrix = createMatrix(sc.nextInt(), sc.nextInt());

                    if (matrix.length ==  matrix[0].length) {
                        System.out.println("Enter matrix: ");
                        matrix = fillMatrix(matrix);
                    } else {
                        System.out.println("ERROR");
                        break;
                    }

                    determinant = calculateDeterminant(matrix);

                    if(determinant == 0) {
                        System.out.println("ERROR");
                    } else {
                        System.out.println("The result is: ");
                        System.out.println(determinant);
                    }
                    break;
                case 6:
                    matrix1 = inverseMatrix();
                    System.out.println("The result is: ");
                    for (int i = 0; i < matrix1.length; i++) {
                        for (int j = 0; j < matrix1[i].length; j++) {
                            System.out.printf("%.2f ", matrix1[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println();
            }
        }
    }

    public static double[][] createMatrix(int rows, int columns) {
        return new double[rows][columns];
    }

    public static double[][] fillMatrix(double[][] matrix)  {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        return matrix;
    }

    //Outputs the sum of two matrices
    public static void addMatrices() {
        Scanner sc = new Scanner(System.in);
        double[][] matrix1;
        double[][] matrix2;

        //Instantiate the first matrix
        System.out.println("Enter size of first matrix: ");
        matrix1 = createMatrix(sc.nextInt(), sc.nextInt());

        //Create the first matrix
        System.out.println("Enter first matrix: ");
        matrix1 = fillMatrix(matrix1);

        //Instantiate the second matrix
        System.out.println("Enter size of second matrix: ");
        matrix2 = createMatrix(sc.nextInt(), sc.nextInt());

        //Create the second matrix
        System.out.println("Enter second matrix: ");
        matrix2 = fillMatrix(matrix2);

        //Checks that the matrices contain the same amount of rows
        if (matrix1.length == matrix2.length) {
            //Checks that the matrices contain the same amount of columns
            for (int i = 0; i < matrix1.length; i++) {
                if (matrix1[i].length != matrix2[i].length) {
                    System.out.println("ERROR");
                    break;
                }
            }

            //Prints the sum of each element of the matrices
            System.out.println("The result is: ");
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    System.out.print((matrix1[i][j] + matrix2[i][j]) + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("ERROR");
        }
    }

    public static double[][] multiplyMatrixByAConstant(double[][] matrix, double constant) {
        //Prints the multiple of each element of the matrix by the constant
        double[][] matrix2 = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix2[i][j] = matrix[i][j] * constant;
            }
        }
        return matrix2;
    }

    public static void multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        Scanner sc = new Scanner(System.in);
        double dotProduct;

        if (matrix1[0].length == matrix2.length) {
            System.out.println("The result is: ");
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2[0].length; j++) {
                    dotProduct = 0;
                    for (int k = 0; k < matrix2.length; k++) {
                        dotProduct += matrix1[i][k] * matrix2[k][j];
                    }
                    System.out.print(dotProduct + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("ERROR");
        }
    }
    public static void transposeMatrix() {
        Scanner sc = new Scanner(System.in);
        double[][] matrix1;
        double[][] matrix2;
        int command;

        System.out.println("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4. Horizontal line");
        command = sc.nextInt();
        System.out.println("Enter matrix size: ");
        matrix1 = createMatrix(sc.nextInt(), sc.nextInt());

        System.out.println("Enter the matrix: ");
        matrix1 = fillMatrix(matrix1);

        System.out.println("The result is: ");

        switch (command) {
            case 1:
                matrix2 = new double[matrix1[0].length][matrix1.length];
                for (int i = 0; i < matrix1.length; i++) {
                    for (int j = 0; j < matrix1[i].length; j++) {
                        matrix2[i][j] = matrix1[j][i];
                        System.out.print(matrix2[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            case 2:
                matrix2 = new double[matrix1[0].length][matrix1.length];
                for (int i = 0; i < matrix1.length; i++) {
                    for (int j = 0; j < matrix1[0].length; j++) {
                        matrix2[i][j] = matrix1[Math.abs(j - (matrix1[0].length - 1))][Math.abs(i - (matrix1.length - 1))];
                        System.out.print(matrix2[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            case 3:
                matrix2 = new double[matrix1[0].length][matrix1.length];
                for (int i = 0; i < matrix1.length; i++) {
                    for (int j = 0; j < matrix1[0].length; j++) {
                        matrix2[i][j] = matrix1[i][Math.abs(j - (matrix1[0].length - 1))];
                        System.out.print(matrix2[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            case 4:
                matrix2 = new double[matrix1.length][matrix1[0].length];
                for (int i = 0; i < matrix1.length; i++) {
                    for (int j = 0; j < matrix1[0].length; j++) {
                        matrix2[i][j] = matrix1[Math.abs(i - (matrix1.length - 1))][j];
                        System.out.print(matrix2[i][j] + " ");
                    }
                    System.out.println();
                }
        }
    }

    public static double calculateDeterminant(double[][] matrix) {
        double determinant = 0;

        if (matrix.length == 1) {
            determinant = matrix[0][0];
        } else if (matrix.length == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }  else {
            for (int i = 0 ; i < matrix.length; i++) {
                double[][] tempMatrix = new double[matrix.length - 1][matrix[0].length - 1];
                for (int  j = 0; j < tempMatrix.length; j++) {
                    int index = 0;
                    for (int k = 0; index < tempMatrix[0].length; k++) {
                        if (k != i) {
                            tempMatrix[j][index] = matrix[j + 1][k];
                            index++;
                        }
                    }
                }

                if (i % 2 != 0) {
                    determinant -= matrix[0][i] * calculateDeterminant(tempMatrix);
                } else {
                    determinant += matrix[0][i] * calculateDeterminant(tempMatrix);
                }
            }
        }
        if (determinant == -0) {
            return 0;
        }
        return determinant;
    }

    public static double[][] matrixOfMinors(double[][] matrix) {
        double[][] minorMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int rowIndex = 0;
                double[][] tempMatrix = new double[matrix.length - 1][matrix[0].length - 1];
                for (int k = 0; rowIndex < tempMatrix.length; k++) {
                    if (k !=  i) {
                        int columnIndex = 0;
                        for (int l = 0; columnIndex < tempMatrix[0].length; l++) {
                            if (l  != j) {
                                tempMatrix[rowIndex][columnIndex] = matrix[k][l];
                                columnIndex++;
                            }
                        }
                        rowIndex++;
                    }
                }
                minorMatrix[i][j] = calculateDeterminant(tempMatrix);
            }
        }

        return minorMatrix;
    }

    public static double[][] inverseMatrix() {
        Scanner sc = new Scanner(System.in);
        double[][] matrix;
        double[][] minorMatrix;
        double determinant;
        double constant;

        System.out.println("Enter matrix size: ");
        matrix = createMatrix(sc.nextInt(), sc.nextInt());

        System.out.println("Enter the matrix: ");
        matrix = fillMatrix(matrix);

        minorMatrix = matrixOfMinors(matrix);
        determinant = calculateDeterminant(matrix);

        for (int i = 0; i < minorMatrix.length; i++) {
            for (int j = i + 1; j < minorMatrix[i].length; j += 2) {
                minorMatrix[i][j] *= -1;
                minorMatrix[j][i] *= -1;
            }
        }

        double[][] transposedMinorMatrix = new double[minorMatrix[0].length][minorMatrix.length];
        for (int i = 0; i < minorMatrix.length; i++) {
            for (int j = 0; j < minorMatrix[i].length; j++) {
                transposedMinorMatrix[i][j] = minorMatrix[j][i];
            }
        }

        constant = 1 / determinant;
        return multiplyMatrixByAConstant(transposedMinorMatrix, constant);
    }
}

