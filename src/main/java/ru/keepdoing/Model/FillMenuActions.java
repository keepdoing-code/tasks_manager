package ru.keepdoing.Model;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Menu.AbstractMenuItem;
import ru.keepdoing.Menu.MenuBuilder;
import ru.keepdoing.Menu.MenuWrapper;
import ru.keepdoing.View.GUIHelper;
import ru.keepdoing.View.Log;

public class FillMenuActions {

    private final DBQueries dbQueries;

    private final MenuWrapper menu;

    private MenuBuilder rootMenu, subMenu;

    private int choice;


    public FillMenuActions(final MenuWrapper menu, final DBQueries dbQueries) {
        this.dbQueries = dbQueries;
        this.menu = menu;

        fillRootMenu();
        fillSubmenu();
    }


    public void fillRootMenu() {
        rootMenu = menu.addMenu("Root menu");
        menu.setRootMenu(rootMenu);

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("Show tasks") {
            @Override
            public void run() {
                GUIHelper.printData(dbQueries.getTasksView(), "Here is all your tasks");
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("Select and work") {
            @Override
            public void run() {
                GUIHelper.printData(dbQueries.getTasksView());
                choice = GUIHelper.askChoice("Select the task to work with");
                menu.callSubMenu(subMenu);
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("Add new item") {
            @Override
            public void run() {

            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("Quit") {
            @Override
            public void run() {
                System.exit(0);
            }
        });

    }

    public void fillSubmenu() {
        subMenu = menu.addSubMenu("Sub menu", rootMenu);

        menu.addActionToMenu(subMenu, new AbstractMenuItem("Change status") {
            @Override
            public void run() {
                Log.s(choice);
                dbQueries.getDbWorker();
                menu.callSubMenu(rootMenu);
            }
        });

        menu.addActionToMenu(subMenu, new AbstractMenuItem("Remove") {
            @Override
            public void run() {
                Log.s(choice);
                dbQueries.removeTask(choice);
                menu.callSubMenu(rootMenu);
            }
        });
    }

}
