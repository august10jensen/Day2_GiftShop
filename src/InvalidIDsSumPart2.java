import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InvalidIDsSumPart2 {
    public static long findInvalidID(File input){
        long sum = 0;
        int runCount = 0;

        try (Scanner scan = new Scanner(input).useDelimiter("\\D")) {
            while (scan.hasNext()) {
                long start = scan.nextLong();
                long end = scan.nextLong();

                int endDigits = longDigits(end);

                for (int splits = 2; splits <= endDigits; splits++) {
                    long repeat = firstFraction(start,splits);

                    while (true){
                        int repeatL = longDigits(repeat);
                        long candidate = buildCandidate(repeat, repeatL, splits);
                        runCount++;

                        if (candidate > end) {
                            break;
                        }

                        if (candidate >= start && isPrimetive(repeat)) {
                            sum += candidate;
                        }

                        repeat ++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FUCKKKK ITS ALL GONE WRONG: " + e.getMessage());
        }

        System.out.println(runCount);
        return sum;
    }

    private static long buildCandidate(long block, int digits, int repeats){
        long result = 0;
        for (int i = 0; i < repeats; i++) {
            result = result * pow10(digits) + block;
        }
        return result;
    }

    private static int longDigits(long n){
        return Long.toString(n).length();
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

    private static boolean isPrimetive(long repeat) {
        String s = Long.toString(repeat);
        String ss = s + s;

        return ss.indexOf(s, 1) == s.length();
    }
}
