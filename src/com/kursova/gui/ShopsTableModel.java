package com.kursova.gui;

import com.kursova.domain.Shop;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by NicholasG on 10.04.2016.
 */
public class ShopsTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Назва", "Адреса",
            "Телефон", "Директор", "Вебсайт", "Розклад" };

    private List<Shop> shops;
    private List<Shop> oldShops = new ArrayList<>();

    private static String oldSearch = "";

    public ShopsTableModel( List<Shop> shops ) {
        this.shops = shops;
        this.oldShops.addAll( shops );
        getSorted();

    }

    private void getSorted() {
        //region Ініціалізація сортування
        //Сортування по назві
        Comparator<Shop> shopComparator = ( o1, o2 ) -> o1.getName().compareToIgnoreCase( o2.getName() );
        this.shops.sort( shopComparator );
        this.oldShops.sort( shopComparator );
        //endregion
    }

    public void search( String name ) {
        //region Пошук товару по назві
        //Якщо користувач видалив символ з поля пошуку, то пошук здійснюється по всіх елементах повторно
        this.shops = oldSearch.length() > name.length() ? oldShops : this.shops;

        //Якщо @name порожнє, то поточним списком магазинів стає старий список всіх магазинів
        if ( name.equals( "" ) ) this.shops = oldShops;
        else this.shops = this.shops.stream()
                .filter( s -> s.getName().toLowerCase()
                        .startsWith( name.toLowerCase() ) )
                .collect( Collectors.toList() );
        oldSearch = name;
        //endregion
        fireTableStructureChanged();
    }

    public void addShop( Shop shop ) {
        //region Додавання нового елемента
        shops.add( shop );
        oldShops.add( shop );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void updateShop( Shop shop ) {
        //region Заміна старого елемента на новий
        UnaryOperator<Shop> shopUnaryOperator = s -> {
            if ( s.getId() == shop.getId() ) return shop;
            else return s;
        };
        shops.replaceAll( shopUnaryOperator );
        oldShops.replaceAll( shopUnaryOperator );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow( int rowIndex ) {
        //region Видалення елемента
        oldShops.remove( shops.get( rowIndex ) );
        shops.remove( rowIndex );
        //endregion
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public Shop getShopFromRow( int rowIndex ) {
        return shops.get( rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, shops.size() );
    }

    @Override
    public int getRowCount() {
        return shops.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        //region Витягування значення з комірки
        Shop s = shops.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( s.getId() );
            case 1:
                return s.getName();
            case 2:
                return s.getAddress();
            case 3:
                return s.getPhone();
            case 4:
                return s.getChief();
            case 5:
                return s.getSite();
            case 6:
                return s.getSchedule();
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
