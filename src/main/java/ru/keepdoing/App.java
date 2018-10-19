package ru.keepdoing;

public class App 
{
    public static void main( String[] args )
    {
        DBQueries queries = new DBQueries("tasks.db");
        queries.dropTables();
        queries.createTables();
        queries.addStatus("Open");
        queries.addStatus("Any");
        queries.addStatus("WTF");
        ConsoleGUI.printListObjects(queries.getStatuses());
    }
}
