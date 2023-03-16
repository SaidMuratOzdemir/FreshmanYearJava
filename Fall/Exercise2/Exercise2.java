public class Exercise2 {
    /*
     * Change the X's in the both file name and class' name to your own
     * student number and submit your file to the assignment given through
     * Teams.
     * 
     * if you do NOT submit your assignment in time, you will recieve 0
     * if your file has compile time errors, and do not compile you will recieve 10
     * if your file does compile but a runtime error occurs, you will recieve 20
     * You MUST NOT change the method headers and comply with their return types
     * 
     * Main method is just there for you to test your program, you will not be evaluated by your main method
     * You can write any method you like, and use them in suitable places but you will only be graded
     * for your work for the questions
     */
    public static void main(String[] args) {
        // Question 1
        int[][] array2D = generateArray2D(4, 4, 0, 10);
        display2D(array2D);
        int diagonalSum = sumDiagonal(array2D);
        System.out.println("Sum of the diagonal for the given 2D array is: " + diagonalSum);


        // Question 2
        int[][] employees = generateArray2D(8, 7, 4, 9);
        display2D(employees);
        int[] workingHours = employeeWorkingHours(employees);
        display2D(employees);
        display(workingHours);


        // Question 3
        int[][] matrixA = generateArray2D(3, 2, 1, 10);
        int[][] matrixB = generateArray2D(2, 3, 1, 10);

        display2D(matrixA);
        display2D(matrixB);

        int[][] productMatrix = dotProduct(matrixA, matrixB);
        display2D(productMatrix);


        // Question 4
        double[][] points = generateArray2D(12, 5, -0.3, 0.3);
        display2D(points);
        int[] closestPoints = getClosestPoints(points, 0);
        System.out.println("Indexes of points with closest distance");
        display(closestPoints);


        // Question 5
        char[][] charArray2D = headsNtails(7);
        display2D(charArray2D);


    }

    // Question 1: Sum the major diagonal in a matrix
    public static int sumDiagonal(int[][] array2D) {
        /*
         * Write a method that sums all the numbers in the major diagonal
         * in an n x n matrix
         * 
         * Args:
         *      array2D (int[n][n]) : two dimensional array of integers
         * 
         * Returns:
         *      (int) : sum of all the numbers in the major diagonal
         */

        // Your code goes here...

        int sum = 0;
        for (int i = 0; i < array2D.length; i++) {
            sum += array2D[i][i];
        }
        return sum;

    }

    // Question 2: Compute the weekly hours for each employee
    public static int[] employeeWorkingHours(int[][] employees) {
        /*
         * Suppose the weekly hours for all employees are stored in a
         * two-dimensional array. Each row records an employee's seven-day
         * work hours with seven columns. Write a method that sorts the
         * employee's according to their sum of working hours for an entire week
         * in decreasing order. Finally, returns the sum of working hours for each
         * employee (sort both returned array and given employees array)
         * 
         * Args:
         *      employees (int[8][7]) : the array of employees. Each row contains
         *      the employee's seven day work hours.
         * 
         * Returns:
         *      int[8] : the array of sum working hours for each employee in decreasing order.
         */

        // Your code goes here...

        int[] totalHours = new int[8];


        for (int i = 0; i < employees.length; i++ ){
            for (int j = 0; j < employees[i].length; j ++){
                totalHours[i] += employees[i][j];
            }
        }

        while (true) {
            int count = 0;
            for (int i = 0; i < employees.length - 1; i++) {
                if (totalHours[i + 1] > totalHours[i]) {
                    int tempInt;
                    int[] tempArray;

                    tempInt = totalHours[i];
                    totalHours[i] = totalHours[i + 1];
                    totalHours[i + 1] = tempInt;

                    for (int j = 0; j < employees[i].length; j++) {
                        tempArray = employees[i];
                        employees[i] = employees[i + 1];
                        employees[i + 1] = tempArray;
                    }
                    i = 0;
                    count = 0;
                }
                else {
                    count++;
                }
                if (count == employees.length - 1) {
                    return totalHours;
                }
            }
        }
    }

    // Question 3: multiply two matrices
    public static int[][] dotProduct(int[][] matrixA, int[][] matrixB) {
        /*
         * Write a method to multiply two matrices.
         * 
         * Args:
         *      matrixA (int[m][n]) : the two-dimensional array of integers
         *      matrixB (int[n][k]) : the two-dimensional array of integers
         * 
         * Returns:
         *      int[m][k] : the two-dimensional array of result dot product of
         *      matrixA and matrixB
         */

        // Your code goes here...

        int[][] matrix = new int[matrixA.length][matrixB[0].length];

        for (int m = 0; m < matrixA.length; m++) {
            for (int n = 0; n < matrixB.length; n++) {
                for (int k = 0; k < matrixB[0].length; k++) {
                    matrix[m][k] += matrixA[m][n] * matrixB[n][k];
                }
            }
        }

        return matrix;
    }

    // Question 4: All closest pairs of points
    public static int[] getClosestPoints(double[][] points, int pointIndex) {
        /*
         * Write a method that returns the array indexes of the closest points to the given
         * point index. if m number of points share the same minimum distance to
         * given point index, then your array will have all of those points
         * 
         * Args:
         *      points (double[n][d]) : array of n points in d dimensional space
         *      pointIndex (int) : index of a point from points array that we will search for closest m points
         * 
         * Returns:
         *      (int[m]) : array of point indexes that share the same minimum distance to given point
         */

        // Your code goes here...

        double[] distances = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            distances[i] = distance( points[pointIndex] , points[i] );
        }
        double minDistance = distances[random(0,points.length-1,pointIndex)];

        int whileController = 0;
        while(whileController != distances.length){
            if ((whileController!=pointIndex) && (minDistance > distances[whileController])) {
                minDistance = distances[whileController];
                whileController = 0;
            }
            else {
                whileController++;
            }
        }


        int NumberOfPointsHaveSameDistance = 0;

        for (int i = 0; i < distances.length; i++) {
            if (minDistance == distances[i]){
                NumberOfPointsHaveSameDistance++;
            }
        }

        int[] closestPointsIndex = new int[NumberOfPointsHaveSameDistance];
        for (int i = 0, index = 0; i < distances.length; i++) {
            if (minDistance==distances[i]){
                closestPointsIndex[index++] = i;
            }
        }
        return closestPointsIndex;
    }

    // Calculates the euclidean distance between two points in d dimensional space
    public static double distance(double[] pointA, double[] pointB) {
        /*
         * Helper method for calculating distance between given two points
         * 
         * Args:
         *      pointA (double[d]) : a point in d-dimensional space
         *      pointB (double[d]) : a point in d-dimensional space
         * 
         * Returns:
         *      (double): distance between given two points UP TO 1 DECIMAL PLACE
         */

        // Your code goes here...

        double distance = 0;
        for (int i = 0; i < pointA.length; i++) {
            distance += (pointA[i] - pointB[i])* (pointA[i] - pointB[i]);//distance =r((x1-x2)^2 + (y1-y2)^2
        }


        return Math.round(Math.sqrt(distance*10))/10.0;//??????????
    }

    // Question 5: Nine heads and tails
    public static char[][] headsNtails(int number) {
        /*
         * Nine coins are placed in a 3-by-3 matrix with some face up and
         * some face down. You can represent the state of the coins using a 
         * 3-by-3 matrix with values 0 (heads) and 1 (tails). Here are some examples:
         *      0 0 0       1 0 1       1 0 0
         *   A: 0 1 0    B: 0 0 1   C:  1 1 1
         *      0 0 0       1 0 0       1 1 0
         * 
         *  Each state can also be represented using a binary number: For example,
         *  the preceding A, B and C matrices correspond to the numbers
         *      A: 0 0 0 0 1 0 0 0 0
         *      B: 1 0 1 0 0 1 1 0 0
         *      C: 1 0 0 1 1 1 1 1 0
         * 
         *  There are a total of 512 posibilities, so you can use
         *  decimal numbers 0, 1, 2, ..., 511 to represent all states of the matrix.
         *      for A: number == 10
         *      for B: number == 332
         *      for C: number == 318
         * 
         * Write a program that accepts a number and returns the corresponding matrix with
         * the characters H and T
         * 
         *      if the number == 7;
         *      then the binary of 7 == 0 0 0 0 0 0 1 1 1;
         *      so in matrix format it will be;
         *              0 0 0
         *              0 0 0
         *              1 1 1
         * 
         *      finally we can represent it with coins like;
         *              H0.eleman H H
         *              H H H
         *              T T T8. eleman
         * 
         * Args:
         *      number (int): the number to represent with the characters H and T in matrix format
         * 
         * Returns:
         *      (char[3][3]): the corresponding matrix representation of given number with coins
         */
        
        // Your code goes here...

        char[][] charArray = new char[3][3];
        int[]   binaryArray = decimalToBinary(number);

        for (int i = 0; i < binaryArray.length ; i++ ){
            int row = i/3;
            int col = i%3;
            if (binaryArray[i] == 1){
                charArray[row][col] = 'T';
            }
            else{
                charArray[row][col] = 'H';
            }
        }

        return charArray;
    }

    // Converts the given decimal number to a binary represented with an array
    public static int[] decimalToBinary(int number) {
        /*
         * Helper method to conver the given decimal number to binary representation
         * 
         * Args: number (int): the decimal number convert to binary
         * Returns: int[9]: the binary representation of the given number
         */

        int[] binary = new int[9];

        for (int i = 0; i < binary.length; i++) {
            if (number>=Math.pow(2,(8-i))){
                binary[i] = 1;
                number -= Math.pow(2,(8-i));
            }
        }
        return binary;
    }

    /////////////// HELPER METHODS ////////////////////////////

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void display(double[] array) {
        for (double i : array) {
            System.out.printf("%.1f\t", i);
        }
        System.out.println();
    }

    public static void display(int[] array) {
        for (int i : array) {
            System.out.printf("%d\t", i);
        }
        System.out.println();
    }

    public static void display(char[] array) {
        for (int i : array) {
            System.out.printf("%c\t", i);
        }
        System.out.println();
    }

    public static void display2D(int[][] array2D) {
        for (int[] row : array2D) {
            display(row);
        }
        System.out.println("--------------------------");
    }

    public static void display2D(double[][] array2D) {
        for (double[] row : array2D) {
            display(row);
        }
        System.out.println("--------------------------");
    }

    public static void display2D(char[][] array2D) {
        for (char[] row : array2D) {
            display(row);
        }
        System.out.println("--------------------------");
    }

    public static int[][] generateArray2D(int m, int n, int start, int end) {
        int[][] array2D = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array2D[i][j] = random(start, end);
            }
        }

        return array2D;
    }

    public static double[][] generateArray2D(int m, int n, double start, double end) {
        double[][] array2D = new double[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array2D[i][j] = round(random(start, end), 1);
            }
        }

        return array2D;
    }

    public static int random(int start, int end) {
        return start + (int) (Math.random() * (end - start + 1));
    }

    public static double random(double start, double end) {
        return start + Math.random() * (end - start + 1);
    }

    public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    public static int random(int start, int end, int exclude) {
        int number = random(start, end);
        while (true) {
            if (number != exclude) {
                break;
            }
            number = random(start, end);
        }
        return number;
    }
}
