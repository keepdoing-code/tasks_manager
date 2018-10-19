package ru.keepdoing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {

    private final String connectionString = "jdbc:sqlite:";
    private final String filename;
//    private Connection connection = null;

    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionString + filename);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    private void closeConnection(Connection cn){
        try {
            if (cn != null)
                cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private ResultSet exec(String query, QueryType queryType, Connection cn) {

        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            switch (queryType){
                case exec:
                    st.execute(query);
                    closeConnection(cn);
                    break;
                case execUpdate:
                    st.executeUpdate(query);
                    closeConnection(cn);
                    break;
                case execQuery:
                    ResultSet rs = st.executeQuery(query);
                    final int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf(" %s |", rs.getMetaData().getColumnLabel(i));
                    }
                    System.out.printf("\n");

                    while (rs.next()) {
                        for(int i = 1; i <= columnCount; i++){
                            System.out.printf(" %s |", rs.getString(i));
                        }
                        System.out.printf("\n");
                    }
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
        this.exec(Queries.createTablesQuery, QueryType.exec, this.connect());
    }

    public void dropTables(){
        this.exec(Queries.dropTablesQuery, QueryType.exec, this.connect());
    }

    public void addStatus(String status) {
        exec(Queries.addStatus + "('" + status + "');", QueryType.execUpdate, this.connect());
    }

    public void addType(String type) {
        exec(Queries.addType + "('" + type + "');", QueryType.execUpdate, this.connect());
    }

    public List<String> readData(String table){

        return null;
    }

    public void showStatuses() {
        exec(Queries.showStatuses,QueryType.execQuery,this.connect());
    }

    public DBWorker(String file) {
        this.filename = file;

        dropTables();
        createTables();
        addStatus("Open");
        addStatus("Close");
        showStatuses();
    }

    enum QueryType{
        exec, execUpdate, execQuery
    }

}
