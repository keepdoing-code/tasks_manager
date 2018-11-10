package ru.keepdoing.Menu;

public abstract class AbstractMenuItem {
    private String name;

    abstract void run();

    String getName() {
        return this.name;
    }

    AbstractMenuItem(String name) {
        this.name = name;
    }
}
