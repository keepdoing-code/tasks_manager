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
    public static final String GET_TASKS_VIEW = "SELECT tasks.id, tasks.task, types.tname, statuses.sname FROM tasks, statuses, types WHERE (tasks.tid = types.id and tasks.sid = statuses.id);";
    public static final String REMOVE_TASK = "DELETE FROM tasks WHERE id = ";
    public static final String UPDATE_TASK_STATUS = "UPDATE tasks SET sid = ? WHERE id = ?;";
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


    public DBQueries(String dbFilename) {
        this.dbFilename = dbFilename;
        this.dbWorker = new DBWorker(dbFilename);
    }

    public DBWorker getDbWorker() {
        return dbWorker;
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

    public void fillTestTasks() {
        this.addTask("Task 1", 1, 1, 0, 0);
        this.addTask("Task 2", 1, 1, 0, 0);
        this.addTask("Task 3", 2, 2, 0, 0);
        this.addTask("Task 4", 3, 3, 0, 0);
        this.addTask("Task 5", 1, 4, 0, 0);
    }

    public void createTables() {
        dbWorker.execUpdate(CREATE_TABLES_QUERY);
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

    public ArrayList<Object[]> getTasksView() {
        return getData(GET_TASKS_VIEW);
    }

    public void removeTask(int taskId) {
        dbWorker.execUpdate(REMOVE_TASK + taskId + ";");
    }

    public void changeTaskStatus(final int taskId, final int newStatus) {
        Object[] params = {newStatus, taskId};
        dbWorker.prepExec(UPDATE_TASK_STATUS, params);
    }

}
