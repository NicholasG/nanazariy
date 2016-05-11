package com.kursova.dao.queries;

public final class DeveloperQueries {

    private static final String INSERT_QUERY = "INSERT INTO developers " +
            "(brand, ofice, country, city, addres, phone, sait, founder, rating) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE developers " +
            "SET brand = ?, ofice = ?, country = ?, city = ?, addres = ?, " +
            "phone = ?, sait = ?, founder = ?, rating = ? " +
            "WHERE Id_dev = ?";
    private static final String DELETE_QUERY = "DELETE FROM developers WHERE Id_dev = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM developers";
    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM developers WHERE Id_dev = ?";
    private static final String SELECT_ALL_BY_NAME_QUERY = "SELECT * FROM developers WHERE UPPER(brand) LIKE UPPER(?)";

    public static String getInsertQuery() {
        return INSERT_QUERY;
    }

    public static String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    public static String getDeleteQuery() {
        return DELETE_QUERY;
    }

    public static String getSelectAllQuery() {
        return SELECT_ALL_QUERY;
    }

    public static String getSelectOneByIdQuery() {
        return SELECT_ONE_BY_ID_QUERY;
    }

    public static String getSelectAllByNameQuery() {
        return SELECT_ALL_BY_NAME_QUERY;
    }

}
