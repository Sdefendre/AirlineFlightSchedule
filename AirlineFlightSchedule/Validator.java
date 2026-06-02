package AirlineFlightSchedule;

import java.util.Scanner;

public class Validator {

    private static Scanner sc = new Scanner(System.in);
    public static final int INVALID_INT = Integer.MAX_VALUE;

    public static int getRandomInt(int from, int to) {
        // compute random value in range from-to
        int maxNum = to - from + 1;
        return from + (int) (Math.random() * maxNum);
    }//getRandomInt

    public static int stringToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("\t*** cannot convert [" + str + "] to int ***");
            return INVALID_INT;
        }
    }//stringToInteger

    public static int getInt2(String prompt) {
        int result;
        for (;;) {
            try {
                System.out.print(prompt);
                if (sc.hasNextInt()) {
                    result = sc.nextInt();
                    sc.nextLine(); // eliminate newline from input buffer
                    return result;
                } else {
                    if (!sc.hasNext()) {
                        handleStreamClosure();
                    }
                    System.out.println("\t*** Invalid number format ***");
                    sc.nextLine(); // trash non-numeric input
                }
            } catch (java.util.NoSuchElementException | java.lang.IllegalStateException ex) {
                handleStreamClosure();
            }
        }
    }//getInt2

    public static int getInt(String prompt) {
        for (;;) {
            try {
                System.out.print(prompt);
                int result = sc.nextInt();
                sc.nextLine(); // eliminate newline from input buffer
                return result;
            } catch (java.util.InputMismatchException ex) {
                System.out.println("\t*** Invalid number format ***");
                try {
                    sc.nextLine(); // trash non-numeric input
                } catch (java.util.NoSuchElementException | java.lang.IllegalStateException e) {
                    handleStreamClosure();
                }
            } catch (java.util.NoSuchElementException | java.lang.IllegalStateException ex) {
                handleStreamClosure();
            }
        }
    }//getInt

    public static int getInt(String prompt, int min, int max) {
        int result = 0;
        boolean isValid = false;
        while (isValid == false) {
            result = getInt(prompt);
            if (result < min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (result > max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return result;
    }//getInt
    
    public static void pause() {
        try {
            System.out.println("Press Enter to continue");
            Validator.sc.nextLine();
        } catch (java.util.NoSuchElementException | java.lang.IllegalStateException ex) {
            handleStreamClosure();
        }
    }//pause
    
    public static String getString(String prompt) {
        String result = "";
        while (result.equals("")) {
            try {
                System.out.print(prompt);
                if (!sc.hasNextLine()) {
                    handleStreamClosure();
                }
                result = sc.nextLine();
                if (result.equals("")) {
                    System.out.println("\t*** Input cannot be empty ***");
                }
            } catch (java.util.NoSuchElementException | java.lang.IllegalStateException ex) {
                handleStreamClosure();
            }
        }
        return result;
    }//getString  

    private static void handleStreamClosure() {
        System.err.println("\n*** Input stream closed. Exiting. ***");
        System.exit(1);
    }

}//class Validator
