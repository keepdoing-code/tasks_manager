package ru.keepdoing.Menu;

public class SimpleMenuBuilder {

    private String result = "";

    public SimpleMenuBuilder fillPattern(String s, int count) {
        for (int i = 0; i < count; i++) {
            result += s;
        }
        result += "\n";
        return this;
    }

    public SimpleMenuBuilder addLine(String str) {
        return add(str + "\n");
    }

    public SimpleMenuBuilder addTab(String str) {
        return add("\t" + str + "\n");
    }

    public SimpleMenuBuilder add(String str) {
        result += str;
        return this;
    }

    public String getString() {
        return result;
    }

}
