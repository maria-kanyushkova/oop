package lab2.prime_numbers_generator;

public class Main {
    public static void main(String[] args) {
        try {
            PrimeNumberDTO primeNumberDTO = new PrimeNumberDTO(args);
            Sieve.printPrimeNumbers(Sieve.sieving(primeNumberDTO.getUpperBound()));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
