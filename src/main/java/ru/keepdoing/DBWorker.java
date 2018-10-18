package ru.keepdoing;

import java.sql.*;

public class DBWorker {

    private final String connectionString = "jdbc:sqlite:";
    private final String filename;
    private Connection connection;

    public Connection connect(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionString + filename);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void execQuery(String query, QueryType queryType){
        Connection cn = this.connect();
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            switch (queryType){
                case exec: st.execute(query);
                    break;
                case execUpdate: st.executeUpdate(query);
                    break;
                case execQuery: st.executeQuery(query);
                    break;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public void createTables(){
        this.execQuery(Queries.createTablesQuery, QueryType.exec);
    }

    public void dropTables(){
        this.execQuery(Queries.dropTablesQuery, QueryType.exec);
    }

    public DBWorker(String file) {
        this.filename = file;

        try {
            connection = DriverManager.getConnection(connectionString + filename);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.execute(Queries.setPragmaOn);
            statement.execute(Queries.dropTablesQuery);
            statement.execute(Queries.createTablesQuery);
            statement.executeUpdate(Queries.insertDataQuery);
            ResultSet rs = statement.executeQuery("SELECT * FROM task;");
            while (rs.next()) {
                System.out.printf("id - %s | name - %s\n", rs.getString("id"), rs.getString("task"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    enum QueryType{
        exec, execUpdate, execQuery;
    }

}
