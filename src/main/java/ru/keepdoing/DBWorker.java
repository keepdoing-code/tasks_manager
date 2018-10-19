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

    private ArrayList<String[]> exec(String query, QueryType queryType, Connection cn) {

        try {
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            ResultSet rs;
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
                    rs = st.executeQuery(query);
                    return this.readMany(rs);

                case execOne:
                    rs = st.executeQuery(query);
                    return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

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

    public ArrayList<String[]> readMany(ResultSet rs) throws SQLException{
            final int columnCount = rs.getMetaData().getColumnCount();
            ArrayList<String[]> data = new ArrayList<>();

            while (rs.next()) {
                String[] str = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    str[i - 1] = rs.getString(i);
                }
                data.add(str);
                return data;
            }
    }

    public String readOne(ResultSet rs) throws SQLException{
        final int count = rs.getMetaData().getColumnCount();
        if (count > 1){
            return "";
        }
        rs.first();
        return rs.getString(1);
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

    enum QueryType{
        exec, execUpdate, execQuery, execOne
    }

}
