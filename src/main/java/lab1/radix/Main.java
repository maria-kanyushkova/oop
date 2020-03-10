package lab1.radix;

public class Main {
    public static void main(String[] args) {
        try {
            RadixDTO radixDTO = new RadixDTO(args);
            System.out.println(Converter.convert(
                    radixDTO.getValue(),
                    radixDTO.getFromRadix(),
                    radixDTO.getToRadix()
            ));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
