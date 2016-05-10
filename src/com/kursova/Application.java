package com.kursova;

import com.alee.laf.WebLookAndFeel;
import com.kursova.gui.MainView;

public class Application {

    public static void main( String[] args ) {
        WebLookAndFeel.install();
        MainView view = new MainView();
        view.setVisible( true );
    }

}
