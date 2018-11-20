package ru.keepdoing;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Model.MenuWrapper;
import ru.keepdoing.Controller.FillMenuActions;
import ru.keepdoing.View.ConsoleGUI;

import java.io.File;

public class App {
    private static final String DB_FILE = "tasks.db";
    private static final String DB_FILE_PATH = ".";

    public static void main(String[] args) {

        DBQueries dbQueries = new DBQueries(DB_FILE);

        if (!(new File(DB_FILE_PATH, DB_FILE).exists())) {
            dbQueries.settingUp();
            dbQueries.createTables();
            dbQueries.fillTables();
        }

        MenuWrapper menu = new MenuWrapper();
        new FillMenuActions(menu, dbQueries);
        new ConsoleGUI(menu);
    }
}
