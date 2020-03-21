package lab2.prime_numbers_generator;

public class PrimeNumberDTO {
    private int upperBound;

    public PrimeNumberDTO(int upperBound) throws IllegalArgumentException {
        setUpperBound(upperBound);
    }

    private void setUpperBound(int maxLimit) {
        upperBound = maxLimit;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
