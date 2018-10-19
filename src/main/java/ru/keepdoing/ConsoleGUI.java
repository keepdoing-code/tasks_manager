package ru.keepdoing;

import java.util.ArrayList;

public class ConsoleGUI {
    public ConsoleGUI(){

    }

    public static void main(String[] args) {
        ArrayList<String[]> arr = new ArrayList<>();
        arr.add(new String[]{"a","b"});
        String[] str = arr.get(0);
        for (String s :str) {
            System.out.println(s);
        }
    }
}
