package ru.keepdoing.View;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GUIHelper {

    public static final String WRONG_INPUT = "Wrong input. Enter again: ";
    public static final String WRONG_MENU_ITEM = "Nonexistent menu item. Try again: ";
    private static final String INPUT_PROMPT = "\n>";
    private static final String DAY_PROMPT = "Enter day";
    private static final String MONTH_PROMPT = "Enter month";
    private static final String YEAR_PROMPT = "Enter year";


    public static int askInteger(final String askMessage) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            print(askMessage);
            print(INPUT_PROMPT);
            choice = scanner.nextInt();
        } catch (NumberFormatException e) {
            print(WRONG_INPUT);
            return askInteger(askMessage);
        }
        return choice;
    }

    public static String askString(final String askMessage) {
        Scanner scanner = new Scanner(System.in);

        try {
            print(askMessage);
            print(INPUT_PROMPT);
            return scanner.nextLine();
        } catch (Exception e) {
            Log.s(e.getMessage());
            return askString(askMessage);
        }
    }

    public static LocalDate askDate(final String askMessage) {
        print(askMessage);
        print(INPUT_PROMPT);

        LocalDate date;
        try {
            int day = askInteger(DAY_PROMPT);
            int month = askInteger(MONTH_PROMPT);
            int year = askInteger(YEAR_PROMPT);
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println(WRONG_INPUT);
            return askDate(askMessage);
        }
        return date;
    }

    private static void print(final String str) {
        System.out.printf(str);
    }

    public static void printData(final ArrayList<Object[]> data, String title) {
        if (title.length() > 0) System.out.println(" < " + title + " > ");

        for (Object[] arr : data) {
            for (Object obj : arr) {
                System.out.printf(" %s\t|", String.valueOf(obj));
            }
            print("\n");
        }
        print("\n");
    }

    public static void printData(final ArrayList<Object[]> data) {
        printData(data, "");
    }

}
