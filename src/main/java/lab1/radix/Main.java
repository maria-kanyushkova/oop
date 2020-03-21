package lab1.radix;

public class Main {
    public static void main(String[] args) {
        try {
            RadixDTO radixDTO = parseArgs(args);
            Utils.validateCorrectnessOfNumberSystems(
                    radixDTO.getFromRadix(),
                    radixDTO.getToRadix()
            );
            Utils.validateValueOnNumberSystem(
                    radixDTO.getValue(),
                    radixDTO.getFromRadix()
            );
            System.out.println(Converter.convert(
                    radixDTO.getValue(),
                    radixDTO.getFromRadix(),
                    radixDTO.getToRadix()
            ));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }


    private static RadixDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <from> <to> <value>");
        }
        return new RadixDTO(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]),
                args[2]
        );
    }
}
