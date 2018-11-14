package ru.keepdoing.View;

import java.sql.Timestamp;

public class Log {

    public static void s(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(new Timestamp(System.currentTimeMillis()).toString())
                .append("] - ")
                .append(s)
                .append('\n');
        System.out.print(sb.toString());
    }

    public static void s(int i) {
        s(String.valueOf(i));
    }
}
