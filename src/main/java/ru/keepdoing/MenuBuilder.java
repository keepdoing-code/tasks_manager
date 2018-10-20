package ru.keepdoing;

public class MenuBuilder {

    private String result = "";

    public MenuBuilder fillPattern(String s, int count) {
        for (int i = 0; i < count; i++) {
            result += s;
        }
        result += "\n";
        return this;
    }

    public MenuBuilder addLine(String str) {
        return add(str + "\n");
    }

    public MenuBuilder addTab(String str) {
        return add("\t" + str + "\n");
    }

    public MenuBuilder add(String str) {
        result += str;
        return this;
    }

    public String get() {
        return result;
    }

}
