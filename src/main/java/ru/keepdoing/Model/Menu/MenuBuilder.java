package ru.keepdoing.Model.Menu;

import ru.keepdoing.View.WrongMenuItemException;

import java.util.HashMap;

public class MenuBuilder {

    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();

    private StringBuilder menuText = new StringBuilder();

    private int itemsCounter = 1;

    public String getMenuName() {
        return menuName;
    }

    private final String menuName;


    public MenuBuilder(String menuName) {
        this.menuName = menuName;
        menuText.append(menuName);
        menuText.append('\n');
    }


    public void run(int menuItem) throws WrongMenuItemException {
        if (!items.containsKey(menuItem)) throw new WrongMenuItemException();
        items.get(menuItem).run();
    }


    public void addAction(int itemNumber, AbstractMenuItem item) {
        ++itemsCounter;
        items.put(itemNumber, item);

        menuText.append('\t')
                .append(itemNumber)
                .append(":\t")
                .append(item.getName())
                .append('\n');
    }

    public void addAction(AbstractMenuItem item) {
        addAction(itemsCounter, item);
    }

    public String getMenuText() {
        return menuText.toString();
    }

}
