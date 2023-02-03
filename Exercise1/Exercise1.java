
public class Exercise1 {
     //You MUST NOT change the method headers and comply with their return types
     //Main method is just there for you to test your program, you will not be evaluated by your main method
     //You can write any method you like, and use them in suitable places, but you will only be grade for your work for the questions

    public static void main(String[] args) {
        // Question 1
/*
        int[] students = new int[10];
        for (int i = 0; i < students.length; i++) {
            students[i] = random(0, 100); //random method: gives students random grades between 0-100 (included)
        }

        System.out.println("Scores of students:");
        display(students); // It displays doubles in array


        char[] grades = grade(students);

        System.out.println("Grades of students are:");
        display(grades); // displays all characters in "grades"
*/
        // Question 2
/*
        int[] numbers = new int[8];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random(0, 10);
        }
        display(numbers);
        System.out.println("Average of numbers is: " + average(numbers));

        double[] doubleNumbers = new double[8];
        for (int i = 0; i < doubleNumbers.length; i++) {
            doubleNumbers[i] = random(-0.5, 0.5);
        }
        display(doubleNumbers);
        System.out.println("Average of numbers is: " + average(doubleNumbers));

*/
        // Question 3
/*
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random(0, 10, 1, 3, 5, 7, 9);
        }
        System.out.println("Generated numbers are: ");
        display(numbers);
*/

        // Question 4
/*
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random(0, 10, 0, 1, 3, 5, 7, 9);
        }
        System.out.println("Generated numbers with duplicate values are: ");
        display(numbers);
        int[] nonDuplicateNumbers = eliminateDuplicates(numbers);

        System.out.println("Non duplicate numbers");
        display(nonDuplicateNumbers);

*/

        // Question 5
/*
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        System.out.println("is array in sorted order: " + isSorted(numbers));
        display(numbers);

        shuffle(numbers);

        System.out.println("is array in sorted order: " + isSorted(numbers));
        display(numbers);

*/
        // Question 6
/*
        int n = 100;
        boolean[] lockers = new boolean[n];
        int[] openLockers = lockers(lockers);

        System.out.println("Open lockers are:");
        display(openLockers);

        System.out.println("for n == 100, open locker amount should be 10: open locker amount: " + openLockers.length);

        n = 1000;
        lockers = new boolean[n];
        openLockers = lockers(lockers);

        System.out.println("for n == 1000, open locker amount should be 31, open locker amount: " + openLockers.length);
*/
    }

    // Question 1: Assign grades
    public static char[] grade(int[] students) {
        /*
         * Write a method that gets student scores and finds the best score
         * then, assigns grades based on the following scheme:
         *      grade is A if score is >= best - 10;
         *      grade is B if score is >= best - 20;
         *      grade is C if score is >= best - 30;
         *      grade is D if score is >= best - 40;
         *      grade is F otherwise
         *
         * Args: students int[]: array of scores
         * Returns: char[]: array of grades
*/
        char[] grades = new char[students.length];
        int best = getMax(students);
        for (int i = 0; i < students.length; i++) {
            if (students[i] >= (best - 10)) {
                grades[i] = 'A';
            } else if (students[i] >= (best - 20)) {
                grades[i] = 'B';
            } else if (students[i] >= (best - 30)) {
                grades[i] = 'C';
            } else if (students[i] >= (best - 40)) {
                grades[i] = 'D';
            } else {
                grades[i] = 'F';
            }
        }
        return grades;
    }

    // finds the maximum value from the given array
    public static int getMax(int[] array) {
        /*
         * Helper method for finding the maximum value from an array
         * 
         * Args: array int[]: array of integers
         * Returns: int: maximum value in the array
         */
        int maximum= array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }

    // Question 2: Average an array
    public static int average(int[] array) {
        /*
         * Write a method that returns the average of an array
         * 
         * Args: array int[]: array to average
         * Returns: int: average of the array
         */
        // Your code goes here...

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return (sum / array.length);

    }

    // Overload the above method for double data type
    public static double average(double[] array) {
        /*
         * Write a method that returns the average of an array
         * 
         * Args: array double[]: array to average
         * Returns: double: average of the array
         */

        // Your code goes here...

        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return (sum / array.length);

    }

    // Question 3: Random number chooser
        public static int random(int start, int end, int... exclude) {
            /*
             * Write a method that returns a random number between start and end,
             * excluding the numbers passed in the argument exclude. if a number generated
             * is one of the excluded numbers, then generate another number.
             *
             * Args:
             *      start int: the start of the number to generate
             *      end int: the end of the number to generate
             *      exclude int[optional]: the numbers to exclude
             *
             * Returns: int: the number generated that is not one of the exluded numbers
             */

             // Your code goes here...

        while (true) {
            int number = random(start, end);
            boolean isExcluded = false;
            for (int i = 0; i < exclude.length; i++) {
                if (number == exclude[i]) {
                    isExcluded = true;
                    break;
                }
            }
            if (!isExcluded) {
                return number;
            }
        }

    }

    // Question 4: Eliminate duplicates
    public static int[] eliminateDuplicates(int[] array) {
        /*
         * Write a method that returns a new array by eliminating the duplicate
         * values in the array. Order of appearences for numbers must not change
         * 
         * Args: array int[]: array containing duplicate elements
         * Returns: int[]: new array that does not contains duplicate elements, but
         * keeps the order of appearences of numbers.
         */

        // Your code goes here...

        for(int i=0;array.length>i;i++)
            {
                for(int j=0;array.length>j;j++)
                {
                    if(i!=j && array[i] == array[j])
                    {
                        array[j]=-1;
                    }
                }
            }
        int notMinus1=0;
        for(int i=0;array.length>i;i++)// find how many of them is not -1
        {
            if(array[i]!=-1){
                notMinus1++;
            }
        }
        int[] newArray = new int[notMinus1];
        for(int i=0,j=0;i<array.length;i++)
        {
            if(array[i]!=-1)
            {
                newArray[j++] = array[i];
            }
        }

        return newArray;
    }

    // Question 5: Sorted?
    public static boolean isSorted(int[] array) {
        /*
         * Write a method that returns true if the array is already sorted in increasing order,
         * false otherwise
         * 
         * Args: array int[]: array of numbers
         * Returns: boolean
         */

        // Your code goes here...
        for (int i = 0; i < (array.length - 1); i++) {
            if (array[i]>array[(i+1)])
                return false;
        }
        return true;
    }

    // shuffle the array randomly
    public static void shuffle(int[] array) {
        /*
         * Write a method that shuffles the array randomly
         */

        // Your code goes here...
        for(int i = 0; i < array.length; i++) {
            int randomIndex = random(0, array.length - 1);
            swap(array, i, randomIndex);
        }
    }

    // Question 6: Locker puzzle
    public static int[] lockers(boolean[] locker) {

        for(int i = 0; i < locker.length; i++) {
            for(int j = i; j < locker.length; j += i + 1) {
                locker[j] = !locker[j];
            }
        }
        int count = 0;
        for (int i = 0; i < locker.length; i++) {
            if (locker[i]) {
                count++;
            }
        }

        int[] openLockersPlace = new int[count];
        int j = 0;

        for(int i = 0; i < locker.length; i++) {
            if (locker[i]) {
                openLockersPlace[j++] = i+1;
            }
        }
        return openLockersPlace;
    }

    ////////////////////// HELPER FUNCTIONS //////////////////////

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
        // It displays doubles in array
        for (int i : array) {
            System.out.printf("%d\t", i);
        }
        System.out.println();
    }

    public static void display(char[] array) {
        // It displays all characters in array
        for (int i : array) {
            System.out.printf("%c\t", i);
        }
        System.out.println();
    }

    public static int random(int start, int end) {
        return start + (int) (Math.random() * (end - start + 1));
    }

    public static double random(double start, double end) {
        //return a random double between -0,5, 0,5
        return start + Math.random() * (end - start);

    }

    public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}
