package ru.keepdoing.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu {
    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();

    private final String menuName;

    public MainMenu(String menuName) {
        this.menuName = menuName;
    }

    public void run(int menuNum) {
        //TODO check error when wrong number entered
        items.get(menuNum).run();
    }

    public void add(int itemNumber, AbstractMenuItem item) {
        items.put(itemNumber, item);
    }

    public String getText() {
        System.out.println(this.menuName);
        for (Integer i : items.keySet()) {
            System.out.printf("%d  ", i);
            System.out.println(items.get(i).getName());
        }
        return null;
    }

}
