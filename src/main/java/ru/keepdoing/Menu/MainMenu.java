package ru.keepdoing.Menu;

import java.util.HashMap;

public class MainMenu {
    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();

    public MainMenu() {
        items.put(0, new AbstractMenuItem("First item") {
            @Override
            void run() {
                System.out.println(getName());
            }
        });
    }

    public void run(int menuNum) {
        //TODO check error
        items.get(menuNum).run();
    }
}
