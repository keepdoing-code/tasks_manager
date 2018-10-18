package ru.keepdoing;

public class Queries {
    protected static final String setPragmaOn = "PRAGMA foreign_keys = ON;";
    protected static final String createTablesQuery =
            "CREATE TABLE if not exists status (id INTEGER PRIMARY KEY AUTOINCREMENT, sname text);" +
                    "CREATE TABLE if not exists type (id INTEGER PRIMARY KEY AUTOINCREMENT, tname text);" +
                    "CREATE TABLE if not exists task (id INTEGER PRIMARY KEY AUTOINCREMENT, task text, tid INTEGER REFERENCES type(id) ON DELETE CASCADE , sid INTEGER REFERENCES status(id) ON DELETE CASCADE, dfrom INTEGER, dto INTEGER)";
    protected static final String dropTablesQuery =
            "DROP TABLE if exists status;" +
                    "DROP TABLE if exists type;" +
                    "DROP TABLE if exists task;";
    protected static final String insertDataQuery =
            "INSERT INTO status(sname) VALUES ('opened');" +
                    "INSERT INTO status(sname) VALUES ('closed');" +
                    "INSERT INTO type(tname) VALUES ('quick');" +
                    "INSERT INTO type(tname) VALUES ('important');" +
                    "INSERT INTO task(task, tid, sid, dfrom, dto) VALUES ('first task',1,1,1,1);";


}
