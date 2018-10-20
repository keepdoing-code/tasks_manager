package ru.keepdoing;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGUI {
    private static final String WRONG_INPUT = "\n\nWrong input. Enter item number again: \n";


    public ConsoleGUI(){
        mainCycleV2();
    }

    public void mainCycleV2() {
        System.out.printf(Menu.DEV_EXEC_MENU);
        Scanner conInput = new Scanner(System.in);

        try {
            int choice = Integer.parseInt(conInput.next());
            DBQueries queries = new DBQueries("tasks.db");
            queries.settingUp();
            ArrayList<Object[]> data = Menu.execMenu.exec(choice, queries);
            if (data != null) printData(data);
        } catch (NumberFormatException e) {
            System.out.printf(WRONG_INPUT);
        }
        mainCycleV2();
    }

    public void mainCycle() {
        System.out.printf(Menu.DEV_MENU);
        Scanner in = new Scanner(System.in);
        String str = in.next();

        try {
            int choice = Integer.parseInt(str);
            DBQueries queries = new DBQueries("tasks.dbWorker");
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

        if (title.length() > 0) System.out.println(" < " + title + " > ");

        for (Object[] arr : data) {
            for (Object obj : arr) {
                System.out.printf(" %s\t|", String.valueOf(obj));
            }
            System.out.printf("\n");
        }
        System.out.print("\n");
    }

    public static void printData(ArrayList<Object[]> data) {
        printData(data, "");
    }

    public static void testQueries() {
        DBQueries queries = new DBQueries("tasks.dbWorker");
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
