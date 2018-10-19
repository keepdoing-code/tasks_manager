package ru.keepdoing;

public class App 
{
    public static void main( String[] args )
    {
        DBQueries queries = new DBQueries("tasks.db");
        queries.settingUp();
        queries.dropTables();
        queries.createTables();
        queries.addStatus("Open");
        queries.addStatus("Any");
        queries.addStatus("WTF");
        queries.addType("immediate");
        queries.addType("idle");
        queries.addType("simple");
        queries.addType("ordinary");
        ConsoleGUI.printData(queries.getTypes());
        System.out.print("\n");
        ConsoleGUI.printData(queries.getStatuses());
    }
}
