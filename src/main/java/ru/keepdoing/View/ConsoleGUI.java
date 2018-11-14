package ru.keepdoing.View;

import ru.keepdoing.Menu.Menu;
import ru.keepdoing.TasksExceptions.WrongMenuItemException;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGUI {
    private final String WRONG_INPUT = "Wrong input. Enter item number again: ";
    private final String WRONG_MENU_ITEM = "Nonexistent menu item. Try again: ";
    private final Menu menu;


    public ConsoleGUI(Menu menu) {
        this.menu = menu;
    }

    public void askMenuItem() {
        Scanner consoleInput = new Scanner(System.in);

        try {
            print(menu.getMenuText());
            int choice = Integer.parseInt(consoleInput.next());
            menu.run(choice);
        } catch (NumberFormatException e) {
            print(WRONG_INPUT);
        } catch (WrongMenuItemException e) {
            print(WRONG_MENU_ITEM);
        }
        print("\n");
        askMenuItem();
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

}
