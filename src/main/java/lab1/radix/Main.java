package lab1.radix;

public class Main {
    public static void main(String[] args) {
        try {
            RadixDTO radixDto = new RadixDTO(args);
            System.out.println(Converter.convert(
                    radixDto.getValue(),
                    radixDto.getFromRadix(),
                    radixDto.getToRadix()
            ));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
