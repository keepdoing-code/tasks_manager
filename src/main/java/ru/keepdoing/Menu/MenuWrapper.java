package ru.keepdoing.Menu;

import ru.keepdoing.Exceptions.WrongMenuItemException;

import java.util.HashMap;

public class MenuWrapper {

    private MenuBuilder currentMenu;

    private HashMap<String, MenuBuilder> structure = new HashMap<>();


    public void run(int num) throws WrongMenuItemException {
        currentMenu.run(num);
    }


    public String getCurrentMenuText() {
        return currentMenu.getMenuText();
    }


    public MenuBuilder addSubMenu(final String name, final MenuBuilder rootMenu) {
        MenuBuilder mb = new MenuBuilder(name);
        mb.addAction(new AbstractMenuItem("Go back") {
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
