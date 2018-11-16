package ru.keepdoing.Menu;

import ru.keepdoing.Exceptions.WrongMenuItemException;

import java.util.HashMap;

public class MenuWrapper {

    private MenuBuilder currentMenu;

    private HashMap<String, MenuBuilder> structure = new HashMap();


    public MenuWrapper(final String rootMenuName) {
        addMenu(rootMenuName);
        setRootMenu(rootMenuName);
    }


    public void run(int num) throws WrongMenuItemException {
        currentMenu.run(num);
    }


    public String getCurrentMenuText() {
        return currentMenu.getMenuText();
    }


    public void addMenu(final String name, final String rootMenu) {
        MenuBuilder mb = new MenuBuilder(name);
        mb.addAction(new AbstractMenuItem("Go back") {
            @Override
            public void run() {
                currentMenu = getMenuByName(rootMenu);
            }
        });
        structure.put(name, mb);
    }


    public void addMenu(final String name) {
        MenuBuilder mb = new MenuBuilder(name);
        structure.put(name, mb);
    }


    public void addActionToMenu(final String menuName, AbstractMenuItem item) {
        MenuBuilder mb = getMenuByName(menuName);
        mb.addAction(item);
    }


    public MenuBuilder getMenuByName(final String name) {
        return structure.get(name);
    }


    public void callSubMenu(final String name) {
        currentMenu = getMenuByName(name);
    }

    public void setRootMenu(String menuName) {
        currentMenu = getMenuByName(menuName);
    }

    //================================================================
    public class MenuBuilder {

        private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();

        private StringBuilder menuText = new StringBuilder();

        private int itemsCounter = 1;


        public MenuBuilder(String menuName) {
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

}
