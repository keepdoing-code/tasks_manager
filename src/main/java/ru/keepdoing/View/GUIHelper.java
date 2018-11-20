package ru.keepdoing.View;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GUIHelper {

    public static final String WRONG_INPUT = "Wrong input. Enter again: ";
    public static final String WRONG_MENU_ITEM = "Nonexistent menu item. Try again: ";
    private static final String INPUT_PROMPT = "\n>";
    private static final String DAY_PROMPT = "Enter day";
    private static final String MONTH_PROMPT = "Enter month";
    private static final String YEAR_PROMPT = "Enter year";
    private static final String TITLE_PATTERN = " < %s > \n";
    private static final String SUCCESSFULLY_ADDED = "[Successfully added]";
    private static final String UNSUCCESSFULLY_ADDING = "[Error occurred when trying to add]";
    private static final char SEPARATOR_PATTERN = '-';
    private static final char LINE_SEPARATOR = '\n';

    public static int askInteger(final String askMessage) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            print(askMessage);
            print(INPUT_PROMPT);
            choice = scanner.nextInt();
        } catch (InputMismatchException | NumberFormatException e) {
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

    public static void print(final String str) {
        System.out.printf(str);
    }

    public static void printData(final ArrayList<Object[]> data, final String titleText) {
        if (titleText.length() > 0) {
            System.out.printf(TITLE_PATTERN, titleText);
        }

        int[] columnSizes = findColumnSizes(data);
        String rowPattern = makeTablePattern(columnSizes);
        String rowSeparator = makeSeparator(String.format(rowPattern, data.get(0)).length(), SEPARATOR_PATTERN);

        print(rowSeparator);

        for (int i = 0; i < data.size(); i++) {
            System.out.printf(rowPattern, data.get(i));

            if (i == 0) {
                print(rowSeparator);
            }
        }

        print(rowSeparator);
    }

    public static void printData(final ArrayList<Object[]> data) {
        printData(data, "");
    }

    public static void showOperationStatus(int status) {
        System.out.println(status);
        if (status > 0) {
            print(SUCCESSFULLY_ADDED + LINE_SEPARATOR);
        } else {
            print(UNSUCCESSFULLY_ADDING + LINE_SEPARATOR);
        }
    }

    private static int[] findColumnSizes(final ArrayList<Object[]> data) {
        int[] arr = new int[data.get(0).length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        for (Object[] obj : data) {
            for (int i = 0; i < obj.length; i++) {
                int length = String.valueOf(obj[i]).length();
                if (arr[i] < length) arr[i] = length;
            }
        }

        return arr;
    }

    private static String makeTablePattern(final int[] columnSizes) {
        StringBuilder sb = new StringBuilder();

        for (int columnSize : columnSizes) {
            sb
                    .append("%-")
                    .append(columnSize)
                    .append("s | ");
        }

        sb.append(LINE_SEPARATOR);
        return sb.toString();
    }

    private static String makeSeparator(final int length, final char pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(pattern);
        }
        sb.append(LINE_SEPARATOR);
        return sb.toString();
    }
}
