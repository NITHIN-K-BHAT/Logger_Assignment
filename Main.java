

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


class FileReadingException extends Exception {
    public FileReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String sid;

        do {
            logger.info ("Enter symbol: ");

            sid = s.next();
            String line = "";
            boolean symbolFound = false;

            try (FileInputStream fin = new FileInputStream("C:\\Users\\Nithin.bhat\\Desktop\\loggerassignment\\nme.txt")) {

                Scanner sc = new Scanner(fin);

//
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    if (line.startsWith(sid)) {
                        logger.info(line);
                        symbolFound = true;
                    }
                }
                if (!symbolFound) {
                    logger.log(Level.WARNING, "No line starts with that symbol: " + sid);
                }

            } catch (IOException ioe) {

                FileReadingException fileReadingException = new FileReadingException("Error reading file", ioe);
                logger.log(Level.SEVERE, fileReadingException.getMessage(), fileReadingException);
            }

            logger.info("One more task? (Y/N)");

        } while (s.next().toUpperCase().charAt(0) == 'Y');
    }
}
