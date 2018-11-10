package ru.keepdoing.Menu;

import java.util.HashMap;

public class MenuItems {

    public HashMap<Integer, IMenuItem> items = new HashMap<>();

    public MenuItems() {
        items.put(1, new IMenuItem() {
            @Override
            public void run() {
                System.out.println("Test first");
            }
        });

        items.put(2, new IMenuItem() {
            @Override
            public void run() {
                System.out.println("Test two");
            }
        });
    }
}
