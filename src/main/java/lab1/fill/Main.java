package lab1.fill;

import common.ArgsValidator;
import common.FileManager;
import lab1.replace.StringReplacer;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 2);
            File inputFile = FileManager.create(args[0]);
            File outputFile = FileManager.create(args[1]);

            String fileContent = "\n" +
                    "\n" +
                    "    ################\n" +
                    "   #               #\n" +
                    "   #      #####   #\n" +
                    "   #   O  #   #   ###\n" +
                    "   #      #   #     #\n" +
                    "    #######   #######\n" +
                    "\n" +
                    "                  ###\n" +
                    "        #######   # #\n" +
                    "        #     #   #  ##\n" +
                    "        #  O  #  #     #\n" +
                    "        #     #  ######\n" +
                    "        #######\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n";//FileManager.read(inputFile);
            char[][] aria = Helper.parse(fileContent);
            Helper.print(Filler.fill(aria));

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

}
