package ru.keepdoing;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGUI {

    private static final String MAIN_MENU = new Menu()
            .addLine("Select what your want to do?")
            .addTab("1. Show Tasks")
            .addTab("2. Tasks manager")
            .addTab("3. Types and Statuses")
            .addTab("4. Exit")
            .add("> ")
            .get();

    private static final String DEV_MENU = new Menu()
            .fillPattern("-", 20)
            .addLine("Develop menu")
            .addTab("1. Create data base and tables structure")
            .addTab("2. Drop all tables in data base")
            .addTab("3. Fill tables by demo data")
            .addTab("4. Show tasks")
            .addTab("5. Exit")
            .add("> ")
            .get();

    private static final String SHOW_TASKS_MENU = new Menu()
            .addLine("Tasks menu")
            .addTab("1. Show all")
            .addTab("2. Filter by type")
            .addTab("3. Filter by status")
            .get();

    private static final String WRONG_INPUT = "\n\nWrong input. Enter item number again: \n";

    public ConsoleGUI(){
        mainCycle();
    }

    public void mainCycle() {
        System.out.printf(DEV_MENU);
        Scanner in = new Scanner(System.in);
        String str = in.next();

        try {
            int choice = Integer.parseInt(str);
            DBQueries queries = new DBQueries("tasks.db");
            queries.settingUp();
            switch (choice) {
                case 1:
                    queries.createTables();
                    break;
                case 2:
                    queries.dropTables();
                    break;
                case 3:
                    queries.addStatus("Open");
                    queries.addStatus("Close");
                    queries.addStatus("WTF");
                    queries.addType("Immediate");
                    queries.addType("Idle");
                    queries.addType("Simple");
                    queries.addType("Ordinary");
                    queries.addTask("Write the task app", 1, 1, 1, 1);
                    break;
                case 4:
                    printData(queries.getTypes(), "Types");
                    printData(queries.getStatuses(), "Statuses");
                    printData(queries.getTasks(), "Tasks");
                    printData(queries.getData(DBQueries.TASKS_VIEW), "Tasks view");
                    break;
                case 5:
                    System.exit(0);
            }

        } catch (NumberFormatException e) {
            System.out.printf(WRONG_INPUT);
        }
        System.out.printf("Success operation!\n");
        mainCycle();
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

    public static void printData(ArrayList<Object[]> data) {
        printData(data, "");
    }

    public static void testQueries() {
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
}
