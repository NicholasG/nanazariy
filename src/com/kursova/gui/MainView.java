package com.kursova.gui;

import com.kursova.dao.DeveloperDAO;
import com.kursova.domain.Developer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private DevelopersTableModel tableModel = getShopsTableModel();

    public MainView() {
        initComponents();
    }

    private void tableShopMouseClicked( MouseEvent e ) {
        if ( e.getClickCount() == 2 ) {
            int selectedRow = tableDevelopers.getSelectedRow();
            int shopId = tableModel.getDeveloperFromRow( selectedRow ).getId();
            Models models = new Models( this, shopId );
            models.setVisible( true );
        }
    }

    private void buttonAddTBActionPerformed( ActionEvent e ) {
        AddNewDeveloper newShop = new AddNewDeveloper( this );
        newShop.setVisible( true );
    }

    private void buttonEditTBActionPerformed( ActionEvent e ) {
        int selectedRow = tableDevelopers.getSelectedRow();
        Developer developer = tableModel.getDeveloperFromRow( selectedRow );
        AddNewDeveloper newShop = new AddNewDeveloper( this, developer );
        newShop.setVisible( true );
    }

    private void buttonDeleteTBActionPerformed( ActionEvent e ) {
        int confirmDialog = JOptionPane.showConfirmDialog( this,
                "Are you sure you want to delete a developer?", "Warning!",
                JOptionPane.YES_NO_OPTION );
        if ( confirmDialog == JOptionPane.YES_OPTION ) try {
            int selectedRow = tableDevelopers.getSelectedRow();
            Developer developer = tableModel.getDeveloperFromRow( selectedRow );
            new DeveloperDAO().deleteDeveloper( developer.getId() );
            tableModel.removeRow( selectedRow );
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void buttonPrintTBActionPerformed( ActionEvent e ) {
        MessageFormat headerFormat = new MessageFormat( "Сторінка {0}" );
        MessageFormat footerFormat = new MessageFormat( "- {0} -" );
        try {
            tableDevelopers.print( JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat );
        } catch ( PrinterException e1 ) {
            e1.printStackTrace();
        }
    }

    private void textFieldSearchKeyTyped( KeyEvent e ) {
        if ( e.getKeyChar() != KeyEvent.VK_BACK_SPACE ) {
            String country = textFieldSearch.getText() + e.getKeyChar();
            tableModel.search( country );
        } else {
            String country = textFieldSearch.getText();
            tableModel.search( country );
        }
    }

    private DevelopersTableModel getShopsTableModel() {
        try {
            DeveloperDAO dao = new DeveloperDAO();
            final List<Developer> developers = dao.findAll();
            return new DevelopersTableModel( developers );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( this, "Could not initialize a table: " + e.getMessage() );
        }
        return new DevelopersTableModel( new ArrayList<>() );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        scrollPane = new JScrollPane();
        tableDevelopers = new JTable(tableModel);
        toolBar = new JToolBar();
        buttonAddTB = new JButton();
        buttonDeleteTB = new JButton();
        buttonEditTB = new JButton();
        buttonPrintTB = new JButton();
        label1 = new JLabel();
        textFieldSearch = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEVELOPERS");
        setBackground(Color.white);
        Container contentPane = getContentPane();

        //======== scrollPane ========
        {
            scrollPane.setBackground(Color.white);

            //---- tableDevelopers ----
            tableDevelopers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableDevelopers.setRowHeight(20);
            tableDevelopers.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            tableDevelopers.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableShopMouseClicked(e);
                }
            });
            scrollPane.setViewportView(tableDevelopers);
        }

        //======== toolBar ========
        {
            toolBar.setFloatable(false);
            toolBar.setBackground(Color.white);
            toolBar.setAlignmentX(0.0F);
            toolBar.setBorderPainted(false);
            toolBar.setMaximumSize(new Dimension(1920, 41));
            toolBar.setPreferredSize(new Dimension(907, 41));

            //---- buttonAddTB ----
            buttonAddTB.setToolTipText("Add a new developer");
            buttonAddTB.setIcon(new ImageIcon(getClass().getResource("/com/kursova/gui/icons/add.png")));
            buttonAddTB.setAlignmentY(0.0F);
            buttonAddTB.setBorderPainted(false);
            buttonAddTB.setBackground(Color.white);
            buttonAddTB.addActionListener(e -> buttonAddTBActionPerformed(e));
            toolBar.add(buttonAddTB);

            //---- buttonDeleteTB ----
            buttonDeleteTB.setBorderPainted(false);
            buttonDeleteTB.setIcon(new ImageIcon(getClass().getResource("/com/kursova/gui/icons/delete.png")));
            buttonDeleteTB.setBackground(Color.white);
            buttonDeleteTB.setAlignmentY(0.0F);
            buttonDeleteTB.setToolTipText("Delete a developer");
            buttonDeleteTB.addActionListener(e -> buttonDeleteTBActionPerformed(e));
            toolBar.add(buttonDeleteTB);

            //---- buttonEditTB ----
            buttonEditTB.setIcon(new ImageIcon(getClass().getResource("/com/kursova/gui/icons/edit.png")));
            buttonEditTB.setAlignmentY(0.0F);
            buttonEditTB.setBorderPainted(false);
            buttonEditTB.setBackground(Color.white);
            buttonEditTB.setToolTipText("Edit a developer");
            buttonEditTB.addActionListener(e -> buttonEditTBActionPerformed(e));
            toolBar.add(buttonEditTB);

            //---- buttonPrintTB ----
            buttonPrintTB.setSelectedIcon(new ImageIcon(getClass().getResource("/com/kursova/gui/icons/print.png")));
            buttonPrintTB.setAlignmentY(0.0F);
            buttonPrintTB.setBorderPainted(false);
            buttonPrintTB.setToolTipText("Print");
            buttonPrintTB.setBackground(Color.white);
            buttonPrintTB.setIcon(new ImageIcon(getClass().getResource("/com/kursova/gui/icons/print.png")));
            buttonPrintTB.addActionListener(e -> buttonPrintTBActionPerformed(e));
            toolBar.add(buttonPrintTB);

            //---- label1 ----
            label1.setText("Find by country: ");
            label1.setPreferredSize(new Dimension(630, 30));
            label1.setMaximumSize(new Dimension(1920, 30));
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            toolBar.add(label1);

            //---- textFieldSearch ----
            textFieldSearch.setMaximumSize(new Dimension(125, 30));
            textFieldSearch.setPreferredSize(new Dimension(125, 30));
            textFieldSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textFieldSearchKeyTyped(e);
                }
            });
            toolBar.add(textFieldSearch);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(toolBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                .addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JScrollPane scrollPane;
    static JTable tableDevelopers;
    private JToolBar toolBar;
    private JButton buttonAddTB;
    private JButton buttonDeleteTB;
    private JButton buttonEditTB;
    private JButton buttonPrintTB;
    private JLabel label1;
    private JTextField textFieldSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
