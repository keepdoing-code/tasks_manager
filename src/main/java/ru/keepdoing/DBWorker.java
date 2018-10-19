package ru.keepdoing;

import javax.management.Query;
import java.sql.*;

public class DBWorker {

    private final String connectionString = "jdbc:sqlite:";
    private final String filename;
    private Connection connection;

    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionString + filename);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    private ResultSet exec(String query, QueryType queryType) {
        Connection cn = this.connect();
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            switch (queryType){
                case exec: st.execute(query);
                    break;
                case execUpdate: st.executeUpdate(query);
                    break;
                case execQuery:
                    return st.executeQuery(query);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
//            try {
//                if (cn != null)
//                    cn.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
        }
        return null;
    }

    public void createTables(){
        this.exec(Queries.createTablesQuery, QueryType.exec);
    }

    public void dropTables(){
        this.exec(Queries.dropTablesQuery, QueryType.exec);
    }

    public void addStatus(String status) {
        exec(Queries.addStatus + "('" + status + "');", QueryType.execUpdate);
    }

    public void showStatuses() {
        try {
            ResultSet rs = exec(Queries.showStatuses, QueryType.execQuery);
            while (rs.next()) {
                System.out.printf("%s | %s \n", rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DBWorker(String file) {
        this.filename = file;

        dropTables();
        createTables();
        addStatus("Open");
        addStatus("Close");
        showStatuses();


//        try {
//            connection = DriverManager.getConnection(connectionString + filename);
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);
//            statement.execute(Queries.setPragmaOn);
//            statement.execute(Queries.dropTablesQuery);
//            statement.execute(Queries.createTablesQuery);
//            statement.executeUpdate(Queries.insertDataQuery);
//            ResultSet rs = statement.executeQuery("SELECT * FROM task;");
//            while (rs.next()) {
//                System.out.printf("id - %s | name - %s\n", rs.getString("id"), rs.getString("task"));
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }


    }

    enum QueryType{
        exec, execUpdate, execQuery;
    }

}
