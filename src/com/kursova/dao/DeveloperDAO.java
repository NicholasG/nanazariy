package com.kursova.dao;

import com.kursova.domain.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kursova.dao.queries.DeveloperQueries.*;

public class DeveloperDAO {

    public int insertDeveloper( Developer developer ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            statement.setString( 1, developer.getBrand() );
            statement.setString( 2, developer.getOffice() );
            statement.setString( 3, developer.getCountry() );
            statement.setString( 4, developer.getCity() );
            statement.setString( 5, developer.getAddress() );
            statement.setString( 6, developer.getPhone() );
            statement.setString( 7, developer.getSite() );
            statement.setString( 8, developer.getFounder() );
            statement.setString( 9, developer.getRating() );

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    public void updateDeveloper( Developer developer ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getUpdateQuery() );

            statement.setString( 1, developer.getBrand() );
            statement.setString( 2, developer.getOffice() );
            statement.setString( 3, developer.getCountry() );
            statement.setString( 4, developer.getCity() );
            statement.setString( 5, developer.getAddress() );
            statement.setString( 6, developer.getPhone() );
            statement.setString( 7, developer.getSite() );
            statement.setString( 8, developer.getFounder() );
            statement.setString( 9, developer.getRating() );
            statement.setInt( 10, developer.getId() );

            statement.executeUpdate();
        }
    }

    public void deleteDeveloper( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getDeleteQuery() );

            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    public List<Developer> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Developer> result = new ArrayList<>();
            while ( rs.next() ) result.add( getDeveloperFromRow( rs ) );
            return result;
        }
    }

    public Developer findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getDeveloperFromRow( rs );
        }

        return null;
    }

    public List<Developer> findAllByName( String name ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllByNameQuery() );
            statement.setString( 1, name + '%' );

            ResultSet rs = statement.executeQuery();
            List<Developer> result = new ArrayList<>();
            while ( rs.next() ) result.add( getDeveloperFromRow( rs ) );
            return result;
        }
    }

    private Developer getDeveloperFromRow( ResultSet rs ) throws SQLException {
        Developer d = new Developer();
        d.setId( rs.getInt( 1 ) );
        d.setBrand( rs.getString( 2 ) );
        d.setAddress( rs.getString( 3 ) );
        d.setPhone( rs.getString( 4 ) );
        d.setFounder( rs.getString( 5 ) );
        d.setSite( rs.getString( 6 ) );
        d.setRating( rs.getString( 7 ) );
        d.setRating( rs.getString( 8 ) );
        d.setRating( rs.getString( 9 ) );
        d.setRating( rs.getString( 10 ) );

        return d;
    }

}
