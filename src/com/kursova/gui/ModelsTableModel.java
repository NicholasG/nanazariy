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

public class ModelsTableModel extends AbstractTableModel {

    private static final ModelDAO MODEL_DAO = new ModelDAO();

    private final String[] columns = { "ID", "Model", "Serial",
            "Color", "Type", "Date",
            "Architecture" };

    private List<Model> models;
    private List<Model> oldModels = new ArrayList<>();

    private static String oldSearch = "";

    public ModelsTableModel( List<Model> models ) {
        this.models = models;
        this.oldModels.addAll( models );
        getSorted();
    }

    private void getSorted() {
        //region Ініціалізація сортування
        //Сортування по model
        Comparator<Model> goodComparator = ( o1, o2 ) -> o1.getModel().compareToIgnoreCase( o2.getModel() );
        this.models.sort( goodComparator );
        this.oldModels.sort( goodComparator );
        //endregion
    }

    public static ModelsTableModel getGoodsTableModel( int developerId ) {
        try {
            java.util.List<Model> models = MODEL_DAO.findAllByDeveloperId( developerId );
            return new ModelsTableModel( models );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Не вдалося ініціалізувати таблицю товарів: " + e.getMessage() );
        }
        return new ModelsTableModel( new ArrayList<>() );
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

    public void addModel( Model model ) {
        //region Додавання нового елемента
        models.add( model );
        oldModels.add( model );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void updateModel( Model model ) {
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

    public Model getModelFromRow( int rowIndex ) {
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
    public String getColumnName( int column ) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass( int columnIndex ) {
        return String.class;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Model m = models.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( m.getId() );
            case 1:
                return m.getModel();
            case 2:
                return m.getSerial();
            case 3:
                return m.getColor();
            case 4:
                return m.getType();
            case 5:
                return m.getDate().toString();
            case 6:
                return m.getArchitecture();
            default:
                return null;
        }
    }

}
