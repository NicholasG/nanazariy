package com.kursova.dao;

import com.kursova.domain.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kursova.dao.queries.ShopQueries.*;

public class ShopDAO {

    public int insertShop( Shop shop ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            statement.setString( 1, shop.getName() );
            statement.setString( 2, shop.getAddress() );
            statement.setString( 3, shop.getPhone() );
            statement.setString( 4, shop.getChief() );
            statement.setString( 5, shop.getSite() );
            statement.setString( 6, shop.getSchedule() );

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    public void updateShop( Shop shop ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getUpdateQuery() );

            statement.setString( 1, shop.getName() );
            statement.setString( 2, shop.getAddress() );
            statement.setString( 3, shop.getPhone() );
            statement.setString( 4, shop.getChief() );
            statement.setString( 5, shop.getSite() );
            statement.setString( 6, shop.getSchedule() );
            statement.setInt( 7, shop.getId() );

            statement.executeUpdate();
        }
    }

    public void deleteShop( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getDeleteQuery() );

            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    public List<Shop> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Shop> result = new ArrayList<>();
            while ( rs.next() ) result.add( getShopFromRow( rs ) );
            return result;
        }
    }

    public Shop findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getShopFromRow( rs );
        }

        return null;
    }

    public List<Shop> findAllByName( String name ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllByNameQuery() );
            statement.setString( 1, name + '%' );

            ResultSet rs = statement.executeQuery();
            List<Shop> result = new ArrayList<>();
            while ( rs.next() ) result.add( getShopFromRow( rs ) );
            return result;
        }
    }

    private Shop getShopFromRow( ResultSet rs ) throws SQLException {
        Shop s = new Shop();
        s.setId( rs.getInt( 1 ) );
        s.setName( rs.getString( 2 ) );
        s.setAddress( rs.getString( 3 ) );
        s.setPhone( rs.getString( 4 ) );
        s.setChief( rs.getString( 5 ) );
        s.setSite( rs.getString( 6 ) );
        s.setSchedule( rs.getString( 7 ) );

        return s;
    }

}
