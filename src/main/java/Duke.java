import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ArrayList<Task> l = new ArrayList<Task>();

        System.out.println(
                "Yo! Duke here \n"
                + "What did you call me for? \n"
                + "It better be something useful or else... \n"
        );

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int listLength = l.size();
                if (listLength == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Your list items:");
                    for (int i = 0; i < listLength; i ++) {
                        Task t = l.get(i);
                        String statusIcon = t.getStatusIcon();
                        System.out.printf("%d. %s %s \n", i + 1, statusIcon, t.getTaskName());
                    }
                }
            } else if (input.substring(0, 4).equals("done")) {
                try {
                    int index = Integer.parseInt(input.substring(5));
                    l.get(index - 1).markComplete();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index, please try again");
                } catch (IndexOutOfBoundsException ee) {
                    System.out.println("Invalid index, please try again");
                }
            } else {
                Task newTask = new Task(input, false);
                l.add(newTask);
                System.out.println("Added `" + input + "` to your list");
            }
            input = s.nextLine();
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");

    }
}
