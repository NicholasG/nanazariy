package com.kursova.gui;

import com.kursova.dao.ModelDAO;
import com.kursova.domain.Model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by NicholasG on 10.04.2016.
 */
public class GoodsTableModel extends AbstractTableModel {

    private static final ModelDAO DEVELOPER_DAO = new ModelDAO();

    private final String[] columns = { "ID", "Назва", "Тип",
            "Виробник", "Артикул", "Ціна",
            "Од. вимір.", "Кількість", "Колір", "Опис" };

    private List<Model> models;
    private List<Model> oldModels = new ArrayList<>();

    private static String oldSearch = "";

    public GoodsTableModel( List<Model> models ) {
        this.models = models;
        this.oldModels.addAll( models );
        getSorted();
    }

    private void getSorted() {
        //region Ініціалізація сортування
        //Сортування по назві
        Comparator<Model> goodComparator = ( o1, o2 ) -> o1.getModel().compareToIgnoreCase( o2.getModel() );
        this.models.sort( goodComparator );
        this.oldModels.sort( goodComparator );
        //endregion
    }

    public static GoodsTableModel getGoodsTableModel( int shopId ) {
        try {
            java.util.List<Model> models = DEVELOPER_DAO.findAllGoodsByShopId( shopId );
            return new GoodsTableModel( models );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Не вдалося ініціалізувати таблицю товарів: " + e.getMessage() );
        }
        return new GoodsTableModel( new ArrayList<>() );
    }

    public static GoodsTableModel getGoodsTableModel() {
        try {
            final java.util.List<Model> models = DEVELOPER_DAO.findAll();
            return new GoodsTableModel( models );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Не вдалося ініціалізувати таблицю товарів: " + e.getMessage() );
        }
        return new GoodsTableModel( new ArrayList<>() );
    }

    public void search( String name ) {
        //region Пошук товару по назві
        //Якщо користувач видалив символ з поля пошуку, то пошук здійснюється по всіх елементах повторно
        models = oldSearch.length() > name.length() ? oldModels : models;
        //Якщо @name порожнє, то поточним списком товарів стає старий список всіх товарів
        if ( name.equals( "" ) ) models = oldModels;
        else models = models.stream()
                .filter( g -> g.getModel().toLowerCase()
                        .startsWith( name.toLowerCase() ) )
                .collect( Collectors.toList() );
        oldSearch = name;
        //endregion
        fireTableStructureChanged();
    }

    public void addGood( Model model ) {
        //region Додавання нового елемента
        models.add( model );
        oldModels.add( model );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void updateGood( Model model ) {
        //region Заміна старого елемента на новий
        UnaryOperator<Model> goodUnaryOperator = g -> {
            if ( g.getId() == model.getId() ) return model;
            else return g;
        };
        models.replaceAll( goodUnaryOperator );
        oldModels.replaceAll( goodUnaryOperator );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow( int rowIndex ) {
        oldModels.remove( models.get( rowIndex ) );
        models.remove( rowIndex );
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public Model getGoodFromRow( int rowIndex ) {
        return models.get( rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, models.size() );
    }

    public List<Model> getModels() {
        return models;
    }

    @Override
    public int getRowCount() {
        return models.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Model g = models.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( g.getId() );
            case 1:
                return g.getModel();
            case 2:
                return g.getSerial();
            case 3:
                return g.getType();
            case 4:
                return g.getDate();
            case 5:
                return String.valueOf( g.getPrice() );
            case 6:
                return g.getScale();
            case 7:
                return String.valueOf( g.getAmount() );
            case 8:
                return g.getColor();
            case 9:
                return g.getArchitecture();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName( int column ) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass( int columnIndex ) {
        return String.class;
    }

}
