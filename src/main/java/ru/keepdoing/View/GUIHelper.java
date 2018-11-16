package ru.keepdoing.View;

import java.util.ArrayList;
import java.util.Scanner;

public class GUIHelper {

    public static final String WRONG_INPUT = "Wrong input. Enter item number again: ";
    public static final String WRONG_MENU_ITEM = "Nonexistent menu item. Try again: ";

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

    public static int askChoice(final String askMessage) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            print(askMessage);
            print("\n> ");
            choice = scanner.nextInt();
        } catch (NumberFormatException e) {
            print(WRONG_INPUT);
            return askChoice(askMessage);
        }
        return choice;
    }

    public static String askString(final String askMessage) {
        Scanner scanner = new Scanner(System.in);

        try {
            print(askMessage);
            return scanner.next();
        } catch (Exception e) {
            Log.s(e.getMessage());
            return askString(askMessage);
        }
    }
}
