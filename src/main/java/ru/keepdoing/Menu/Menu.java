package ru.keepdoing.Menu;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.TasksExceptions.WrongMenuItemException;

public class Menu {

    private MenuBuilder currentMenu;
    //TODO refactor menu to List of menus and manage them in this class
    private final MenuBuilder main = new MenuBuilder("Main menu");
    private final MenuBuilder subMenu = new MenuBuilder("Sub menu 1");
    private DBQueries dbQueries;

    public Menu(DBQueries dbQueries) {
        this.dbQueries = dbQueries;

        fillMenuItems();
        main.BuildMenuText();
        subMenu.BuildMenuText();
        currentMenu = main;
    }

    public void run(int num) throws WrongMenuItemException {
        currentMenu.run(num);
    }

    public String getMenuText() {
        return currentMenu.getMenuText();
    }


    //Maybe we must fill methods in App class or any another class, where we create instance of DBQueries.
    //TODO refactor code according to above sentence
    private void fillMenuItems() {
        main.add(1, new AbstractMenuItem("print") {
            @Override
            public void run() {
                System.out.println("Main menu first item");
            }
        });

        main.add(2, new AbstractMenuItem("Submenu") {
            @Override
            public void run() {
                currentMenu = subMenu;
            }
        });

        main.add(9, new AbstractMenuItem("Quit") {
            @Override
            public void run() {
                System.out.println("Bye bye..");
                System.exit(0);
            }
        });


        subMenu.add(1, new AbstractMenuItem("print submenu") {
            @Override
            public void run() {
                System.out.println("Sub menu item");
            }
        });

        subMenu.add(2, new AbstractMenuItem("Go back") {
            @Override
            public void run() {
                currentMenu = main;
            }
        });


    }
}
