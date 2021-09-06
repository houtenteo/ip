package duke;

import java.util.Scanner;

import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;


/**
 * Class to handle all the user inputs.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Parser {
    private boolean isRunning;
    private MyList list;
    private Storage storage;
    private Scanner s;

    /**
     * Constructor for the class
     * @param list The list that the parser would be updating.
     * @param storage the storage to read and write the data.
     */
    public Parser(MyList list, Storage storage) {
        this.isRunning = true;
        this.list = list;
        this.storage = storage;
    }

    /**
     * Method to check if the parser is still running.
     * @return true if its running and false otherwise.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Method to generate Dukes response based on the user command.
     * @param command The command the user enters.
     * @return The String representing Duke's response.
     */
    public String getDukeResponse(String command) {
        this.s = new Scanner(command);
        String input = this.s.next();
        String response = "";

        switch (input) {
        case "list":
            response = this.list.listAll();
            break;
        case "done":
            response = getDoneResponse();
            break;
        case "todo":
            response = getTodoResponse();
            break;
        case "deadline":
            response = getDeadlineResponse();
            break;
        case "event":
            response = getEventResponse();
            break;
        case "delete":
            response = getDeleteResponse();
            break;
        case "setFormat":
            response = getSetFormatResponse();
            break;
        case "format":
            response = Ui.currentDateFormatMessage();
            break;
        case "find":
            response = getFindResponse();
            break;
        case "bye":
            response = Ui.botShutdownMessage();
            this.isRunning = false;
            break;
        case "remind":
            response = getReminderResponse();
            break;
        default:
            response = Ui.getUnrecognisedCmdMessage();
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'done' command.
     * @return Duke's response
     */
    private String getDoneResponse() {
        String response = "";
        int counter = 0;

        while (this.s.hasNextInt()) {
            int index = this.s.nextInt();
            response += this.list.markComplete(index);
            this.storage.writeToFile();
            counter++;
        }
        if (counter == 0) {
            response = Ui.invalidIndexMessage();
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'todo' command.
     * @return Duke's response.
     */
    private String getTodoResponse() {
        String response = "";
        String description = "";
        while (this.s.hasNextLine()) {
            description = this.s.nextLine();
        }
        try {
            Todo newTodo = new Todo(description, false);
            response = this.list.addTask(newTodo);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to return duke's response to a 'deadline' command.
     * @return Duke's response.
     */
    private String getDeadlineResponse() {
        String response = "";
        String description = "";
        while (this.s.hasNextLine()) {
            description = this.s.nextLine();
        }
        try {
            Deadline newDeadline = new Deadline(description, false);
            response = this.list.addTask(newDeadline);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to generate Duke's response to an 'event' command.
     * @return Duke's response.
     */
    private String getEventResponse() {
        String response = "";
        String description = "";
        while (this.s.hasNextLine()) {
            description = this.s.nextLine();
        }
        try {
            Event newEvent = new Event(description, false);
            response = this.list.addTask(newEvent);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'delete' command.
     * @return Duke's response.
     */
    private String getDeleteResponse() {
        String response = "";
        if (this.s.hasNextInt()) {
            int index = this.s.nextInt();
            response = this.list.deleteTask(index);
            this.storage.writeToFile();
        } else {
            response = Ui.invalidIndexMessage();
        }
        return response;
    }

    /**
     * Method to generate Duke's reponse to a 'setFormat' command.
     * @return Duke's response.
     */
    private String getSetFormatResponse() {
        String response = "";
        if (this.s.hasNextLine()) {
            try {
                Duke.setFormat(this.s.nextLine().substring(1));
                response = Ui.formatUpdatedMessage();
                this.storage.writeToFile();
            } catch (IllegalArgumentException e) {
                response = Ui.unacceptableFormatMessage();
            }
        } else {
            response = Ui.noFormatSpecifiedMessage();
        }
        return response;
    }

    /**
     * Method to generate Duke's response to a 'find' command.
     * @return Duke's response.
     */
    private String getFindResponse() {
        String response = "";
        if (this.s.hasNextLine()) {
            response = this.list.find(this.s.nextLine());
        } else {
            response = Ui.noKeywordSpecifiedMessage();
        }
        return response;
    }

    /**
     * Method to generate Duke's response to the 'remind' command.
     * @return Duke's response.
     */
    private String getReminderResponse() {
        String response = "";
        int daysFromToday = 0;
        if (this.s.hasNextInt()) {
            daysFromToday = this.s.nextInt();
            response = this.list.findDeadlineWithin(daysFromToday);
        } else {
            response = Ui.noDaySpecifiedMessage();
        }
        return response;
    }
}
