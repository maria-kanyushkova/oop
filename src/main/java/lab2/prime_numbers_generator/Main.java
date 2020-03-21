package lab2.prime_numbers_generator;

public class Main {
    public static void main(String[] args) {
        try {
            PrimeNumberDTO primeNumberDTO = parseArgs(args);
            Sieve.printPrimeNumbers(Sieve.sieving(primeNumberDTO.getUpperBound()));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static PrimeNumberDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <upperBound>");
        }
        return new PrimeNumberDTO(Integer.parseInt(args[0]));
    }
}
