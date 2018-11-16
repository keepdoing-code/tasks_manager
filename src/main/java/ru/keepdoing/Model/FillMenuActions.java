package ru.keepdoing.Model;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Menu.AbstractMenuItem;
import ru.keepdoing.Menu.MenuBuilder;
import ru.keepdoing.Menu.MenuWrapper;

public class FillMenuActions {

    public FillMenuActions(final MenuWrapper menu, final DBQueries dbQueries) {

        MenuBuilder rootMenu = menu.addMenu("Root menu");
        menu.setRootMenu(rootMenu);

        final MenuBuilder subMenu = menu.addSubMenu("Sub menu", rootMenu);

        menu.addActionToMenu(subMenu, new AbstractMenuItem("sub menu first item") {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("first item") {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("call sub menu") {
            @Override
            public void run() {
                System.out.println(this.getName());
                menu.callSubMenu(subMenu);
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem("quit") {
            @Override
            public void run() {
                System.exit(0);
            }
        });


    }
}
