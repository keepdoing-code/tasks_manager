package ru.keepdoing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.View.ConsoleGUI;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }

    @Test
    public void testQueries() {
        DBQueries queries = new DBQueries("test_tasks.db");
        queries.settingUp();
        queries.dropTables();
        queries.createTables();

        queries.addStatus("Open");
        queries.addStatus("Close");
        queries.addStatus("WTF");

        queries.addType("immediate");
        queries.addType("idle");
        queries.addType("simple");
        queries.addType("ordinary");

        queries.addTask("Write the task app", 1, 1, 1, 1);

        ConsoleGUI.printData(queries.getTypes(), "Types");
        ConsoleGUI.printData(queries.getStatuses(), "Statuses");
        ConsoleGUI.printData(queries.getTasks(), "Tasks");

        ConsoleGUI.printData(queries.getData(DBQueries.TASKS_VIEW), "Tasks view");
    }


}
