package ru.keepdoing.Model.Menu;

import ru.keepdoing.View.WrongMenuItemException;

import java.util.*;

public class MenuWrapper {

    private static final String GO_BACK = "Go back";
    private MenuBuilder currentMenu;
    private HashMap<String, MenuBuilder> structure = new HashMap<>();


    public void run(int num) throws WrongMenuItemException {
        currentMenu.run(num);
    }

    public String getCurrentMenuName() {
        return currentMenu.getMenuName();
    }

    public String getCurrentMenuText() {

        HashMap<Integer, AbstractMenuItem> items = currentMenu.getMenuItems();
        StringBuilder sb = new StringBuilder(currentMenu.getMenuName());
        sb.append("\n");

        for (Integer item : items.keySet()) {
            String s = String.format("%4d:  %-10s%n", item, items.get(item).getName());
            sb.append(s);
        }

        return sb.toString();
    }

    public MenuBuilder addSubMenu(final String name, final MenuBuilder rootMenu) {
        MenuBuilder mb = new MenuBuilder(name);
        mb.addAction(new AbstractMenuItem(GO_BACK) {
            @Override
            public void run() {
                currentMenu = rootMenu;
            }
        });
        structure.put(name, mb);
        return mb;
    }

    public MenuBuilder addMenu(final String name) {
        MenuBuilder mb = new MenuBuilder(name);
        structure.put(name, mb);
        return mb;
    }

    public void addActionToMenu(final MenuBuilder mb, AbstractMenuItem item) {
        structure.get(mb.getMenuName()).addAction(item);
    }

    public void callSubMenu(final MenuBuilder mb) {
        currentMenu = mb;
    }

    public void setRootMenu(final MenuBuilder mb) {
        currentMenu = mb;
    }
}
