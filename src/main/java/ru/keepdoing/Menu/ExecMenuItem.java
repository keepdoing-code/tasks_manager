package ru.keepdoing.Menu;

class ExecMenuItem {
    private final String name;
    private final String query;
    private final QueryType queryType;

    public ExecMenuItem(final String name, final String query, QueryType qt) {
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
