package com.kursova.gui;

import com.kursova.domain.Developer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by NicholasG on 10.04.2016.
 */
public class DevelopersTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Brand", "Office", "City",
            "Address", "Phone", "Website", "Founder", "Rating", "Country" };

    private List<Developer> developers;
    private List<Developer> oldDevelopers = new ArrayList<>();

    private static String oldSearch = "";

    public DevelopersTableModel( List<Developer> developers ) {
        this.developers = developers;
        this.oldDevelopers.addAll( developers );
        getSorted();
    }

    private void getSorted() {
        //region Ініціалізація сортування
        //Сортування по назві
        Comparator<Developer> shopComparator = ( o1, o2 ) -> o1.getBrand().compareToIgnoreCase( o2.getBrand() );
        this.developers.sort( shopComparator );
        this.oldDevelopers.sort( shopComparator );
        //endregion
    }

    public void search( String name ) {
        //region Пошук товару по назві
        //Якщо користувач видалив символ з поля пошуку, то пошук здійснюється по всіх елементах повторно
        this.developers = oldSearch.length() > name.length() ? oldDevelopers : this.developers;

        //Якщо @name порожнє, то поточним списком магазинів стає старий список всіх магазинів
        if ( name.equals( "" ) ) this.developers = oldDevelopers;
        else this.developers = this.developers.stream()
                .filter( s -> s.getBrand().toLowerCase()
                        .startsWith( name.toLowerCase() ) )
                .collect( Collectors.toList() );
        oldSearch = name;
        //endregion
        fireTableStructureChanged();
    }

    public void addShop( Developer developer ) {
        //region Додавання нового елемента
        developers.add( developer );
        oldDevelopers.add( developer );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void updateShop( Developer developer ) {
        //region Заміна старого елемента на новий
        UnaryOperator<Developer> shopUnaryOperator = s -> {
            if ( s.getId() == developer.getId() ) return developer;
            else return s;
        };
        developers.replaceAll( shopUnaryOperator );
        oldDevelopers.replaceAll( shopUnaryOperator );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow( int rowIndex ) {
        //region Видалення елемента
        oldDevelopers.remove( developers.get( rowIndex ) );
        developers.remove( rowIndex );
        //endregion
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public Developer getShopFromRow( int rowIndex ) {
        return developers.get( rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, developers.size() );
    }

    @Override
    public int getRowCount() {
        return developers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        //region Витягування значення з комірки
        Developer d = developers.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( d.getId() );
            case 1:
                return d.getBrand();
            case 2:
                return d.getOffice();
            case 3:
                return d.getCity();
            case 4:
                return d.getAddress();
            case 5:
                return d.getPhone();
            case 6:
                return d.getSite();
            case 7:
                return d.getFounder();
            case 8:
                return d.getRating();
            case 9:
                return d.getCountry();
            default:
                return null;
        }
        //endregion
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
