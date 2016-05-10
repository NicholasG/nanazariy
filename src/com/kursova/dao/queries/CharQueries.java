package com.kursova.dao.queries;

public final class CharQueries {

    private static final String INSERT_QUERY = "INSERT INTO char " +
            "(display, type_display, processor, frequency, RAM, type_RAM, " +
            "hard_disk, videocard, veb_cam, os) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE char " +
            "SET display = ?, type_display = ?, processor = ?, frequency = ?, " +
            "RAM = ?, type_RAM = ?, hard_disk = ?, videocard = ?, veb_cam = ?, os = ? " +
            "WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM char WHERE id = ?";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM char";

    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM char WHERE id = ?";

    public static String getSelectOneByIdQuery() {
        return SELECT_ONE_BY_ID_QUERY;
    }

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

}
