package com.kursova.gui;

import com.kursova.dao.GoodDAO;
import com.kursova.domain.Good;

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

    private static final GoodDAO goodDAO = new GoodDAO();

    private final String[] columns = { "ID", "Назва", "Тип",
            "Виробник", "Артикул", "Ціна",
            "Од. вимір.", "Кількість", "Колір", "Опис" };

    private List<Good> goods;
    private List<Good> oldGoods = new ArrayList<>();

    private static String oldSearch = "";

    public GoodsTableModel( List<Good> goods ) {
        this.goods = goods;
        this.oldGoods.addAll( goods );
        getSorted();
    }

    private void getSorted() {
        //region Ініціалізація сортування
        //Сортування по назві
        Comparator<Good> goodComparator = ( o1, o2 ) -> o1.getName().compareToIgnoreCase( o2.getName() );
        this.goods.sort( goodComparator );
        this.oldGoods.sort( goodComparator );
        //endregion
    }

    public static GoodsTableModel getGoodsTableModel( int shopId ) {
        try {
            java.util.List<Good> goods = goodDAO.findAllGoodsByShopId( shopId );
            return new GoodsTableModel( goods );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Не вдалося ініціалізувати таблицю товарів: " + e.getMessage() );
        }
        return new GoodsTableModel( new ArrayList<>() );
    }

    public static GoodsTableModel getGoodsTableModel() {
        try {
            final java.util.List<Good> goods = goodDAO.findAll();
            return new GoodsTableModel( goods );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( null, "Не вдалося ініціалізувати таблицю товарів: " + e.getMessage() );
        }
        return new GoodsTableModel( new ArrayList<>() );
    }

    public void search( String name ) {
        //region Пошук товару по назві
        //Якщо користувач видалив символ з поля пошуку, то пошук здійснюється по всіх елементах повторно
        goods = oldSearch.length() > name.length() ? oldGoods : goods;
        //Якщо @name порожнє, то поточним списком товарів стає старий список всіх товарів
        if ( name.equals( "" ) ) goods = oldGoods;
        else goods = goods.stream()
                .filter( g -> g.getName().toLowerCase()
                        .startsWith( name.toLowerCase() ) )
                .collect( Collectors.toList() );
        oldSearch = name;
        //endregion
        fireTableStructureChanged();
    }

    public void addGood( Good good ) {
        //region Додавання нового елемента
        goods.add( good );
        oldGoods.add( good );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void updateGood( Good good ) {
        //region Заміна старого елемента на новий
        UnaryOperator<Good> goodUnaryOperator = g -> {
            if ( g.getId() == good.getId() ) return good;
            else return g;
        };
        goods.replaceAll( goodUnaryOperator );
        oldGoods.replaceAll( goodUnaryOperator );
        //endregion
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow( int rowIndex ) {
        oldGoods.remove( goods.get( rowIndex ) );
        goods.remove( rowIndex );
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public Good getGoodFromRow( int rowIndex ) {
        return goods.get( rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, goods.size() );
    }

    public List<Good> getGoods() {
        return goods;
    }

    @Override
    public int getRowCount() {
        return goods.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Good g = goods.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( g.getId() );
            case 1:
                return g.getName();
            case 2:
                return g.getType();
            case 3:
                return g.getManufacturer();
            case 4:
                return g.getArticle();
            case 5:
                return String.valueOf( g.getPrice() );
            case 6:
                return g.getScale();
            case 7:
                return String.valueOf( g.getAmount() );
            case 8:
                return g.getColor();
            case 9:
                return g.getSpecifications();
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
