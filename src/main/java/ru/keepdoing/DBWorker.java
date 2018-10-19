package ru.keepdoing;

import java.sql.*;
import java.util.ArrayList;

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

    private void exec(String query, Connection cn){
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            st.execute(query);
            closeConnection(cn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void execUpdate(String query, Connection cn){
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            st.executeUpdate(query);
            closeConnection(cn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private Object execOne(String query, Connection cn){
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            ResultSet rs = st.executeQuery(query);
            final int count = rs.getMetaData().getColumnCount();
            if (count > 1){
                return "";
            }
            rs.first();
            Object obj = rs.getObject(1);
            closeConnection(cn);
            return obj;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private ArrayList<Object[]> execMany(String query, Connection cn){
        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            ResultSet rs = st.executeQuery(query);
            final int columnCount = rs.getMetaData().getColumnCount();
            ArrayList<Object[]> data = new ArrayList<>();

            while (rs.next()) {
                Object[] obj = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    obj[i - 1] = rs.getObject(i);
                }
                data.add(obj);
            }
            closeConnection(cn);
            return data;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void createTables(){
        this.exec(Queries.createTablesQuery, this.connect());
    }

    public void dropTables(){
        this.exec(Queries.dropTablesQuery, this.connect());
    }

    public void addStatus(String status) {
        exec(Queries.addStatus + "('" + status + "');", this.connect());
    }

    public void addType(String type) {
        exec(Queries.addType + "('" + type + "');", this.connect());
    }

    public void showStatuses() {
        ArrayList<String[]> data = exec(Queries.showStatuses,QueryType.execQuery,this.connect());
        for (String[] arr : data) {
            for (String s : arr){
                System.out.printf(" %s |", s);
            }
            System.out.printf("\n");
        }
    }

    public DBWorker(String file) {
        this.filename = file;

        dropTables();
        createTables();
        addStatus("Open");
        addStatus("Close");
        showStatuses();
    }

}
