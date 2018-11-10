package ru.keepdoing.Controller;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {

    private final String filename;

    DBWorker(String file) {
        this.filename = file;
    }

    public void exec(String query) {
        try {
            Connection cn = this.connect();
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            st.execute(query);
            this.closeConnection(cn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void execUpdate(String query) {
        try {
            Connection cn = this.connect();
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            st.executeUpdate(query);
            closeConnection(cn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Object execOne(String query) {
        try {
            Connection cn = this.connect();
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            ResultSet rs = st.executeQuery(query);
            final int count = rs.getMetaData().getColumnCount();
            if (count > 1) {
                return "";
            }
            rs.first();
            Object obj = rs.getObject(1);
            closeConnection(cn);
            return obj;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Object[]> execMany(String query) {
        try {
            Connection cn = this.connect();
            Statement st = cn.createStatement();
            st.setQueryTimeout(30);
            ResultSet rs = st.executeQuery(query);
            final int columnCount = rs.getMetaData().getColumnCount();
            ArrayList<Object[]> data = new ArrayList<>();

            while (rs.next()) {
                Object[] obj = new Object[columnCount];
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
        return null;
    }

    private Connection connect() throws SQLException {
        Connection con;
        String connectionString = "jdbc:sqlite:";
        con = DriverManager.getConnection(connectionString + filename);
        return con;
    }

    private void closeConnection(Connection cn) throws SQLException {
        if (cn != null)
            cn.close();
    }

}
