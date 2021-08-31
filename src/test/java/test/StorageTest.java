<<<<<<< Updated upstream:src/test/java/test/StorageTest.java
package test;

import duke.Duke;
import duke.MyList;
import duke.Storage;
import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testGetTaskFromString() {
        Duke duke = new Duke();
        Storage s = new Storage(new MyList(), "./Data.txt");
        try {
            assertEquals(
                    s.getTaskFromString("[D][X]  run /by 2000-12-12 yyyy-MM-dd").toString(),
                    new Deadline("run /by 2000-12-12", true).toString()
            );
        } catch (WrongCommandFormatException e) {
            throw new Error("testGetTaskFromString failed");
        }
    }

    @Test
    public void testGetTaskFromString2() {
        Duke duke = new Duke();
        Storage s = new Storage(new MyList(), "./Data.txt");
        try {
            assertEquals(
                    s.getTaskFromString("[E][]  run /at lolololol").toString(),
                    new Event("run /at lolololol", false).toString()
            );
        } catch (WrongCommandFormatException e) {
            throw new Error("testGetTaskFromString2 failed");
        }

    }
}
=======
//package duke;
//
//import duke.exception.WrongCommandFormatException;
//import duke.tasktype.Deadline;
//import duke.tasktype.Event;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class StorageTest {
//
//    @Test
//    public void testGetTaskFromString() {
//        Duke duke = new Duke();
//        Storage s = new Storage(new MyList(), "./Data.txt");
//        try {
//            assertEquals(
//                    s.getTaskFromString("[D][X]  run /by 2000-12-12 yyyy-MM-dd").toString(),
//                    new Deadline("run /by 2000-12-12", true).toString()
//            );
//        } catch (WrongCommandFormatException e) {
//            throw new Error("testGetTaskFromString failed");
//        }
//    }
//
//    @Test
//    public void testGetTaskFromString2() {
//        Duke duke = new Duke();
//        Storage s = new Storage(new MyList(), "./Data.txt");
//        try {
//            assertEquals(
//                    s.getTaskFromString("[E][]  run /at lolololol").toString(),
//                    new Event("run /at lolololol", false).toString()
//            );
//        } catch (WrongCommandFormatException e) {
//            throw new Error("testGetTaskFromString2 failed");
//        }
//
//    }
//}
>>>>>>> Stashed changes:src/main/java/duke/StorageTest.java
