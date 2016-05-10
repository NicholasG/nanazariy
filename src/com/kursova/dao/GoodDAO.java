package com.kursova.dao;

import com.kursova.dao.queries.GoodQueries;
import com.kursova.domain.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodDAO {

    // Додає запис в таблицю Товари
    public int insertGood( Good good ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    GoodQueries.getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            //region Вставка параметрів в запит
            statement.setString( 1, good.getName() );
            statement.setString( 2, good.getType() );
            statement.setString( 3, good.getManufacturer() );
            statement.setString( 4, good.getArticle() );
            statement.setFloat( 5, good.getPrice() );
            statement.setString( 6, good.getScale() );
            statement.setInt( 7, good.getAmount() );
            statement.setString( 8, good.getColor() );
            statement.setString( 9, good.getSpecifications() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
            // Повернення нового ідентифікатора (id)
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    // Оновлює запис в таблиці Товари
    public void updateGood( Good good ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getUpdateQuery() );

            //region Вставка параметрів в запит
            statement.setString( 1, good.getName() );
            statement.setString( 2, good.getType() );
            statement.setString( 3, good.getManufacturer() );
            statement.setString( 4, good.getArticle() );
            statement.setFloat( 5, good.getPrice() );
            statement.setString( 6, good.getScale() );
            statement.setInt( 7, good.getAmount() );
            statement.setString( 8, good.getColor() );
            statement.setString( 9, good.getSpecifications() );
            statement.setInt( 10, good.getId() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
        }

    }

    // Видаляє запис з таблиці Товари
    public void deleteGood( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getDeleteQuery() );

            // Вказуємо id товару, який буде видалено
            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    // Отримує всі записи з таблиці Товари
    public List<Good> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Good> result = new ArrayList<>();
            while ( rs.next() ) result.add( getGoodFromRow( rs ) );
            return result;
        }
    }

    // Пошук по id
    public Good findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getGoodFromRow( rs );
        }
        return null;
    }

    // Пошук по назві
    public List<Good> findAllByName( String name ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getSelectAllByNameQuery() );
            statement.setString( 1, name + '%' );

            ResultSet rs = statement.executeQuery();
            List<Good> result = new ArrayList<>();
            while ( rs.next() ) result.add( getGoodFromRow( rs ) );
            return result;
        }
    }

    // Додає товар в магазин
    public int insertGoodIntoShop( int goodId, int shopId ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    GoodQueries.getInsertGoodIntoShopQuery(), Statement.RETURN_GENERATED_KEYS );

            statement.setInt( 1, goodId );
            statement.setInt( 2, shopId );

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey( statement );
        }

    }

    // Пошук всіх товарів, які є в магазині
    public List<Good> findAllGoodsByShopId( int shopId ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            //region Пошук всіх індексів товарів, які знаходяться в магазині з індексом @shopId
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getSelectAllGoodsByShopId() );
            statement.setInt( 1, shopId );

            ResultSet rs = statement.executeQuery();
            List<Integer> var1 = new ArrayList<>();
            while ( rs.next() ) var1.add( rs.getInt( 1 ) );
            //endregion

            //region Витягування всіх товарів по індексах
            List<Good> result = new ArrayList<>();
            var1.stream().forEach( id -> {
                try {
                    result.add( findById( id ) );
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            } );
            //endregion
            return result;
        }
    }

    // Вадаляє товар з магазину
    public void deleteGoodFromShop( int shopId, int goodId ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( GoodQueries.getDeleteGoodFromShopQuery() );
            statement.setInt( 1, shopId );
            statement.setInt( 2, goodId );

            statement.executeUpdate();
        }
    }

    private Good getGoodFromRow( ResultSet rs ) throws SQLException {
        Good g = new Good();
        g.setId( rs.getInt( 1 ) );
        g.setName( rs.getString( 2 ) );
        g.setType( rs.getString( 3 ) );
        g.setManufacturer( rs.getString( 4 ) );
        g.setArticle( rs.getString( 5 ) );
        g.setPrice( rs.getFloat( 6 ) );
        g.setScale( rs.getString( 7 ) );
        g.setAmount( rs.getInt( 8 ) );
        g.setColor( rs.getString( 9 ) );
        g.setSpecifications( rs.getString( 10 ) );

        return g;
    }

}
