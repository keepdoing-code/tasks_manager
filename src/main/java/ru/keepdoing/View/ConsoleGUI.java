package ru.keepdoing.View;

import ru.keepdoing.Model.Menu.MenuWrapper;

import java.util.List;

public class ConsoleGUI {

    private final MenuWrapper menu;

    public ConsoleGUI(MenuWrapper menu) {
        this.menu = menu;
    }

    public void askMenuItem() {
        try {
            String menuText = menu.getCurrentMenuText();
            int choice = GUIHelper.askInteger(menuText);
            menu.run(choice);
        } catch (WrongMenuItemException e) {
            System.out.println(GUIHelper.WRONG_MENU_ITEM);
        }
        askMenuItem();
    }

}
