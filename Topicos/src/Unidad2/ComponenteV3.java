package Unidad2;

import javax.swing.*;
import java.awt.event.*;


public class ComponenteV3 extends JPanel implements ComponentListener, ActionListener{
    private JLeeEntero txtNoControl;
    private JTextField txtNombre, txtCarrera;
    private JButton btnGrabar, btnLimpiar;
    private JLabel lblNoControl, lblNombre, lblCarrera;

    public ComponenteV3() {
        super();
        hazInterfaz();
        hazEscuchas();
    }

    private void hazInterfaz() {
        setLayout(null);
        setSize(200, 200);

        txtNoControl = new JLeeEntero(8);
        txtNombre = new JTextField();
        txtCarrera = new JTextField();
        btnGrabar = new JButton("Grabar");
        btnLimpiar = new JButton("Limpiar");
        lblNoControl = new JLabel("No. Control ");
        lblNombre = new JLabel("Nombre ");
        lblCarrera = new JLabel("Carrera ");

    }


    private void hazEscuchas() {
        this.addComponentListener(this);
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
    public void componentResized(ComponentEvent e) {

        int h = this.getHeight();
        int w = this.getWidth();

        lblNoControl.setBounds((int)( w * .10), (int)(h * .05), 80, 10);
        txtNoControl.setBounds((int)( w * .25), (int)(h * .05), 80, 10);

        lblNombre.setBounds((int)( w * .25), (int)(h * .20), 80, 10);
        txtNombre.setBounds((int)( w * .10), (int)(h * .20), 80, 10);
        lblCarrera.setBounds(5, 60, 80, 10);
        txtCarrera.setBounds(90, 60, 60, 10);
        add(lblNoControl);
        add(txtNoControl);
        add(lblNombre);
        add(txtNombre);
     //   add(lblCarrera);
      //  add(txtCarrera);

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
