package ru.keepdoing.Menu;

public abstract class AbstractMenuItem {
    private String name;

    public abstract void run();

    public String getName() {
        return this.name;
    }

    public AbstractMenuItem(String name) {
        this.name = name;
    }
}
