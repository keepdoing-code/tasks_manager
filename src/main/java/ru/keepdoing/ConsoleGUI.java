package ru.keepdoing;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Menu.AbstractMenuItem;
import ru.keepdoing.Menu.MainMenu;
import ru.keepdoing.Menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGUI {
    private final String WRONG_INPUT = "\n\nWrong input. Enter item number again: \n";
    private final MainMenu mainMenu = new MainMenu("Main menu");
    private final DBQueries dbQueries;
    
    //------------------------------------------------------------------------------

    public ConsoleGUI(){
        this.fillItems();
        dbQueries = new DBQueries("tasks.db");
        dbQueries.settingUp();
        this.mainCycle();
    }

    //------------------------------------------------------------------------------

    public void mainCycle() {
        print("\n\n\n");
        print(mainMenu.getText());
        Scanner consoleInput = new Scanner(System.in);

        try {
            int choice = Integer.parseInt(consoleInput.next());
            mainMenu.run(choice);
        } catch (NumberFormatException e) {
            print(WRONG_INPUT);
        }
        mainCycle();
    }

    //------------------------------------------------------------------------------

    private void fillItems() {
        mainMenu.add(1, new AbstractMenuItem("First item") {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        });

        mainMenu.add(2, new AbstractMenuItem("Second item") {
            @Override
            public void run() {
                System.out.println("This second");
            }
        });

        mainMenu.add(3, new AbstractMenuItem("Quit") {
            @Override
            public void run() {
                System.out.println("Bye bye..");
                System.exit(0);
            }
        });
    }

    //------------------------------------------------------------------------------

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


//                    queries.createTables();
//                    queries.dropTables();
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

//                    printData(queries.getTypes(), "Types");
//                    printData(queries.getStatuses(), "Statuses");
//                    printData(queries.getTasks(), "Tasks");
//                    printData(queries.getData(DBQueries.TASKS_VIEW), "Tasks view");


}
