package cz.uhk.fim.pro2.moview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private JPanel rootPanel;
    private JLabel lblNazev;
    private JTextField txtNazev;
    private JTextField txtRok;
    private JButton btnVyhledat;
    private JLabel lblRok;
    private JCheckBox cbRok;

    public UI() {
        lblRok.setText("Rok: ");
        lblNazev.setText("Název: ");
        btnVyhledat.setText("Vyhledat");
        lblRok.setVisible(false);
        txtRok.setVisible(false);

        cbRok.addActionListener(e->{
            if (cbRok.isSelected()){
                lblRok.setVisible(true);
                txtRok.setVisible(true);
                txtNazev.setPreferredSize(new Dimension(400,20));
            } else {
                lblRok.setVisible(false);
                txtRok.setVisible(false);
                txtRok.setText("");
                txtNazev.setPreferredSize(new Dimension(300,20));
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
