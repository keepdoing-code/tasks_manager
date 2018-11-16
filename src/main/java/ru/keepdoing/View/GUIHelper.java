package ru.keepdoing.View;

import java.util.ArrayList;

public class GUIHelper {

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
