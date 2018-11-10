package ru.keepdoing;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Menu.MainMenu;
import ru.keepdoing.Menu.Menu;
import ru.keepdoing.Menu.MenuItems;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGUI {
    private static final String WRONG_INPUT = "\n\nWrong input. Enter item number again: \n";

    public ConsoleGUI(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.run(0);
    }

    public void mainCycle() {
        print(Menu.execMenu.getString());
        Scanner conInput = new Scanner(System.in);

        try {
            int choice = Integer.parseInt(conInput.next());
            DBQueries dbQueries = new DBQueries("tasks.db");
            dbQueries.settingUp();
            ArrayList<Object[]> data = Menu.execMenu.exec(choice, dbQueries);
            if (data != null) printData(data);
        } catch (NumberFormatException e) {
            print(WRONG_INPUT);
        }
        mainCycle();
    }

    public static void printData(ArrayList<Object[]> data, String title) {
        if (title.length() > 0) System.out.println(" < " + title + " > ");

        for (Object[] arr : data) {
            for (Object obj : arr) {
                System.out.printf(" %s\t|", String.valueOf(obj));
            }
            print("\n");
        }
        print("\n");
    }

    public static void printData(ArrayList<Object[]> data) {
        printData(data, "");
    }

    public static void print(String str) {
        System.out.printf(str);
    }

    //TODO  Remove this method
//    public void mainCycle() {
//        print(Menu.DEV_MENU);
//        Scanner in = new Scanner(System.in);
//        String str = in.next();
//
//        try {
//            int choice = Integer.parseInt(str);
//            DBQueries queries = new DBQueries("tasks.db");
//            queries.settingUp();
//            switch (choice) {
//                case 1:
//                    queries.createTables();
//                    break;
//                case 2:
//                    queries.dropTables();
//                    break;
//                case 3:
//                    queries.addStatus("Open");
//                    queries.addStatus("Close");
//                    queries.addStatus("WTF");
//
//                    queries.addType("Immediate");
//                    queries.addType("Idle");
//                    queries.addType("Simple");
//                    queries.addType("Ordinary");
//
//                    queries.addTask("Write the task app", 1, 1, 1, 1);
//                    break;
//                case 4:
//                    printData(queries.getTypes(), "Types");
//                    printData(queries.getStatuses(), "Statuses");
//                    printData(queries.getTasks(), "Tasks");
//                    printData(queries.getData(DBQueries.TASKS_VIEW), "Tasks view");
//                    break;
//                case 5:
//                    System.exit(0);
//            }
//
//        } catch (NumberFormatException e) {
//            System.out.printf(WRONG_INPUT);
//        }
//        System.out.printf("Success operation!\n");
//        mainCycle();
//    }

}
