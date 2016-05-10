package com.kursova.dao.queries;

public final class ShopQueries {

    private static final String INSERT_QUERY = "INSERT INTO shop " +
            "(name, address, phone, chief, site, schedule) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE shop " +
            "SET name = ?, address = ?, phone = ?, chief = ?, site = ?, schedule = ? " +
            "WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM shop WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM shop";
    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM shop WHERE id = ?";
    private static final String SELECT_ALL_BY_NAME_QUERY = "SELECT * FROM shop WHERE UPPER(name) LIKE UPPER(?)";

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
