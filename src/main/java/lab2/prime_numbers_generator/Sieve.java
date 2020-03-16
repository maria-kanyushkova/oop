package lab2.prime_numbers_generator;

import java.util.ArrayList;
import java.util.List;

public class Sieve {
    public static List<Boolean> sieving(int upperBound) {
        List<Boolean> sieve = new ArrayList<>();
        for (int i = 0; i <= upperBound; i++) {
            sieve.add(true);
        }
        for (int i = 2; i <= upperBound; i++) {
            if (sieve.get(i)) {
                for (int j = 2; j <= upperBound; j++) {
                    int index = i * j;
                    if (index > upperBound) {
                        continue;
                    }
                    sieve.set(index, false);
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
