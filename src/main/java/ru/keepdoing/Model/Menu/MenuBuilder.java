package ru.keepdoing.Model.Menu;

import ru.keepdoing.View.WrongMenuItemException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MenuBuilder {

    //    private static char LINE_SEPARATOR = '\n';
    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();
    //    private StringBuilder menuText = new StringBuilder();
    private int itemsCounter = 1;
    private final String menuName;


    public MenuBuilder(String menuName) {
        this.menuName = menuName;
//        menuText.append(menuName);
//        menuText.append(LINE_SEPARATOR);
    }

    public void run(int menuItem) throws WrongMenuItemException {
        if (!items.containsKey(menuItem)) throw new WrongMenuItemException();
        items.get(menuItem).run();
    }

    public void addAction(int itemNumber, AbstractMenuItem item) {
        ++itemsCounter;
        items.put(itemNumber, item);

        //TODO exclude this to View
//        menuText.append('\t')
//                .append(itemNumber)
//                .append(":\t")
//                .append(item.getName())
//                .append(LINE_SEPARATOR);
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
