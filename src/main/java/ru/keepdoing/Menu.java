package ru.keepdoing;

public class Menu {

    public static final String MAIN_MENU = new MenuBuilder()
            .addLine("Select what your want to do?")
            .addTab("1. Show Tasks")
            .addTab("2. Tasks manager")
            .addTab("3. Types and Statuses")
            .addTab("4. Exit")
            .add("> ")
            .get();

    public static final String DEV_MENU = new MenuBuilder()
            .fillPattern("-", 20)
            .addLine("Develop menu")
            .addTab("1. Create data base and tables structure")
            .addTab("2. Drop all tables in data base")
            .addTab("3. Fill tables by demo data")
            .addTab("4. Show tasks")
            .addTab("5. Exit")
            .add("> ")
            .get();

    public static final ExecMenuBuilder execMenu = new ExecMenuBuilder("Dev menu")
            .add(1, new MenuItem("Show tasks", DBQueries.GET_TASKS_QUERY, QueryType.many))
            .add(2, new MenuItem("Create tables", DBQueries.CREATE_TABLES_QUERY, QueryType.update))
            .add(3, new MenuItem("Exit", "Exit", QueryType.none));
    public static final String DEV_EXEC_MENU = execMenu.get();

}
