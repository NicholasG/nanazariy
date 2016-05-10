package com.kursova.dao;

import java.sql.*;

final class DataAccessUtil {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String CONNECTION_USERNAME = "root";
    private static final String CONNECTION_PASSWORD = "1111";

    static Connection createConnection() throws SQLException {
        try {
            Class.forName( DRIVER_CLASS );
        } catch ( ClassNotFoundException e ) {
            System.err.println( "ERROR: Driver class not found, cause: " + e.getMessage() );
        }

        return DriverManager.getConnection( CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD );
    }

    static void close( ResultSet resultSet ) {
        try {
            if ( resultSet != null && !resultSet.isClosed() ) resultSet.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    static int getNewRowKey( PreparedStatement statement ) throws SQLException {
        ResultSet rs = statement.getGeneratedKeys();
        if ( rs.next() ) return rs.getInt( 1 );
        return -1;
    }

}
