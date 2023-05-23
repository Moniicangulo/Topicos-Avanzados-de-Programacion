package Unidad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComponenteV1 extends JPanel implements ActionListener{

    // este componente maneja componentes relativas
    JLeeEntero txtNoControl;
    JTextField txtNombre, txtCarrera;
    JButton btnGrabar, btnLimpiar;

    public ComponenteV1(){
        super();
        hazInterfaz();
        hazEscuchas();
    }

    private void hazInterfaz(){
        txtNoControl = new JLeeEntero(8);
        txtNombre    = new JTextField();
        txtCarrera   = new JTextField();
        btnGrabar    = new JButton("Grabar");
        btnLimpiar   = new JButton("Limpiar");
        setLayout(new GridLayout(0,2, 5, 5));
        add(new JLabel("No. Control ", JLabel.RIGHT));
        add(txtNoControl);
        add(new JLabel("Nombre ", JLabel.RIGHT));
        add(txtNombre);
        add(new JLabel("Carrera ", JLabel.RIGHT));
        add(txtCarrera);
        add(btnGrabar);
        add(btnLimpiar);
    }

    private void hazEscuchas(){

    }

    public long getTxtNoControl(){
        return txtNoControl.getCantidad();
    }

    public String getTxtNombre(){
        return txtNombre.getText();
    }

    public String getTxtCarrera(){
        return txtCarrera.getText();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
