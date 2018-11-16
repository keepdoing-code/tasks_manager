package ru.keepdoing;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Model.Menu.MenuWrapper;
import ru.keepdoing.Model.FillMenuActions;
import ru.keepdoing.View.ConsoleGUI;
import ru.keepdoing.View.Log;

import java.io.File;

public class App {
    private static final String DB_FILE = "tasks.db";

    public static void main( String[] args )
    {

        DBQueries dbQueries = new DBQueries(DB_FILE);

        if (!(new File(".", DB_FILE).exists())) {
            dbQueries.settingUp();
            dbQueries.createTables();
            dbQueries.fillTables();
            dbQueries.fillTestTasks();
            Log.s("DB created. Tables created");
        }

        MenuWrapper menu = new MenuWrapper();
        new FillMenuActions(menu, dbQueries);
        new ConsoleGUI(menu).askMenuItem();
    }
}
