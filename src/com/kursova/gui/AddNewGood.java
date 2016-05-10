package com.kursova.gui;

import com.kursova.dao.GoodDAO;
import com.kursova.domain.Good;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddNewGood extends JDialog {

    private JTextField textFieldName;
    private JTextField textFieldType;
    private JTextField textFieldArticle;
    private JTextField textFieldPrice;
    private JTextField textFieldScale;
    private JTextField textFieldAmount;
    private JTextField textFieldColor;
    private JTextField textFieldManufacturer;
    private JScrollPane scrollPane;
    private JTextArea textAreaSpec;
    private JLabel labelName;
    private JLabel labelType;
    private JLabel labelArticle;
    private JLabel labelPrice;
    private JLabel labelScale;
    private JLabel labelAmount;
    private JLabel labelColor;
    private JLabel labelManufacturer;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JLabel labelSpec;

    private Good good = null;
    private GoodDAO dao = new GoodDAO();

    public AddNewGood( Frame owner ) {
        super( owner );
        initComponents();
    }

    public AddNewGood( Dialog owner ) {
        super( owner );
        initComponents();
    }

    public AddNewGood( Dialog owner, Good good ) {
        super( owner );
        this.good = good;
        initComponents();
        initializeTextFields( this.good );
    }

    private void initializeTextFields( Good good ) {
        textFieldName.setText( good.getName() );
        textFieldType.setText( good.getType() );
        textFieldArticle.setText( good.getArticle() );
        textFieldPrice.setText( String.valueOf( good.getPrice() ) );
        textFieldScale.setText( good.getScale() );
        textFieldAmount.setText( String.valueOf( good.getAmount() ) );
        textFieldColor.setText( good.getColor() );
        textFieldManufacturer.setText( good.getManufacturer() );
        textAreaSpec.setText( good.getSpecifications() );
    }

    private void buttonCancelActionPerformed( ActionEvent e ) {
        this.dispose();
    }

    private void buttonSaveActionPerformed( ActionEvent e ) {
        if ( this.good != null ) {
            getGood( this.good );
            try {
                dao.updateGood( good );
                GoodsTableModel model = ( GoodsTableModel ) Goods.table.getModel();
                model.updateGood( good );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        } else {
            Good good = new Good();
            getGood( good );
            try {
                int newId = dao.insertGood( good );
                good.setId( newId );
                dao.insertGoodIntoShop( good.getId(), Goods.shopId );
                GoodsTableModel model = ( GoodsTableModel ) Goods.table.getModel();
                model.addGood( good );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    private void getGood( Good good ) {
        good.setName( textFieldName.getText() );
        good.setType( textFieldType.getText() );
        good.setArticle( textFieldArticle.getText() );
        good.setPrice( Float.parseFloat( textFieldPrice.getText() ) );
        good.setScale( textFieldScale.getText() );
        good.setAmount( Integer.parseInt( textFieldAmount.getText() ) );
        good.setColor( textFieldColor.getText() );
        good.setManufacturer( textFieldManufacturer.getText() );
        good.setSpecifications( textAreaSpec.getText() );
    }

    private void initComponents() {
        textFieldName = new JTextField();
        textFieldType = new JTextField();
        textFieldArticle = new JTextField();
        textFieldPrice = new JTextField();
        textFieldScale = new JTextField();
        textFieldAmount = new JTextField();
        textFieldColor = new JTextField();
        textFieldManufacturer = new JTextField();
        scrollPane = new JScrollPane();
        textAreaSpec = new JTextArea();
        labelName = new JLabel();
        labelType = new JLabel();
        labelArticle = new JLabel();
        labelPrice = new JLabel();
        labelScale = new JLabel();
        labelAmount = new JLabel();
        labelColor = new JLabel();
        labelManufacturer = new JLabel();
        buttonSave = new JButton();
        buttonCancel = new JButton();
        labelSpec = new JLabel();

        //======== this ========
        setTitle( "ADD/EDIT GOOD" );
        Container contentPane = getContentPane();
        contentPane.setLayout( null );
        contentPane.add( textFieldName );
        textFieldName.setBounds( 70, 10, 135, textFieldName.getPreferredSize().height );
        contentPane.add( textFieldType );
        textFieldType.setBounds( 70, 35, 135, textFieldType.getPreferredSize().height );
        contentPane.add( textFieldArticle );
        textFieldArticle.setBounds( 70, 60, 135, textFieldArticle.getPreferredSize().height );
        contentPane.add( textFieldPrice );
        textFieldPrice.setBounds( 280, 10, 135, textFieldPrice.getPreferredSize().height );
        contentPane.add( textFieldScale );
        textFieldScale.setBounds( 280, 35, 135, textFieldScale.getPreferredSize().height );
        contentPane.add( textFieldAmount );
        textFieldAmount.setBounds( 280, 60, 135, textFieldAmount.getPreferredSize().height );
        contentPane.add( textFieldColor );
        textFieldColor.setBounds( 515, 10, 135, textFieldColor.getPreferredSize().height );
        contentPane.add( textFieldManufacturer );
        textFieldManufacturer.setBounds( 515, 35, 135, textFieldManufacturer.getPreferredSize().height );

        //======== scrollPane ========
        {
            scrollPane.setViewportView( textAreaSpec );
        }
        contentPane.add( scrollPane );
        scrollPane.setBounds( 10, 120, 640, 110 );

        //---- labelName ----
        labelName.setText( "Name:" );
        contentPane.add( labelName );
        labelName.setBounds( 10, 10, 55, 20 );

        //---- labelType ----
        labelType.setText( "Type:" );
        contentPane.add( labelType );
        labelType.setBounds( 10, 35, 55, 20 );

        //---- labelArticle ----
        labelArticle.setText( "Article:" );
        contentPane.add( labelArticle );
        labelArticle.setBounds( 10, 60, 55, 20 );

        //---- labelPrice ----
        labelPrice.setText( "Price:" );
        contentPane.add( labelPrice );
        labelPrice.setBounds( 220, 10, 55, 20 );

        //---- labelScale ----
        labelScale.setText( "Scale:" );
        contentPane.add( labelScale );
        labelScale.setBounds( 220, 35, 55, 20 );

        //---- labelAmount ----
        labelAmount.setText( "Amount:" );
        contentPane.add( labelAmount );
        labelAmount.setBounds( 220, 60, 55, 20 );

        //---- labelColor ----
        labelColor.setText( "Color:" );
        contentPane.add( labelColor );
        labelColor.setBounds( 430, 10, 55, 20 );

        //---- labelManufacturer ----
        labelManufacturer.setText( "Manufacturer:" );
        contentPane.add( labelManufacturer );
        labelManufacturer.setBounds( 430, 35, 80, 20 );

        //---- buttonSave ----
        buttonSave.setText( "Save" );
        buttonSave.addActionListener( e -> buttonSaveActionPerformed( e ) );
        contentPane.add( buttonSave );
        buttonSave.setBounds( 10, 245, 100, 33 );

        //---- buttonCancel ----
        buttonCancel.setText( "Cancel" );
        buttonCancel.addActionListener( e -> buttonCancelActionPerformed( e ) );
        contentPane.add( buttonCancel );
        buttonCancel.setBounds( 550, 245, 100, 33 );

        //---- labelSpec ----
        labelSpec.setText( "Specifications:" );
        contentPane.add( labelSpec );
        labelSpec.setBounds( 10, 95, 95, 19 );

        contentPane.setPreferredSize( new Dimension( 660, 325 ) );
        pack();
        setLocationRelativeTo( getOwner() );
    }

}
