package ru.keepdoing;

public class Menu {

    private String result = "";

    public Menu fillPattern(String s, int count) {
        for (int i = 0; i < count; i++) {
            result += s;
        }
        result += "\n";
        return this;
    }

    public Menu addLine(String str) {
        return add(str + "\n");
    }

    public Menu addTab(String str) {
        return add("\t" + str + "\n");
    }

    public Menu add(String str) {
        result += str;
        return this;
    }

    public String get() {
        return result;
    }

}
