package ru.keepdoing;

import java.util.ArrayList;

public class ConsoleGUI {
    public ConsoleGUI(){

    }

    public static void printListObjects(ArrayList<Object[]> data){
        for (Object[] arr : data) {
            for (Object obj : arr) {
                System.out.printf(" %s |", String.valueOf(obj));
            }
            System.out.printf("\n");
        }
    }
}
