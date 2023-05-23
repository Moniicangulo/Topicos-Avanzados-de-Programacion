package Unidad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class ComponenteV2 extends JPanel implements ActionListener {
    //componentes con re
    private JLeeEntero txtNoControl;
    private JTextField txtNombre, txtCarrera;
    private JButton btnGrabar, btnLimpiar;
    private JLabel lblNoControl, lblNombre, lblCarrera;

    public ComponenteV2() {
        super();
        hazInterfaz();
        hazEscuchas();
    }

    private void hazInterfaz() {
        setLayout(null);
        setSize(200, 200);

        txtNoControl = new JLeeEntero(8);
        txtNombre    = new JTextField();
        txtCarrera   = new JTextField();
        btnGrabar    = new JButton("Grabar");
        btnLimpiar   = new JButton("Limpiar");
        lblNoControl = new JLabel("No. Control ");
        lblNombre    = new JLabel("Nombre ");
        lblCarrera   = new JLabel("Carrera ");

        lblNoControl.setBounds(5, 20, 80, 10);
        txtNoControl.setBounds(90, 20, 60, 10);
        lblNombre.setBounds(5, 40, 60, 10);
        txtNombre.setBounds(90, 40, 60, 10);
        lblCarrera.setBounds(5, 60, 80, 10);
        txtCarrera.setBounds(90, 60, 60, 10);
        add(lblNoControl);
        add(txtNoControl);
        add(lblNombre);
        add(txtNombre);
        add(lblCarrera);
        add(txtCarrera);
    }

    private void hazEscuchas() {
        this.addComponentListener((ComponentListener) this);


    }

    public long getTxtNoControl() {
        return txtNoControl.getCantidad();
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtCarrera() {
        return txtCarrera.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
