package com.kursova.dao;

import com.kursova.dao.queries.CharQueries;
import com.kursova.domain.Char;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharDAO {

    // Додає запис в таблицю Товари
    public int insertChar( Char aChar ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    CharQueries.getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            //region Вставка параметрів в запит
            statement.setString( 1, aChar.getDisplay() );
            statement.setString( 2, aChar.getTypeOfDisplay() );
            statement.setString( 3, aChar.getProcessor() );
            statement.setString( 4, aChar.getFrequency() );
            statement.setString( 5, aChar.getRam() );
            statement.setString( 6, aChar.getTypeOfRam() );
            statement.setString( 7, aChar.getHardDisk() );
            statement.setString( 8, aChar.getVideoCard() );
            statement.setString( 9, aChar.getWebCam() );
            statement.setString( 10, aChar.getOs() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
            // Повернення нового ідентифікатора (id)
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    // Оновлює запис в таблиці Товари
    public void updateChar( Char aChar ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( CharQueries.getUpdateQuery() );

            //region Вставка параметрів в запит
            statement.setString( 1, aChar.getDisplay() );
            statement.setString( 2, aChar.getTypeOfDisplay() );
            statement.setString( 3, aChar.getProcessor() );
            statement.setString( 4, aChar.getFrequency() );
            statement.setString( 5, aChar.getRam() );
            statement.setString( 6, aChar.getTypeOfRam() );
            statement.setString( 7, aChar.getHardDisk() );
            statement.setString( 8, aChar.getVideoCard() );
            statement.setString( 9, aChar.getWebCam() );
            statement.setString( 10, aChar.getOs() );
            statement.setInt( 11, aChar.getId() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
        }

    }

    // Видаляє запис з таблиці Товари
    public void deleteChar( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( CharQueries.getDeleteQuery() );

            // Вказуємо id товару, який буде видалено
            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    // Отримує всі записи з таблиці Товари
    public List<Char> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( CharQueries.getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Char> result = new ArrayList<>();
            while ( rs.next() ) result.add( getCharFromRow( rs ) );
            return result;
        }
    }

    // Пошук по id
    public Char findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( CharQueries.getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getCharFromRow( rs );
        }
        return null;
    }

    private Char getCharFromRow( ResultSet rs ) throws SQLException {
        Char g = new Char();
        g.setId( rs.getInt( 1 ) );
        g.setDisplay( rs.getString( 2 ) );
        g.setTypeOfDisplay( rs.getString( 3 ) );
        g.setProcessor( rs.getString( 4 ) );
        g.setFrequency( rs.getString( 5 ) );
        g.setRam( rs.getString( 6 ) );
        g.setTypeOfRam( rs.getString( 7 ) );
        g.setHardDisk( rs.getString( 8 ) );
        g.setVideoCard( rs.getString( 9 ) );
        g.setWebCam( rs.getString( 10 ) );
        g.setOs( rs.getString( 11 ) );

        return g;
    }


}
