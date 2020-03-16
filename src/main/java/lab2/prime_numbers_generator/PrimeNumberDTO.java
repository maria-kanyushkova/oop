package lab2.prime_numbers_generator;

public class PrimeNumberDTO {
    private static int upperBound;

    public PrimeNumberDTO(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <upperBound>");
        }
        setUpperBound(Integer.parseInt(args[0]));
    }

    private static void setUpperBound(int maxLimit) {
        upperBound = maxLimit;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
