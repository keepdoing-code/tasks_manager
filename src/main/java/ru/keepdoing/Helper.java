package ru.keepdoing;

import java.util.List;

public class Helper {
    public static void logArr(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            System.out.printf(" %s |", strings);
        }
    }

    public static void logList(List<?> list){
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            System.out.printf(" %s |", obj.toString());
        }
    }
}
