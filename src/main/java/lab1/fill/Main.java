package lab1.fill;

import common.FileManager;

public class Main {
    public static void main(String[] args) {
        try {
            FillDTO fillDTO = new FillDTO(args);
            char[][] aria = Utils.parse(fillDTO.getFileContent());
            aria = Filler.fill(aria);
            FileManager.write(fillDTO.getOutputFile(), Utils.print(aria));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

}
