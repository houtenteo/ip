package duke;

import java.time.format.DateTimeFormatter;

/**
 * The main class for the bot
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Duke {
    private static String format = "yyyy-MM-dd"; //default date
    private MyList list;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().start();
    }

    /**
     * Method to get the current format setting.
     * @return the DateTimeFormatter
     */
    public static String getFormat() {
        return format;
    }

    /**
     * Method to allow the user to change the date format.
     * @param newFormat The desired format
     * @throws IllegalArgumentException Thrown if the format is not accepted by
     *                                  DateTimeFormatter
     */
    public static void setFormat(String newFormat) throws IllegalArgumentException {
        DateTimeFormatter.ofPattern(newFormat);
        format = newFormat;
    }

    /**
     * Constructor for the duke class.
     * Initialises the list and storage.
     */
    public Duke() {
        this.list = new MyList();
        this.storage = new Storage(this.list, "./Data.txt");
    }

    /**
     * Method to start the bot and waits for the user's input.
     */
    public void start() {

        Ui.welcomeMessage();

        this.storage.load();

        Parser p = new Parser(this.list, this.storage);

        while (p.isRunning()) {
            p.readInput();
        }
    }
}
