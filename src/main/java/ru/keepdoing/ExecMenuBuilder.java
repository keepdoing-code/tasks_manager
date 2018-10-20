package ru.keepdoing;

;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecMenuBuilder {

    private String screenMenu;
    private final String menuName;
    private HashMap<Integer, MenuItem> menuExec = new HashMap<>();


    public ExecMenuBuilder(String menuName) {
        this.menuName = menuName;
        screenMenu += menuName + "\n";
    }

    public ExecMenuBuilder add(Integer itemId, MenuItem item) {
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

        MenuItem item = menuExec.get(itemId);
        String query = item.getQuery();
        QueryType queryType = item.getType();

        if ("Exit".equals(query) || queryType == QueryType.none) {
            System.exit(0);
        }

        switch (queryType) {
            case exec:
                dbWorker.exec(query);
                break;
            case update:
                dbWorker.execUpdate(query);
                break;
            case one:
                dbWorker.execOne(query);
                break;
            case many:
                return dbWorker.execMany(query);
        }
        return null;
    }

    public String get() {
        return screenMenu;
    }

}

class MenuItem {
    private final String name;
    private final String query;
    private final QueryType queryType;

    public MenuItem(final String name, final String query, QueryType qt) {
        this.name = name;
        this.query = query;
        this.queryType = qt;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public QueryType getType() {
        return queryType;
    }

}
