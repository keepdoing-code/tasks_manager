package ru.keepdoing.Model;

import ru.keepdoing.View.WrongMenuItemException;

import java.util.HashMap;

public class MenuBuilder {

    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();
    private int itemsCounter = 1;
    private final String menuName;


    public MenuBuilder(String menuName) {
        this.menuName = menuName;
    }

    public void run(int menuItem) throws WrongMenuItemException {
        if (!items.containsKey(menuItem)) throw new WrongMenuItemException();
        items.get(menuItem).run();
    }

    public void addAction(int itemNumber, AbstractMenuItem item) {
        ++itemsCounter;
        items.put(itemNumber, item);
    }

    public void addAction(AbstractMenuItem item) {
        addAction(itemsCounter, item);
    }

    public HashMap<Integer, AbstractMenuItem> getMenuItems() {
        return items;
    }

    public String getMenuName() {
        return menuName;
    }

}
