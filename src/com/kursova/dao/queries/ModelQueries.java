package com.kursova.dao.queries;

public final class ModelQueries {

    // Вставити новий запис в таблицю Товари
    private static final String INSERT_QUERY = "INSERT INTO models " +
            "(model, serial_number, color, type, date, architecture, " +
            "char_id_char, developers_Id_dev) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    // Оновити запис в таблиці Товари
    private static final String UPDATE_QUERY = "UPDATE models " +
            "SET model = ?, type = ?, serial_number = ?, color = ?, " +
            "type = ?, date = ?, architecture = ?, char_id_char = ?, developers_Id_dev = ? " +
            "WHERE id = ?";
    // Видалити запис з таблиці Товари
    private static final String DELETE_QUERY = "DELETE FROM models WHERE id = ?";
    // Отримати всі записи з таблиці Товари
    private static final String SELECT_ALL_QUERY = "SELECT * FROM models";
    // Знайти конкретний запис по id
    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM models WHERE id = ?";
    // Пошук всіх записів по назві
    private static final String SELECT_ALL_BY_NAME_QUERY = "SELECT * FROM models WHERE UPPER(name) LIKE UPPER(?)";
    //
    private static final String SELECT_ALL_MODELS_BY_DEVELOPER_ID = "SELECT * FROM models " +
            "WHERE developers_Id_dev = ?";

    public static String getSelectAllByNameQuery() {
        return SELECT_ALL_BY_NAME_QUERY;
    }

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

    public static String getSelectAllModelsByDeveloperId() {
        return SELECT_ALL_MODELS_BY_DEVELOPER_ID;
    }
}
