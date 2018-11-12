package ru.keepdoing.Controller;

import java.util.ArrayList;


//TODO rename 'dfrom' to 'created' and 'dto' to 'deadline'
//TODO add types: important immediate, not important immediate, important not immediate, not important not immediate
public class DBQueries {
    public static final String PRAGMA_FOREIGN_KEYS_ON = "PRAGMA foreign_keys = ON;";
    public static final String DROP_TABLES_QUERY = "DROP TABLE if exists statuses; DROP TABLE if exists types; DROP TABLE if exists tasks;";
    public static final String ADD_STATUS_QUERY = "INSERT INTO statuses(sname) VALUES ";
    public static final String ADD_TYPE_QUERY = "INSERT INTO types(tname) VALUES ";
    public static final String ADD_TASK_QUERY = "INSERT INTO tasks(task, tid, sid, dfrom, dto) VALUES ";
    public static final String GET_STATUSES_QUERY = "SELECT * FROM statuses;";
    public static final String GET_TYPES_QUERY = "SELECT * FROM types;";
    public static final String GET_TASKS_QUERY = "SELECT * FROM tasks;";
    public static final String TASKS_VIEW = "SELECT tasks.task, statuses.sname, types.tname FROM tasks, statuses, types WHERE (tasks.tid = types.id and tasks.sid = statuses.id);";
    public static final String CREATE_TABLES_QUERY =
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

    private final DBWorker dbWorker;
    private final String dbFilename;


    public DBQueries(String dbFilename){
        this.dbFilename = dbFilename;
        this.dbWorker = new DBWorker(dbFilename);
    }

    public String getFilename() {
        return dbFilename;
    }

    public DBWorker getDbWorker() {
        return dbWorker;
    }

    public void settingUp() {
        dbWorker.exec(PRAGMA_FOREIGN_KEYS_ON);
    }

    public void createTables() {
        dbWorker.execUpdate(CREATE_TABLES_QUERY);
    }

    public void dropTables() {
        dbWorker.execUpdate(DROP_TABLES_QUERY);
    }

    public void addStatus(String status) {
        dbWorker.execUpdate(ADD_STATUS_QUERY + "('" + status + "');");
    }

    public void addType(String type) {
        dbWorker.execUpdate(ADD_TYPE_QUERY + "('" + type + "');");
    }

    public void addTask(String task, int typeId, int statusId, int dateFrom, int dateTo) {
        final String query = ADD_TASK_QUERY + "('" +
                task + "'," + typeId + "," + statusId + "," + dateFrom + "," + dateTo + ");";
        dbWorker.execUpdate(query);
    }

    public ArrayList<Object[]> getData(String query) {
        ArrayList<Object[]> data = dbWorker.execMany(query);
        return data;
    }

    public ArrayList<Object[]> getStatuses() {
        return getData(GET_STATUSES_QUERY);
    }

    public ArrayList<Object[]> getTypes() {
        return getData(GET_TYPES_QUERY);
    }

    public ArrayList<Object[]> getTasks() {
        return getData(GET_TASKS_QUERY);
    }

}