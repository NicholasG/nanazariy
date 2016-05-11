package com.kursova.dao;

import com.kursova.dao.queries.ModelQueries;
import com.kursova.domain.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDAO {

    // Додає запис в таблицю Товари
    public int insertModel( Model model ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    ModelQueries.getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            //region Вставка параметрів в запит
            statement.setString( 1, model.getModel() );
            statement.setString( 2, model.getSerial() );
            statement.setString( 3, model.getColor() );
            statement.setString( 4, model.getType() );
            statement.setString( 5, model.getDate().toString() );
            statement.setString( 6, model.getArchitecture() );
            statement.setInt( 7, model.getCharId() );
            statement.setInt( 8, model.getDeveloperId() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
            // Повернення нового ідентифікатора (id)
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    // Оновлює запис в таблиці Товари
    public void updateModel( Model model ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getUpdateQuery() );

            //region Вставка параметрів в запит
            statement.setString( 1, model.getModel() );
            statement.setString( 2, model.getSerial() );
            statement.setString( 3, model.getColor() );
            statement.setString( 4, model.getType() );
            statement.setString( 5, model.getDate().toString() );
            statement.setString( 6, model.getArchitecture() );
            statement.setInt( 7, model.getCharId() );
            statement.setInt( 8, model.getDeveloperId() );
            statement.setInt( 9, model.getId() );
            //endregion

            // Виконання запиту
            statement.executeUpdate();
        }

    }

    // Видаляє запис з таблиці Товари
    public void deleteModel( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getDeleteQuery() );

            // Вказуємо id товару, який буде видалено
            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    // Отримує всі записи з таблиці Товари
    public List<Model> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Model> result = new ArrayList<>();
            while ( rs.next() ) result.add( getModelFromRow( rs ) );
            return result;
        }
    }

    // Пошук по id
    public Model findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getModelFromRow( rs );
        }
        return null;
    }

    // Пошук по назві
    public List<Model> findAllByName( String name ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getSelectAllByNameQuery() );
            statement.setString( 1, name + '%' );

            ResultSet rs = statement.executeQuery();
            List<Model> result = new ArrayList<>();
            while ( rs.next() ) result.add( getModelFromRow( rs ) );
            return result;
        }
    }

    public List<Model> findAllByDeveloperId( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( ModelQueries.getSelectAllModelsByDeveloperId() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            List<Model> result = new ArrayList<>();
            while ( rs.next() ) result.add( getModelFromRow( rs ) );
            return result;
        }
    }

    private Model getModelFromRow( ResultSet rs ) throws SQLException {
        Model g = new Model();
        g.setId( rs.getInt( 1 ) );
        g.setModel( rs.getString( 2 ) );
        g.setSerial( rs.getString( 3 ) );
        g.setColor( rs.getString( 4 ) );
        g.setType( rs.getString( 5 ) );
        g.setDate( Date.valueOf( rs.getString( 6 ) ) );
        g.setArchitecture( rs.getString( 7 ) );
        g.setCharId( rs.getInt( 8 ) );
        g.setDeveloperId( rs.getInt( 9 ) );

        return g;
    }

}
