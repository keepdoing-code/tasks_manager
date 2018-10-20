package ru.keepdoing;

import java.util.ArrayList;

public class ConsoleGUI {

    public ConsoleGUI(){
        DBQueries queries = new DBQueries("tasks.db");
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

        printData(queries.getTypes(), "Types");
        printData(queries.getStatuses(), "Statuses");
        printData(queries.getTasks(), "Tasks");

        printData(queries.getData(DBQueries.TASKS_VIEW), "Tasks view");
    }

    public static void printData(ArrayList<Object[]> data, String title) {

        System.out.println("> " + title);

        for (Object[] arr : data) {
            for (Object obj : arr) {
                System.out.printf(" %s |", String.valueOf(obj));
            }
            System.out.printf("\n");
        }
        System.out.print("\n");
    }
}
