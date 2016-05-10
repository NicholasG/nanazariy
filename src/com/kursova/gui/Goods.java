package com.kursova.gui;

import com.kursova.dao.ModelDAO;
import com.kursova.domain.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.MessageFormat;

public class Goods extends JDialog {

    private JScrollPane scrollPane1;
    public static JTable table;
    private JToolBar toolBar1;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonEdit;
    private JButton buttonPrint;
    private JLabel label1;
    private JTextField textFieldSearch;

    public static int shopId;
    public static GoodsTableModel tableModel;

    public Goods( Frame owner ) {
        super( owner );
        initComponents();
        tableModel = GoodsTableModel.getGoodsTableModel();
        initializeTable();
    }

    public Goods( Frame owner, int shopId ) {
        super( owner );
        initComponents();
        this.shopId = shopId;
        tableModel = GoodsTableModel.getGoodsTableModel( shopId );
        initializeTable();
    }

    private void initializeTable() {
        table = new JTable( tableModel );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scrollPane1.setViewportView( table );
    }

    private void buttonAddActionPerformed( ActionEvent e ) {
        AddNewGood newGood = new AddNewGood( this );
        newGood.setVisible( true );
    }

    private void buttonEditActionPerformed( ActionEvent e ) {
        try {
            openEditForm();
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void openEditForm() throws SQLException {
        int selectedRow = table.getSelectedRow();
        int id = tableModel.getGoodFromRow( selectedRow ).getId();
        Model model = new ModelDAO().findById( id );
        AddNewGood newGood = new AddNewGood( this, model );
        newGood.setVisible( true );
    }

    private void buttonDeleteActionPerformed( ActionEvent e ) {
        int confirmDialog = JOptionPane.showConfirmDialog( this,
                "Ви дійсно бажаєте видалити товар?", "Увага!",
                JOptionPane.YES_NO_OPTION );
        if ( confirmDialog == JOptionPane.YES_OPTION ) try {
            int selectedRow = table.getSelectedRow();
            int goodId = tableModel.getGoodFromRow( selectedRow ).getId();
            new ModelDAO().deleteGoodFromShop( shopId, goodId );
            tableModel.removeRow( selectedRow );
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void buttonPrintActionPerformed( ActionEvent e ) {
        MessageFormat headerFormat = new MessageFormat( "Сторінка {0}" );
        MessageFormat footerFormat = new MessageFormat( "- {0} -" );
        try {
            table.print( JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat );
        } catch ( PrinterException e1 ) {
            e1.printStackTrace();
        }
    }

    private void textFieldSearchKeyTyped( KeyEvent e ) {
        if ( e.getKeyChar() != KeyEvent.VK_BACK_SPACE ) {
            String name = textFieldSearch.getText() + e.getKeyChar();
            tableModel.search( name );
        } else {
            String name = textFieldSearch.getText();
            tableModel.search( name );
        }
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table = new JTable();
        toolBar1 = new JToolBar();
        buttonAdd = new JButton();
        buttonDelete = new JButton();
        buttonEdit = new JButton();
        buttonPrint = new JButton();
        label1 = new JLabel();
        textFieldSearch = new JTextField();

        //======== this ========
        setBackground( Color.white );
        setTitle( "\u0422\u041e\u0412\u0410\u0420\u0418" );
        setResizable( false );
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        Container contentPane = getContentPane();
        contentPane.setLayout( null );

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            table.setFont( new Font( "Times New Roman", Font.PLAIN, 12 ) );
            scrollPane1.setViewportView( table );
        }
        contentPane.add( scrollPane1 );
        scrollPane1.setBounds( 0, 40, 950, 405 );

        //======== toolBar1 ========
        {
            toolBar1.setFloatable( false );
            toolBar1.setBackground( Color.white );
            toolBar1.setBorderPainted( false );

            //---- buttonAdd ----
            buttonAdd.setIcon( new ImageIcon( getClass().getResource( "/com/kursova/gui/icons/add.png" ) ) );
            buttonAdd.setToolTipText( "\u0414\u043e\u0434\u0430\u0442\u0438" );
            buttonAdd.setBorderPainted( false );
            buttonAdd.setBackground( Color.white );
            buttonAdd.addActionListener( e -> buttonAddActionPerformed( e ) );
            toolBar1.add( buttonAdd );

            //---- buttonDelete ----
            buttonDelete.setIcon( new ImageIcon( getClass().getResource( "/com/kursova/gui/icons/delete.png" ) ) );
            buttonDelete.setToolTipText( "\u0412\u0438\u0434\u0430\u043b\u0438\u0442\u0438" );
            buttonDelete.setBorderPainted( false );
            buttonDelete.setBackground( Color.white );
            buttonDelete.addActionListener( e -> buttonDeleteActionPerformed( e ) );
            toolBar1.add( buttonDelete );

            //---- buttonEdit ----
            buttonEdit.setIcon( new ImageIcon( getClass().getResource( "/com/kursova/gui/icons/edit.png" ) ) );
            buttonEdit.setToolTipText( "\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438" );
            buttonEdit.setBorderPainted( false );
            buttonEdit.setBackground( Color.white );
            buttonEdit.addActionListener( e -> buttonEditActionPerformed( e ) );
            toolBar1.add( buttonEdit );

            //---- buttonPrint ----
            buttonPrint.setIcon( new ImageIcon( getClass().getResource( "/com/kursova/gui/icons/print.png" ) ) );
            buttonPrint.setToolTipText( "\u0414\u0440\u0443\u043a" );
            buttonPrint.setBorderPainted( false );
            buttonPrint.setBackground( Color.white );
            buttonPrint.addActionListener( e -> buttonPrintActionPerformed( e ) );
            toolBar1.add( buttonPrint );

            //---- label1 ----
            label1.setText( "\u041f\u043e\u0448\u0443\u043a \u043f\u043e \u043d\u0430\u0437\u0432\u0456: " );
            label1.setHorizontalAlignment( SwingConstants.RIGHT );
            label1.setPreferredSize( new Dimension( 630, 30 ) );
            label1.setMaximumSize( new Dimension( 630, 30 ) );
            toolBar1.add( label1 );

            //---- textFieldSearch ----
            textFieldSearch.setMaximumSize( new Dimension( 125, 30 ) );
            textFieldSearch.setPreferredSize( new Dimension( 125, 30 ) );
            textFieldSearch.addKeyListener( new KeyAdapter() {
                @Override
                public void keyTyped( KeyEvent e ) {
                    textFieldSearchKeyTyped( e );
                }
            } );
            toolBar1.add( textFieldSearch );
        }
        contentPane.add( toolBar1 );
        toolBar1.setBounds( 0, 0, 950, toolBar1.getPreferredSize().height );

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for ( int i = 0; i < contentPane.getComponentCount(); i++ ) {
                Rectangle bounds = contentPane.getComponent( i ).getBounds();
                preferredSize.width = Math.max( bounds.x + bounds.width, preferredSize.width );
                preferredSize.height = Math.max( bounds.y + bounds.height, preferredSize.height );
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize( preferredSize );
            contentPane.setPreferredSize( preferredSize );
        }
        pack();
        setLocationRelativeTo( getOwner() );
    }

}
