package com.kursova.gui;

import com.kursova.dao.ShopDAO;
import com.kursova.domain.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddNewShop extends JDialog {

    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldPhone;
    private JTextField textFieldChief;
    private JTextField textFieldSite;
    private JTextField textFieldSchedule;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ShopDAO dao = new ShopDAO();

    private Shop shop;

    public AddNewShop( Frame owner ) {
        super( owner );
        initComponents();
    }

    public AddNewShop( Frame owner, Shop shop ) {
        super( owner );
        this.shop = shop;
        initComponents();
        initializeTextFields();
    }

    private void initializeTextFields() {
        textFieldName.setText( shop.getName() );
        textFieldAddress.setText( shop.getAddress() );
        textFieldPhone.setText( shop.getPhone() );
        textFieldChief.setText( shop.getChief() );
        textFieldSite.setText( shop.getSite() );
        textFieldSchedule.setText( shop.getSchedule() );
    }

    public AddNewShop( Dialog owner ) {
        super( owner );
        initComponents();
    }

    private void buttonCancelActionPerformed( ActionEvent e ) {
        this.dispose();
    }

    private void buttonSaveActionPerformed( ActionEvent e ) {
        if ( this.shop != null ) {
            getShop( this.shop );
            try {
                dao.updateShop( shop );
                ShopsTableModel model = ( ShopsTableModel ) MainView.tableShop.getModel();
                model.updateShop( shop );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        } else {
            Shop shop = new Shop();
            getShop( shop );
            try {
                int newId = dao.insertShop( shop );
                shop.setId( newId );
                ShopsTableModel model = ( ShopsTableModel ) MainView.tableShop.getModel();
                model.addShop( shop );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    private void getShop( Shop shop ) {
        shop.setName( textFieldName.getText() );
        shop.setAddress( textFieldAddress.getText() );
        shop.setPhone( textFieldPhone.getText() );
        shop.setChief( textFieldChief.getText() );
        shop.setSite( textFieldSite.getText() );
        shop.setSchedule( textFieldSchedule.getText() );
    }

    private void initComponents() {
        textFieldName = new JTextField();
        textFieldAddress = new JTextField();
        textFieldPhone = new JTextField();
        textFieldChief = new JTextField();
        textFieldSite = new JTextField();
        textFieldSchedule = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        buttonSave = new JButton();
        buttonCancel = new JButton();

        //======== this ========
        setTitle( "ADD/EDIT SHOP" );
        Container contentPane = getContentPane();
        contentPane.setLayout( null );
        contentPane.add( textFieldName );
        textFieldName.setBounds( 85, 10, 135, textFieldName.getPreferredSize().height );
        contentPane.add( textFieldAddress );
        textFieldAddress.setBounds( 85, 35, 135, 24 );
        contentPane.add( textFieldPhone );
        textFieldPhone.setBounds( 85, 60, 135, 24 );
        contentPane.add( textFieldChief );
        textFieldChief.setBounds( 345, 10, 135, 24 );
        contentPane.add( textFieldSite );
        textFieldSite.setBounds( 345, 35, 135, 24 );
        contentPane.add( textFieldSchedule );
        textFieldSchedule.setBounds( 345, 60, 135, 24 );

        //---- label1 ----
        label1.setText( "\u041d\u0430\u0437\u0432\u0430:" );
        contentPane.add( label1 );
        label1.setBounds( 15, 10, 65, 25 );

        //---- label2 ----
        label2.setText( "\u0410\u0434\u0440\u0435\u0441\u0430:" );
        contentPane.add( label2 );
        label2.setBounds( 15, 35, 65, 25 );

        //---- label3 ----
        label3.setText( "\u0422\u0435\u043b\u0435\u0444\u043e\u043d:" );
        contentPane.add( label3 );
        label3.setBounds( 15, 60, 65, 25 );

        //---- label4 ----
        label4.setText( "\u0414\u0438\u0440\u0435\u043a\u0442\u043e\u0440:" );
        contentPane.add( label4 );
        label4.setBounds( 275, 10, 65, 25 );

        //---- label5 ----
        label5.setText( "\u0412\u0435\u0431\u0441\u0430\u0439\u0442:" );
        contentPane.add( label5 );
        label5.setBounds( 275, 35, 65, 25 );

        //---- label6 ----
        label6.setText( "\u0420\u043e\u0437\u043a\u043b\u0430\u0434:" );
        contentPane.add( label6 );
        label6.setBounds( 275, 60, 65, 25 );

        //---- buttonSave ----
        buttonSave.setText( "\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438" );
        buttonSave.addActionListener( e -> buttonSaveActionPerformed( e ) );
        contentPane.add( buttonSave );
        buttonSave.setBounds( 10, 110, 100, 35 );

        //---- buttonCancel ----
        buttonCancel.setText( "\u0412\u0456\u0434\u043c\u0456\u043d\u0438\u0442\u0438" );
        buttonCancel.addActionListener( e -> buttonCancelActionPerformed( e ) );
        contentPane.add( buttonCancel );
        buttonCancel.setBounds( 385, 110, 100, 35 );

        contentPane.setPreferredSize( new Dimension( 500, 170 ) );
        pack();
        setLocationRelativeTo( getOwner() );
    }

}
