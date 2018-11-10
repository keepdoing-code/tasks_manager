package ru.keepdoing.Menu;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Controller.DBWorker;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleExecMenuBuilder {

    private String screenMenu = "";
    private final String menuName;
    private HashMap<Integer, ExecMenuItem> menuExec = new HashMap<>();


    public SimpleExecMenuBuilder(String menuName) {
        this.menuName = menuName;
        screenMenu += menuName + "\n";
    }

    public SimpleExecMenuBuilder add(Integer itemId, ExecMenuItem item) {
        menuExec.put(itemId, item);

        //TODO use stringBuilder for this:
        screenMenu += "\t" + itemId + ". " + item.getName() + "\n";
        return this;
    }

    //TODO Think about DBQueries and DBWorker. What we need to transfer into this method?
    //TODO I have a thought that we need DBQueries in Console gui to setting up db connection (Foreign key constraint).
    //TODO Here we just need the data base file name on what we exec query.

    public ArrayList<Object[]> exec(Integer itemId, DBQueries dbQueries) {
        DBWorker dbWorker = dbQueries.getDbWorker();

        ExecMenuItem item = menuExec.get(itemId);
        String query = item.getQuery();
        QueryType queryType = item.getType();

        //TODO  "Exit" also is a type of menu item in future refactoring
        if ("Exit".equals(query) || queryType == QueryType.none) {
            System.exit(0);
        }

        // TODO  To unify functionality we can wrap query and dbWorker into dbQueries for send to separate methods
        switch (queryType) {
            case exec:
                dbWorker.exec(query);
                break;
            case update:
                dbWorker.execUpdate(query);
                break;
            case one:
//                Object[] objects = new Object[1];
//                objects[0] = dbWorker.execOne(query);
                break;
            case many:
                return dbWorker.execMany(query);
        }
        return null;
    }

    public String getString() {
        return screenMenu;
    }
}