package ru.keepdoing.Model;

import ru.keepdoing.Menu.AbstractMenuItem;
import ru.keepdoing.Menu.MenuWrapper;

public class FillMenuActions {

    public static void fill(final MenuWrapper menu) {
        menu.addMenu("Sub menu", "Root menu");
        menu.addActionToMenu("Sub menu", new AbstractMenuItem("sub menu first item") {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        });

        menu.addActionToMenu("Root menu", new AbstractMenuItem("first item") {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        });

        menu.addActionToMenu("Root menu", new AbstractMenuItem("call sub menu") {
            @Override
            public void run() {
                System.out.println(this.getName());
                menu.callSubMenu("Sub menu");
            }
        });

        menu.addActionToMenu("Root menu", new AbstractMenuItem("quit") {
            @Override
            public void run() {
                System.exit(0);
            }
        });


    }
}
