package com.kursova.gui;

import com.kursova.dao.CharDAO;
import com.kursova.dao.ModelDAO;
import com.kursova.domain.Char;
import com.kursova.domain.Developer;
import com.kursova.domain.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;

public class AddNewModel extends JDialog {

    private Model model = null;
    private ModelDAO dao = new ModelDAO();

    public AddNewModel( Frame owner ) {
        super( owner );
        initComponents();
    }

    public AddNewModel( Dialog owner ) {
        super( owner );
        initComponents();
    }

    public AddNewModel( Dialog owner, Model model ) {
        super( owner );
        this.model = model;
        initComponents();
        initializeTextFields( this.model );
    }

    private void initializeTextFields( Model model ) {
        textFieldModel.setText( model.getModel() );
        textFieldType.setText( model.getType() );
        textFieldDate.setText( model.getDate().toString() );
        textFieldArchitecture.setText( model.getArchitecture() );
        textFieldColor.setText( model.getColor() );
        textFieldSerial.setText( model.getSerial() );

        try {
            Char aChar = new CharDAO().findById( model.getCharId() );
            textFieldDisplay.setText( aChar.getDisplay() );
            textFieldTypeOfDisplay.setText( aChar.getTypeOfDisplay() );
            textFieldProcessor.setText( aChar.getProcessor() );
            textFieldFrequency.setText( aChar.getFrequency() );
            textFieldRam.setText( aChar.getRam() );
            textFieldTypeOfRam.setText( aChar.getTypeOfRam() );
            textFieldHDD.setText( aChar.getHardDisk() );
            textFieldVideo.setText( aChar.getVideoCard() );
            textFieldWebCam.setText( aChar.getWebCam() );
            textFieldOS.setText( aChar.getOs() );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    private void buttonCancelActionPerformed( ActionEvent e ) {
        this.dispose();
    }

    private void buttonSaveActionPerformed( ActionEvent e ) {
        if ( this.model != null ) {
            getModel( this.model );
            Char aChar = getChar();
            aChar.setId( model.getCharId() );
            try {
                new CharDAO().updateChar( aChar );
                dao.updateModel( model );
                ModelsTableModel model = ( ModelsTableModel ) Models.table.getModel();
                model.updateModel( this.model );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        } else {
            Model model1 = new Model();
            Char aChar = getChar();
            DevelopersTableModel mainTable = ( DevelopersTableModel ) MainView.tableDevelopers.getModel();
            Developer developer = mainTable.getDeveloperFromRow( MainView.tableDevelopers.getSelectedRow() );
            getModel( model1 );
            try {
                int charId = new CharDAO().insertChar( aChar );
                model1.setCharId( charId );
                model1.setDeveloperId( developer.getId() );
                int newId = dao.insertModel( model1 );
                ModelsTableModel tableModel = ( ModelsTableModel ) Models.table.getModel();
                model1.setId( newId );
                tableModel.addModel( model1 );
                this.dispose();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    private void getModel( Model model ) {
        model.setModel( textFieldModel.getText() );
        model.setSerial( textFieldSerial.getText() );
        model.setDate( Date.valueOf( textFieldDate.getText() ) );
        model.setColor( textFieldColor.getText() );
        model.setType( textFieldType.getText() );
        model.setArchitecture( textFieldArchitecture.getText() );
    }

    private Char getChar() {
        return new Char(
                textFieldDisplay.getText(),
                textFieldTypeOfDisplay.getText(),
                textFieldProcessor.getText(),
                textFieldFrequency.getText(),
                textFieldRam.getText(),
                textFieldTypeOfRam.getText(),
                textFieldHDD.getText(),
                textFieldVideo.getText(),
                textFieldWebCam.getText(),
                textFieldOS.getText()
        );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        textFieldModel = new JTextField();
        textFieldType = new JTextField();
        textFieldDate = new JTextField();
        textFieldArchitecture = new JTextField();
        textFieldColor = new JTextField();
        labelModel = new JLabel();
        labelType = new JLabel();
        labelDate = new JLabel();
        labelArchitecture = new JLabel();
        labelColor = new JLabel();
        buttonSave = new JButton();
        buttonCancel = new JButton();
        label1 = new JLabel();
        separator1 = new JSeparator();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        textFieldDisplay = new JTextField();
        textFieldTypeOfDisplay = new JTextField();
        textFieldProcessor = new JTextField();
        textFieldFrequency = new JTextField();
        textFieldRam = new JTextField();
        textFieldTypeOfRam = new JTextField();
        textFieldHDD = new JTextField();
        textFieldVideo = new JTextField();
        textFieldWebCam = new JTextField();
        textFieldOS = new JTextField();
        textFieldSerial = new JTextField();

        //======== this ========
        setTitle("ADD/EDIT MODEL");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textFieldModel);
        textFieldModel.setBounds(100, 15, 135, textFieldModel.getPreferredSize().height);
        contentPane.add(textFieldType);
        textFieldType.setBounds(100, 40, 135, textFieldType.getPreferredSize().height);
        contentPane.add(textFieldDate);
        textFieldDate.setBounds(100, 65, 135, textFieldDate.getPreferredSize().height);
        contentPane.add(textFieldArchitecture);
        textFieldArchitecture.setBounds(100, 90, 135, textFieldArchitecture.getPreferredSize().height);
        contentPane.add(textFieldColor);
        textFieldColor.setBounds(100, 115, 135, textFieldColor.getPreferredSize().height);

        //---- labelModel ----
        labelModel.setText("Model:");
        contentPane.add(labelModel);
        labelModel.setBounds(10, 15, 85, 20);

        //---- labelType ----
        labelType.setText("Type:");
        contentPane.add(labelType);
        labelType.setBounds(10, 40, 85, 20);

        //---- labelDate ----
        labelDate.setText("Date:");
        contentPane.add(labelDate);
        labelDate.setBounds(10, 65, 85, 20);

        //---- labelArchitecture ----
        labelArchitecture.setText("Architecture:");
        contentPane.add(labelArchitecture);
        labelArchitecture.setBounds(10, 90, 85, 20);

        //---- labelColor ----
        labelColor.setText("Color:");
        contentPane.add(labelColor);
        labelColor.setBounds(10, 115, 85, 20);

        //---- buttonSave ----
        buttonSave.setText("Save");
        buttonSave.addActionListener(e -> buttonSaveActionPerformed(e));
        contentPane.add(buttonSave);
        buttonSave.setBounds(5, 190, 100, 33);

        //---- buttonCancel ----
        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(e -> buttonCancelActionPerformed(e));
        contentPane.add(buttonCancel);
        buttonCancel.setBounds(625, 190, 100, 33);

        //---- label1 ----
        label1.setText("Serial:");
        contentPane.add(label1);
        label1.setBounds(10, 140, 85, 21);

        //---- separator1 ----
        separator1.setOrientation(SwingConstants.VERTICAL);
        contentPane.add(separator1);
        separator1.setBounds(245, 10, 5, 165);

        //---- label2 ----
        label2.setText("Display:");
        contentPane.add(label2);
        label2.setBounds(255, 15, 85, 20);

        //---- label3 ----
        label3.setText("Type of display:");
        contentPane.add(label3);
        label3.setBounds(255, 40, 85, 20);

        //---- label4 ----
        label4.setText("Processor:");
        contentPane.add(label4);
        label4.setBounds(255, 65, 85, 20);

        //---- label5 ----
        label5.setText("Frequency:");
        contentPane.add(label5);
        label5.setBounds(255, 90, 85, 21);

        //---- label6 ----
        label6.setText("RAM:");
        contentPane.add(label6);
        label6.setBounds(255, 115, 85, 20);

        //---- label7 ----
        label7.setText("Type of RAM:");
        contentPane.add(label7);
        label7.setBounds(495, 15, 85, 21);

        //---- label8 ----
        label8.setText("HHD:");
        contentPane.add(label8);
        label8.setBounds(495, 40, 85, 21);

        //---- label9 ----
        label9.setText("Video:");
        contentPane.add(label9);
        label9.setBounds(495, 65, 85, 21);

        //---- label10 ----
        label10.setText("Web-cam:");
        contentPane.add(label10);
        label10.setBounds(495, 90, 85, 20);

        //---- label11 ----
        label11.setText("OS:");
        contentPane.add(label11);
        label11.setBounds(495, 115, 85, 21);
        contentPane.add(textFieldDisplay);
        textFieldDisplay.setBounds(345, 15, 135, textFieldDisplay.getPreferredSize().height);
        contentPane.add(textFieldTypeOfDisplay);
        textFieldTypeOfDisplay.setBounds(345, 40, 135, 24);
        contentPane.add(textFieldProcessor);
        textFieldProcessor.setBounds(345, 65, 135, 24);
        contentPane.add(textFieldFrequency);
        textFieldFrequency.setBounds(345, 90, 135, 24);
        contentPane.add(textFieldRam);
        textFieldRam.setBounds(345, 115, 135, 24);
        contentPane.add(textFieldTypeOfRam);
        textFieldTypeOfRam.setBounds(585, 15, 135, 24);
        contentPane.add(textFieldHDD);
        textFieldHDD.setBounds(585, 40, 135, 24);
        contentPane.add(textFieldVideo);
        textFieldVideo.setBounds(585, 65, 135, 24);
        contentPane.add(textFieldWebCam);
        textFieldWebCam.setBounds(585, 90, 135, 24);
        contentPane.add(textFieldOS);
        textFieldOS.setBounds(585, 115, 135, 24);
        contentPane.add(textFieldSerial);
        textFieldSerial.setBounds(100, 140, 135, 24);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(745, 265);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JTextField textFieldModel;
    private JTextField textFieldType;
    private JTextField textFieldDate;
    private JTextField textFieldArchitecture;
    private JTextField textFieldColor;
    private JLabel labelModel;
    private JLabel labelType;
    private JLabel labelDate;
    private JLabel labelArchitecture;
    private JLabel labelColor;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JLabel label1;
    private JSeparator separator1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JTextField textFieldDisplay;
    private JTextField textFieldTypeOfDisplay;
    private JTextField textFieldProcessor;
    private JTextField textFieldFrequency;
    private JTextField textFieldRam;
    private JTextField textFieldTypeOfRam;
    private JTextField textFieldHDD;
    private JTextField textFieldVideo;
    private JTextField textFieldWebCam;
    private JTextField textFieldOS;
    private JTextField textFieldSerial;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
