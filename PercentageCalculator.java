import java.util.Scanner;

/**
 * ---------------------------------------------------------------------------------
 * Class: PercentageCalculator
 * Description: A command-line Java application that calculates a student's total
 *              marks, percentage, grade, and pass/fail status based on dynamic
 *              input for multiple subjects (including Subject Names and Marks).
 * Features:    - Validates string vs integer input to prevent program crashes.
 *              - Prevents negative marks or marks exceeding the maximum limit.
 *              - Uses Arrays to store and output a structured Subject-wise result.
 * ---------------------------------------------------------------------------------
 */
public class PercentageCalculator {

    public static void main(String[] args) {
        // Initialize Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("       STUDENT RESULT CALCULATOR         ");
        System.out.println("=========================================");

        /*
         * Step 1: Get the total number of subjects.
         * Loop ensures the user enters a valid positive integer.
         */
        int numberOfSubjects = 0;
        while (numberOfSubjects <= 0) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                numberOfSubjects = scanner.nextInt();
                if (numberOfSubjects <= 0) {
                    System.out.println("Error: Number of subjects must be greater than 0.");
                }
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next(); // Clear invalid input to avoid infinite loop
            }
        }

        /*
         * Step 2: Get the maximum possible marks per subject.
         */
        int maxMarksPerSubject = 0;
        while (maxMarksPerSubject <= 0) {
            System.out.print("Enter maximum possible marks per subject: ");
            if (scanner.hasNextInt()) {
                maxMarksPerSubject = scanner.nextInt();
                if (maxMarksPerSubject <= 0) {
                    System.out.println("Error: Maximum marks must be greater than 0.");
                }
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next();
            }
        }

        // Consume the lingering newline character left by nextInt()
        scanner.nextLine();

        /*
         * Step 3: Get distinct Subject Names and validate them against the count
         */
        String[] subjectNamesArray = null;
        while (subjectNamesArray == null) {
            System.out.print("Enter Subject Names (comma separated): ");
            String namesInput = scanner.nextLine().trim();

            if (namesInput.isEmpty()) {
                System.out.println("Error: Subject names cannot be empty.");
                continue; // Restart the loop
            }

            // Split the input into an array using the comma delimiter
            String[] splitNames = namesInput.split(",");
            if (splitNames.length != numberOfSubjects) {
                System.out.println("Error: Number of subjects and names must match (" + numberOfSubjects + ").");
            } else {
                subjectNamesArray = splitNames; // Validated successfully
            }
        }

        /*
         * Step 4: Iteratively collect marks for each specific subject
         */
        int totalObtainedMarks = 0;
        int[] subjectMarks = new int[numberOfSubjects];

        System.out.println("\n--- Entering Subject Marks ---");
        for (int i = 0; i < numberOfSubjects; i++) {
            int currentMarks = -1;
            String currentSubjectStr = subjectNamesArray[i].trim();

            // Validate that the marks are between 0 and the max limit
            while (currentMarks < 0 || currentMarks > maxMarksPerSubject) {
                System.out.print("Enter marks obtained in " + currentSubjectStr + ": ");

                if (scanner.hasNextInt()) {
                    currentMarks = scanner.nextInt();
                    if (currentMarks < 0 || currentMarks > maxMarksPerSubject) {
                        System.out.println("Error: Marks should be between 0 and " + maxMarksPerSubject + ".");
                    }
                } else {
                    System.out.println("Error: Please enter a valid numerical value.");
                    scanner.next();
                }
            }
            // Store the marks and keep a running total
            subjectMarks[i] = currentMarks;
            totalObtainedMarks += currentMarks;
        }

        scanner.close();

        /*
         * Step 5: Core Calculations (Total bounds and floating-point Percentage)
         */
        int totalMaxMarks = numberOfSubjects * maxMarksPerSubject;
        double percentage = ((double) totalObtainedMarks / totalMaxMarks) * 100;

        /*
         * Step 6: Determine Grades via conditionals
         */
        char grade;
        if (percentage >= 90) grade = 'A';
        else if (percentage >= 75) grade = 'B';
        else if (percentage >= 60) grade = 'C';
        else if (percentage >= 40) grade = 'D';
        else grade = 'F';

        // Ternary operator to establish Pass/Fail status
        String resultStatus = (percentage >= 40) ? "PASS" : "FAIL";

        /*
         * Step 7: Print Formatted Output and Breakdown
         */
        System.out.println("\n==== RESULT ====\n");
        System.out.println("Total Max Marks : " + totalMaxMarks);
        System.out.println("Total Obtained  : " + totalObtainedMarks);
        System.out.printf("Percentage      : %.1f%%\n", percentage);
        System.out.println("Grade           : " + grade);
        System.out.println("Status          : " + resultStatus);

        System.out.println("\n=============================\n");
        System.out.println("Subject-wise Breakdown:");
        // Iterate through the parallel arrays to print mappings cleanly
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.printf(" - %-10s : %d / %d\n", subjectNamesArray[i].trim(), subjectMarks[i], maxMarksPerSubject);
        }
    }
}