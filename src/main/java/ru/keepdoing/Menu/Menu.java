package ru.keepdoing.Menu;

import ru.keepdoing.Controller.DBQueries;

public class Menu {

    public static final String DEV_MENU = new SimpleMenuBuilder()
            .fillPattern("-", 20)
            .addLine("Develop menu")
            .addTab("1. Create data base and tables structure")
            .addTab("2. Drop all tables in data base")
            .addTab("3. Fill tables by demo data")
            .addTab("4. Show tasks")
            .addTab("5. Exit")
            .add("> ")
            .getString();

    public static final SimpleExecMenuBuilder execMenu = new SimpleExecMenuBuilder("Dev menu")
            //TODO  Split addition menu entry into several parts
            //TODO  May be we can attach query type to queries in DBQueries by creating new class (type) Query
            //TODO  Send to add method only menu item text and Query(with query text and type). Auto convert all data in ExecMenuItem.
            .add(1, new ExecMenuItem("Show tasks", DBQueries.GET_TASKS_QUERY, QueryType.many))
            .add(2, new ExecMenuItem("Create tables", DBQueries.CREATE_TABLES_QUERY, QueryType.update))
            .add(3, new ExecMenuItem("Exit", "Exit", QueryType.none));

    public static final String DEV_EXEC_MENU = execMenu.getString();


}
