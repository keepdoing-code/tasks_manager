package ru.keepdoing;

import java.util.ArrayList;

public class DBQueries {
    protected static final String PRAGMA_FOREIGN_KEYS_ON = "PRAGMA foreign_keys = ON;";
    protected static final String CREATE_TABLES_QUERY =
            "CREATE TABLE if not exists statuses (id INTEGER PRIMARY KEY AUTOINCREMENT, sname text);" +
                    "CREATE TABLE if not exists types (id INTEGER PRIMARY KEY AUTOINCREMENT, tname text);" +
                    "CREATE TABLE if not exists tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "task TEXT, " +
                    "tid INTEGER, " +
                    "sid INTEGER, " +
                    "dfrom INTEGER, " +
                    "dto INTEGER," +
                    "FOREIGN KEY(tid) REFERENCES types(id) ON DELETE CASCADE , " +
                    "FOREIGN KEY(sid) REFERENCES statuses(id) ON DELETE CASCADE" +
                    ");";

    protected static final String DROP_TABLES_QUERY = "DROP TABLE if exists statuses; DROP TABLE if exists types; DROP TABLE if exists tasks;";
    protected static final String ADD_STATUS_QUERY = "INSERT INTO statuses(sname) VALUES ";
    protected static final String ADD_TYPE_QUERY = "INSERT INTO types(tname) VALUES ";
    protected static final String ADD_TASK_QUERY = "INSERT INTO tasks(task, tid, sid, dfrom, dto) VALUES ";
    protected static final String GET_STATUSES_QUERY = "SELECT * FROM statuses;";
    protected static final String GET_TYPES_QUERY = "SELECT * FROM types;";
    protected static final String GET_TASKS_QUERY = "SELECT * FROM tasks;";
    protected static final String TASKS_VIEW = "SELECT tasks.task, statuses.sname, types.tname FROM tasks, statuses, types WHERE " +
            "(tasks.tid = types.id and tasks.sid = statuses.id);";
    public final DBWorker db;


    public DBQueries(String dbFilename){
        this.db = new DBWorker(dbFilename);
    }

    public void settingUp() {
        db.exec(PRAGMA_FOREIGN_KEYS_ON);
    }

    public void createTables() {
        db.execUpdate(CREATE_TABLES_QUERY);
    }

    public void dropTables() {
        db.execUpdate(DROP_TABLES_QUERY);
    }

    public void addStatus(String status) {
        db.execUpdate(ADD_STATUS_QUERY + "('" + status + "');");
    }

    public void addType(String type) {
        db.execUpdate(ADD_TYPE_QUERY + "('" + type + "');");
    }

    public void addTask(String task, int typeId, int statusId, int dateFrom, int dateTo) {
        final String query = ADD_TASK_QUERY + "('" +
                task + "'," + typeId + "," + statusId + "," + dateFrom + "," + dateTo + ");";
        db.execUpdate(query);
    }

    public ArrayList<Object[]> getStatuses() {
        ArrayList<Object[]> data = db.execMany(GET_STATUSES_QUERY);
        return data;
    }

    public ArrayList<Object[]> getTypes() {
        ArrayList<Object[]> data = db.execMany(GET_TYPES_QUERY);
        return data;
    }

    public ArrayList<Object[]> getTasks() {
        ArrayList<Object[]> data = db.execMany(GET_TASKS_QUERY);
        return data;
    }

    public ArrayList<Object[]> getData(String query) {
        ArrayList<Object[]> data = db.execMany(query);
        return data;
    }

}
