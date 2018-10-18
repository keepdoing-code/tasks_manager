package ru.keepdoing;

import java.sql.*;
import java.util.Arrays;

public class DBWorker {

    private final String setPragmaOn = "PRAGMA foreign_keys = ON;";
    private final String createTableQuery =
            "CREATE TABLE if not exists status (id INTEGER PRIMARY KEY AUTOINCREMENT, sname text);"+
            "CREATE TABLE if not exists type (id INTEGER PRIMARY KEY AUTOINCREMENT, tname text);" ;
    private final String createTasksQuery =
            "CREATE TABLE if not exists task (id INTEGER PRIMARY KEY AUTOINCREMENT, task text, " +
            "tid INTEGER REFERENCES type(id) ON DELETE CASCADE , sid INTEGER REFERENCES status(id) ON DELETE CASCADE, " +
            "dfrom INTEGER, dto INTEGER)";
    private final String dropTablesQuery =
            "drop table if exists status;" +
            "drop table if exists type;" +
            "drop table if exists tasks;";
    private final String insertStatusQuery =
            "INSERT INTO status(sname) VALUES ('opened');" +
            "INSERT INTO status(sname) VALUES ('closed');";
    private final String insertTypeQuery =
            "INSERT INTO type(tname) VALUES ('quick');" +
            "INSERT INTO type(tname) VALUES ('important');";
    private final String insertTaskQuery =
            "INSERT INTO task(task,tid,sid,dfrom,dto) VALUES ('quick',1,1,1,1);";


    private final String connectionString = "jdbc:sqlite:";
    private final String filename;
    private Connection connection;



    public DBWorker(String file){
        this.filename = file;
        try
        {
            connection = DriverManager.getConnection(connectionString + filename);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.execute(setPragmaOn);
            statement.executeUpdate(dropTablesQuery);
            statement.executeUpdate(createTasksQuery);
            statement.executeUpdate(insertTaskQuery);
            ResultSet rs = statement.executeQuery("SELECT * FROM task;");
            while(rs.next())
            {
                System.out.printf("id - %s | name - %s\n", rs.getString("id"), rs.getString("task"));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
