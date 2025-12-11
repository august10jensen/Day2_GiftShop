import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class InvalidIDsSum {
    public static long findInvalidID(File input){
        long sum = 0;
        int numOfRuns = 0;

        try (Scanner scan = new Scanner(input).useDelimiter("\\D")) {
            while (scan.hasNext()) {
                long start = scan.nextLong();
                long end = scan.nextLong();

                long repeat = firstFraction(start,2);

                while(true){
                    int repeatL = Long.toString(repeat).length();
                    long candidate = repeat * pow10(repeatL) + repeat;

                    if (candidate > end){
                        break;
                    }
                    if (candidate >= start){
                        sum += candidate;
                    }
                    repeat ++;

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FUCKKKK ITS ALL GONE WRONG: " + e.getMessage());
        }

        return sum;
    }
    private static long firstFraction(Long input, int fractions){
        String s = Long.toString(input);
        int half = s.length()/fractions;

        if (half == 0){
            return 1;
        }
        return Long.parseLong(s.substring(0, half));
    }

    private static long pow10(int exponent) {
        return (long) Math.pow(10, exponent);
    }
}
