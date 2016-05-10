package com.kursova.dao.queries;

public final class GoodQueries {

    // Вставити новий запис в таблицю Товари
    private static final String INSERT_QUERY = "INSERT INTO good " +
            "(name, type, manufacturer, article, price, scale, amount, color, specifications) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // Оновити запис в таблиці Товари
    private static final String UPDATE_QUERY = "UPDATE good " +
            "SET name = ?, type = ?, manufacturer = ?, article = ?, price = ?, scale = ?, " +
            "amount = ?, color = ?, specifications = ? " +
            "WHERE id = ?";
    // Видалити запис з таблиці Товари
    private static final String DELETE_QUERY = "DELETE FROM good WHERE id = ?";
    // Отримати всі записи з таблиці Товари
    private static final String SELECT_ALL_QUERY = "SELECT * FROM good";
    // Знайти конкретний запис по id
    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM good WHERE id = ?";
    // Пошук всіх записів по назві
    private static final String SELECT_ALL_BY_NAME_QUERY = "SELECT * FROM good WHERE UPPER(name) LIKE UPPER(?)";
    // Добавити товар в магазин
    private static final String INSERT_GOOD_INTO_SHOP_QUERY = "INSERT INTO good_has_shop " +
            "(good_id, shop_id) " +
            "VALUES (?, ?)";
    // Отримати всі товари з магазину по id
    private static final String SELECT_ALL_GOODS_BY_SHOP_ID = "SELECT good_id FROM good_has_shop WHERE shop_id = ?";
    // Видалити товар з магазину
    private static final String DELETE_GOOD_FROM_SHOP_QUERY = "DELETE FROM good_has_shop " +
            "WHERE shop_id = ? AND good_id = ?";

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

    public static String getInsertGoodIntoShopQuery() {
        return INSERT_GOOD_INTO_SHOP_QUERY;
    }

    public static String getSelectAllGoodsByShopId() {
        return SELECT_ALL_GOODS_BY_SHOP_ID;
    }

    public static String getDeleteGoodFromShopQuery() {
        return DELETE_GOOD_FROM_SHOP_QUERY;
    }

}
