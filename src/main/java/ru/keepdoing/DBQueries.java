package ru.keepdoing;

import java.util.ArrayList;

public class DBQueries {
    protected static final String setPragmaOn = "PRAGMA foreign_keys = ON;";
    protected static final String createTablesQuery =
            "CREATE TABLE if not exists statuses (id INTEGER PRIMARY KEY AUTOINCREMENT, sname text);" +
                    "CREATE TABLE if not exists types (id INTEGER PRIMARY KEY AUTOINCREMENT, tname text);" +
                    "CREATE TABLE if not exists tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, task text, tid INTEGER REFERENCES types(id) ON DELETE CASCADE , sid INTEGER REFERENCES statuses(id) ON DELETE CASCADE, dfrom INTEGER, dto INTEGER)";
    protected static final String dropTablesQuery =
            "DROP TABLE if exists statuses;" +
                    "DROP TABLE if exists types;" +
                    "DROP TABLE if exists tasks;";
    protected static final String addStatusQuery =
            "INSERT INTO statuses(sname) VALUES ";
    protected static final String addTypeQuery =
            "INSERT INTO types(tname) VALUES ";
    protected static final String showStatusesQuery =
            "SELECT * FROM statuses;";
    protected static final String showTypesQuery =
            "SELECT * FROM types;";
    public final DBWorker db;


    public DBQueries(String dbFilename){
        this.db = new DBWorker(dbFilename);
    }

    public void settingUp() {
        db.exec(setPragmaOn);
    }

    public void createTables() {
        db.execUpdate(createTablesQuery);
    }

    public void dropTables() {
        db.execUpdate(dropTablesQuery);
    }

    public void addStatus(String status) {
        db.execUpdate(addStatusQuery + "('" + status + "');");
    }

    public void addType(String type) {
        db.execUpdate(addTypeQuery + "('" + type + "');");
    }

    public ArrayList<Object[]> getStatuses() {
        ArrayList<Object[]> data = db.execMany(showStatusesQuery);
        return data;
    }

    public ArrayList<Object[]> getTypes() {
        ArrayList<Object[]> data = db.execMany(showTypesQuery);
        return data;
    }

}
