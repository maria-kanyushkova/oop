package lab2.prime_numbers_generator;

import java.util.ArrayList;
import java.util.List;

public class Sieve {
    public static List<Boolean> sieving(int upperBound) {
        if (upperBound <= 0) {
            return null;
        }
        List<Boolean> sieve = new ArrayList<>();
        for (int i = 0; i <= upperBound; i++) {
            sieve.add(true);
        }
        for (int i = 2; i <= upperBound; i++) {
            if (sieve.get(i)) {
                int j = 2;
                while (i * j <= upperBound) {
                    sieve.set(i * j, false);
                    j++;
                }
            }
        }
        return sieve;
    }

    public static void printPrimeNumbers(List<Boolean> sieve) {
        for (int i = 1; i < sieve.size(); i++) {
            if (sieve.get(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
