package ru.keepdoing.View;

import ru.keepdoing.Menu.MenuWrapper;
import ru.keepdoing.Exceptions.WrongMenuItemException;
import java.util.Scanner;

public class ConsoleGUI {

    private final MenuWrapper menu;


    public ConsoleGUI(MenuWrapper menu) {
        this.menu = menu;
    }

    public void askMenuItem() {
        Scanner consoleInput = new Scanner(System.in);

        try {
            GUIHelper.print(menu.getCurrentMenuText());
            int choice = Integer.parseInt(consoleInput.next());
            menu.run(choice);
        } catch (NumberFormatException e) {
            GUIHelper.print(GUIHelper.WRONG_INPUT);
        } catch (WrongMenuItemException e) {
            GUIHelper.print(GUIHelper.WRONG_MENU_ITEM);
        }
        GUIHelper.print("\n");
        askMenuItem();
    }
}