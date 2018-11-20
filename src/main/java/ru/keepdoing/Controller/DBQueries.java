package ru.keepdoing.Controller;

import java.util.ArrayList;

public class DBQueries {
    private final DBWorker dbWorker;
    public static final String PRAGMA_FOREIGN_KEYS_ON = "PRAGMA foreign_keys = ON;";
    public static final String ADD_STATUS_QUERY = "INSERT INTO statuses(status) VALUES (?);";
    public static final String ADD_TYPE_QUERY = "INSERT INTO types(type) VALUES (?);";
    public static final String ADD_TASK_QUERY_PREP = "INSERT INTO tasks(task, tid, sid, started, deadline) VALUES (?, ?, ?, ?, ?);";
    public static final String GET_STATUSES_QUERY = "SELECT * FROM statuses;";
    public static final String GET_TYPES_QUERY = "SELECT * FROM types;";
    public static final String UPDATE_TASK_STATUS = "UPDATE tasks SET sid = ? WHERE id = ?;";
    public static final String REMOVE_TASK = "DELETE FROM tasks WHERE id = ?;";
    public static final String DROP_TABLES_QUERY = "DROP TABLE if exists statuses; DROP TABLE if exists types; DROP TABLE if exists tasks;";
    public static final String GET_TASKS_VIEW =
            "SELECT tasks.id, tasks.task, types.type, statuses.status, tasks.started, tasks.deadline " +
                    "FROM tasks, statuses, types " +
                    "WHERE (tasks.tid = types.id and tasks.sid = statuses.id);";
    public static final String CREATE_TABLES_QUERY =
            "CREATE TABLE if not exists statuses (id INTEGER PRIMARY KEY AUTOINCREMENT, status text);" +
                    "CREATE TABLE if not exists types (id INTEGER PRIMARY KEY AUTOINCREMENT, type text);" +
                    "CREATE TABLE if not exists tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "task TEXT, " +
                    "tid INTEGER, " +
                    "sid INTEGER, " +
                    "started INTEGER, " +
                    "deadline INTEGER," +
                    "FOREIGN KEY(tid) REFERENCES types(id) ON DELETE CASCADE , " +
                    "FOREIGN KEY(sid) REFERENCES statuses(id) ON DELETE CASCADE" +
                    ");";


    public DBQueries(String dbFilename) {
        this.dbWorker = new DBWorker(dbFilename);
    }

    public void settingUp() {
        dbWorker.exec(PRAGMA_FOREIGN_KEYS_ON);
    }

    public void fillTables() {
        this.addType("immediate");
        this.addType("important");
        this.addType("so-so");
        this.addType("regular");

        this.addStatus("not started");
        this.addStatus("postponed");
        this.addStatus("working");
        this.addStatus("done");
    }

    public void createTables() {
        dbWorker.execUpdate(CREATE_TABLES_QUERY);
    }

    public void dropTables() {
        dbWorker.execUpdate(DROP_TABLES_QUERY);
    }

    public void addStatus(String status) {
        Object[] params = {status};
        dbWorker.prepExec(ADD_STATUS_QUERY, params);
    }

    public void addType(String type) {
        Object[] params = {type};
        dbWorker.prepExec(ADD_TYPE_QUERY, params);
    }

    public void addTask(String task, int typeId, int statusId, String dateFrom, String dateTo) {
        Object[] params = {task, typeId, statusId, dateFrom, dateTo};
        dbWorker.prepExec(ADD_TASK_QUERY_PREP, params);
    }

    public ArrayList<Object[]> getData(String query) {
        return dbWorker.execMany(query);
    }

    public ArrayList<Object[]> getStatuses() {
        return getData(GET_STATUSES_QUERY);
    }

    public ArrayList<Object[]> getTypes() {
        return getData(GET_TYPES_QUERY);
    }

    public ArrayList<Object[]> getTasksView() {
        return getData(GET_TASKS_VIEW);
    }

    public void removeTask(int taskId) {
        Object[] params = {taskId};
        dbWorker.prepExec(REMOVE_TASK, params);
    }

    public void changeTaskStatus(final int taskId, final int newStatus) {
        Object[] params = {newStatus, taskId};
        dbWorker.prepExec(UPDATE_TASK_STATUS, params);
    }

}
