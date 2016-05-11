package com.kursova.gui;

import com.kursova.dao.DeveloperDAO;
import com.kursova.domain.Developer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddNewDeveloper extends JDialog {

    private DeveloperDAO dao = new DeveloperDAO();

    private Developer developer;

    public AddNewDeveloper( Frame owner ) {
        super( owner );
        initComponents();
    }

    public AddNewDeveloper( Frame owner, Developer developer ) {
        super( owner );
        this.developer = developer;
        initComponents();
        initializeTextFields();
    }

    private void initializeTextFields() {
        textFieldBrand.setText( developer.getBrand() );
        textFieldOffice.setText( developer.getOffice() );
        textFieldCountry.setText( developer.getCountry() );
        textFieldCity.setText( developer.getCity() );
        textFieldAddress.setText( developer.getAddress() );
        textFieldPhone.setText( developer.getPhone() );
        textFieldWebsite.setText( developer.getSite() );
        textFieldFounder.setText( developer.getFounder() );
        textFieldRating.setText( developer.getRating() );
    }

    public AddNewDeveloper( Dialog owner ) {
        super( owner );
        initComponents();
    }

    private void buttonCancelActionPerformed( ActionEvent e ) {
        this.dispose();
    }

    private void buttonSaveActionPerformed( ActionEvent e ) {
        if ( this.developer != null ) {
            getDeveloper( this.developer );
            try {
                dao.updateDeveloper( developer );
                DevelopersTableModel model = ( DevelopersTableModel ) MainView.tableDevelopers.getModel();
                model.updateDeveloper( developer );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        } else {
            Developer developer = new Developer();
            getDeveloper( developer );
            try {
                int newId = dao.insertDeveloper( developer );
                developer.setId( newId );
                DevelopersTableModel tableModel = ( DevelopersTableModel ) MainView.tableDevelopers.getModel();
                tableModel.addDeveloper( developer );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    private void getDeveloper( Developer developer ) {
        developer.setBrand( textFieldBrand.getText() );
        developer.setOffice( textFieldOffice.getText() );
        developer.setCountry( textFieldCountry.getText() );
        developer.setCity( textFieldCity.getText() );
        developer.setAddress( textFieldAddress.getText() );
        developer.setPhone( textFieldPhone.getText() );
        developer.setSite( textFieldWebsite.getText() );
        developer.setFounder( textFieldFounder.getText() );
        developer.setRating( textFieldRating.getText() );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        textFieldBrand = new JTextField();
        textFieldOffice = new JTextField();
        textFieldCountry = new JTextField();
        textFieldCity = new JTextField();
        textFieldAddress = new JTextField();
        textFieldPhone = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        buttonSave = new JButton();
        buttonCancel = new JButton();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        textFieldWebsite = new JTextField();
        textFieldFounder = new JTextField();
        textFieldRating = new JTextField();

        //======== this ========
        setTitle("ADD/EDIT DEVELOPER");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textFieldBrand);
        textFieldBrand.setBounds(85, 10, 135, textFieldBrand.getPreferredSize().height);
        contentPane.add(textFieldOffice);
        textFieldOffice.setBounds(345, 10, 135, 24);
        contentPane.add(textFieldCountry);
        textFieldCountry.setBounds(345, 35, 135, 24);
        contentPane.add(textFieldCity);
        textFieldCity.setBounds(345, 60, 135, 24);
        contentPane.add(textFieldAddress);
        textFieldAddress.setBounds(345, 85, 135, 24);
        contentPane.add(textFieldPhone);
        textFieldPhone.setBounds(345, 110, 135, 24);

        //---- label1 ----
        label1.setText("Brand:");
        contentPane.add(label1);
        label1.setBounds(15, 10, 65, 25);

        //---- label2 ----
        label2.setText("Office:");
        contentPane.add(label2);
        label2.setBounds(275, 10, 65, 25);

        //---- label3 ----
        label3.setText("Country:");
        contentPane.add(label3);
        label3.setBounds(275, 35, 65, 25);

        //---- label4 ----
        label4.setText("City:");
        contentPane.add(label4);
        label4.setBounds(275, 60, 65, 25);

        //---- label5 ----
        label5.setText("Address:");
        contentPane.add(label5);
        label5.setBounds(275, 85, 65, 25);

        //---- label6 ----
        label6.setText("Phone:");
        contentPane.add(label6);
        label6.setBounds(275, 110, 65, 25);

        //---- buttonSave ----
        buttonSave.setText("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
        buttonSave.addActionListener(e -> buttonSaveActionPerformed(e));
        contentPane.add(buttonSave);
        buttonSave.setBounds(5, 145, 100, 35);

        //---- buttonCancel ----
        buttonCancel.setText("\u0412\u0456\u0434\u043c\u0456\u043d\u0438\u0442\u0438");
        buttonCancel.addActionListener(e -> buttonCancelActionPerformed(e));
        contentPane.add(buttonCancel);
        buttonCancel.setBounds(380, 145, 100, 35);

        //---- label7 ----
        label7.setText("Website:");
        contentPane.add(label7);
        label7.setBounds(15, 35, 65, 25);

        //---- label8 ----
        label8.setText("Founder:");
        contentPane.add(label8);
        label8.setBounds(15, 60, 65, 25);

        //---- label9 ----
        label9.setText("Rating:");
        contentPane.add(label9);
        label9.setBounds(15, 85, 65, 25);
        contentPane.add(textFieldWebsite);
        textFieldWebsite.setBounds(85, 35, 135, 24);
        contentPane.add(textFieldFounder);
        textFieldFounder.setBounds(85, 60, 135, 24);
        contentPane.add(textFieldRating);
        textFieldRating.setBounds(85, 85, 135, 24);

        contentPane.setPreferredSize(new Dimension(505, 225));
        setSize(505, 225);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JTextField textFieldBrand;
    private JTextField textFieldOffice;
    private JTextField textFieldCountry;
    private JTextField textFieldCity;
    private JTextField textFieldAddress;
    private JTextField textFieldPhone;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField textFieldWebsite;
    private JTextField textFieldFounder;
    private JTextField textFieldRating;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
